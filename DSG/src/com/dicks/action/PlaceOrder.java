package com.dicks.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dicks.dao.LogDAO;
import com.dicks.dao.PackageDAO;
import com.dicks.dao.PackageDetailDAO;
import com.dicks.dao.ShipmentDAO;
import com.dicks.engine.Allocate;
import com.dicks.engine.CreateTemplate;
import com.dicks.engine.EngineLog;
import com.dicks.engine.PackageE;
import com.dicks.engine.PackageTestResult;
import com.dicks.engine.Parcel;
import com.dicks.engine.ParcelResult;
import com.dicks.engine.Split;
import com.dicks.engine.EngineLog.LogE;
import com.dicks.pojo.Log;
import com.dicks.pojo.LogId;
import com.dicks.pojo.Orders;
import com.dicks.pojo.PackageDetail;
import com.dicks.pojo.PackageDetailId;
import com.dicks.pojo.Packages;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.Shipment;
import com.dicks.pojo.Store;

public class PlaceOrder {
	private String[] product;
	private String[] quantity;
	
	private String shippingtype;
	private String shippingaddress;
	private String shippingzipcode;	
	
	private String id;
	private EngineLog stage1;
	private EngineLog stage2;
	private EngineLog stage3;
//	private ArrayList<LogE> stage1Logs;
	
	private String stage2Logs;
	private String stage3Logs;
	private JSONObject stage2Obj;
	private JSONArray packages;
	private JSONArray stage3Arrays;
	
	private Collection<PackageE> minPackage;
	private Collection<Store> leftStores;
	private Collection<PackageTestResult> allocatedResults;
	private Collection<PackageTestResult> newAllocatedResults;
	private Collection<PackageTestResult> allAllocatedResults;
	
	public String placeorder() throws Exception{
		for(int i=0;i<quantity.length;i++){
			quantity[i] = quantity[i].toLowerCase();
			
			System.out.println("quantity :"+quantity[i]);
			System.out.println("product :"+product[i]);
		}
		
		
		Shipment ss = new Shipment();
		ss = ShipmentDAO.getInstance().getShipmentBySupplyDesitin("15217", "15213");
		System.out.println("distance "+ss.getDistance());
		
		System.out.println("product length: " + product.length);
		System.out.println("quantity length: " + quantity.length);		
		
		Allocate test = new Allocate(product, quantity,shippingtype, shippingaddress, shippingzipcode);
		
		Orders order = test.getOrder();
		this.id = order.getOrderId() + "";
		this.leftStores = test.getLeftStores();
		this.minPackage = test.getPackages();
		this.allocatedResults = test.getAllocatedResults();
		
		this.stage1 = test.getStage1();
		System.out.println();

		Split split = new Split(minPackage, leftStores, allocatedResults);	
		
		this.newAllocatedResults = split.getNewAllocatedResults();
		
		//System.out.println("order id in place order: " + test.getOrderId());			

		LogDAO logDAO =  LogDAO.getInstance();
		
		// For stage 1
//		this.stage1Logs = stage1.getLogs();
		ArrayList<LogE> logEs = stage1.getLogs();
		for (LogE logE : logEs) {
			Rule rule = logE.getRule();
			System.out.println("rule " + logE.getName() + " " + rule);
			Log log = new Log(new LogId(order.getOrderId(), rule.getRuleId()), rule, order, Integer.parseInt(rule.getStage()));
			System.out.println("logs: " + logE.getLogs());
			log.setRecord(Arrays.toString(logE.getLogs().toArray()));
			logDAO.createLog(log);
		}	
		
		// For stage 2
		this.stage2 = split.getStage2();
		for (LogE logE : stage2.getLogs()) {
			Rule rule = logE.getRule();
			System.out.println("rule " + logE.getName() + " " + rule);
			Log log = new Log(new LogId(order.getOrderId(), rule.getRuleId()), rule, order, Integer.parseInt(rule.getStage()));
			System.out.println("logs: " + logE.getLogs());
			log.setRecord(Arrays.toString(logE.getLogs().toArray()));
			logDAO.createLog(log);
		}	
		this.stage2Logs = stage2.getLogsByName("Cost Calculation").get(0);
		JSONParser parser = new JSONParser();
		stage2Obj = (JSONObject) parser.parse(stage2Logs);
		packages = (JSONArray) stage2Obj.get("packages");		
		
		// For stage 3
		this.stage3 = split.getStage3();	
		for (LogE logE : stage3.getLogs()) {
			Rule rule = logE.getRule();
			System.out.println("rule " + logE.getName() + " " + rule);
			Log log = new Log(new LogId(order.getOrderId(), rule.getRuleId()), rule, order, Integer.parseInt(rule.getStage()));
			System.out.println("logs: " + logE.getLogs());
			log.setRecord(Arrays.toString(logE.getLogs().toArray()));
			logDAO.createLog(log);
		}	
		this.setStage3Logs(stage3.getLogsByName("Evaluation").get(0));
		this.stage3Arrays = (JSONArray) parser.parse(this.stage3Logs);

		allAllocatedResults = new ArrayList<PackageTestResult>();
		allAllocatedResults.addAll(this.allocatedResults);
		allAllocatedResults.addAll(this.newAllocatedResults);
		
		for (PackageTestResult r : allAllocatedResults) {
			PackageDAO packageDAO = PackageDAO.getInstance();
			PackageDetailDAO packageDetailDAO = PackageDetailDAO.getInstance();
			ArrayList<ParcelResult> parcelResults = r.getResults();
			for (ParcelResult parcelResult : parcelResults) {
				Packages pack = new Packages(order, order.getCustomer().getCustId(), order.getOrderDate(), 
						order.getTotAmt(), "", "", 3, parcelResult.getParcel().getWeight(), parcelResult.getSource());
				packageDAO.createPackage(pack);
								
				Parcel parcel = parcelResult.getParcel();
				HashMap<Product, Integer> map = parcel.getProducts();
				for (Product product : map.keySet()) {
					//System.out.println("package id: " + pack.getPackageId());
					PackageDetail packDetail = new PackageDetail(new PackageDetailId(pack.getPackageId(), product.getProdId()),
																pack, product, parcel.getProductQty(product));
					packageDetailDAO.createPackageDetail(packDetail);
				}
			}		
		}		
		return "success";	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EngineLog getStage1() {
		return stage1;
	}

	public void setStage1(EngineLog stage1) {
		this.stage1 = stage1;
	}

	public EngineLog getStage2() {
		return stage2;
	}

	public void setStage2(EngineLog stage2) {
		this.stage2 = stage2;
	}

	public EngineLog getStage3() {
		return stage3;
	}

	public void setStage3(EngineLog stage3) {
		this.stage3 = stage3;
	}

//	public ArrayList<LogE> getStage1Logs() {
//		return stage1Logs;
//	}
//
//	public void setStage1Logs(ArrayList<LogE> stage1Logs) {
//		this.stage1Logs = stage1Logs;
//	}

	public Collection<PackageE> getMinPackages() {
		return this.minPackage;
	}

	public void setPackages(Collection<PackageE> packages) {
		this.minPackage = packages;
	}

	public Collection<Store> getLeftStores() {
		return leftStores;
	}

	public void setLeftStores(Collection<Store> leftStores) {
		this.leftStores = leftStores;
	}

	public Collection<PackageTestResult> getAllocatedResults() {
		return allocatedResults;
	}

	public void setAllocatedResults(Collection<PackageTestResult> allocatedResults) {
		this.allocatedResults = allocatedResults;
	}

	public Collection<PackageTestResult> getNewAllocatedResults() {
		return newAllocatedResults;
	}

	public void setNewAllocatedResults(Collection<PackageTestResult> newAllocatedResults) {
		this.newAllocatedResults = newAllocatedResults;
	}

	public String getStage2Logs() {
		return stage2Logs;
	}

	public void setStage2Logs(String stage2Logs) {
		this.stage2Logs = stage2Logs;
	}

	public String getStage3Logs() {
		return stage3Logs;
	}

	public void setStage3Logs(String stage3Logs) {
		this.stage3Logs = stage3Logs;
	}

	public JSONArray getStage3Arrays() {
		return stage3Arrays;
	}

	public void setStage3Arrays(JSONArray stage3Arrays) {
		this.stage3Arrays = stage3Arrays;
	}

	
	public String[] getQuantity(){
		return quantity;
	}
	
	public String[] getProduct(){
		return product;
	}
	
	public void setQuantity(String[] a){
		this.quantity = a;
		
	}
	
	public void setProduct(String[] a){
		this.product = a;
		
	}	
	
	public String getShippingaddress() {
		return shippingaddress;
	}

	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getShippingzipcode() {
		return shippingzipcode;
	}

	public void setShippingzipcode(String shippingzipcode) {
		this.shippingzipcode = shippingzipcode;
	}
	
	public String getShippingtype() {
		return shippingtype;
	}

	public void setShippingtype(String shippingtype) {
		this.shippingtype = shippingtype;
	}
	
	public JSONArray getPackages() {
		return packages;
	}

	public void setPackages(JSONArray packages) {
		this.packages = packages;
	}

}
