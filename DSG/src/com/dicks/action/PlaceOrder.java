package com.dicks.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.dicks.dao.PackageDAO;
import com.dicks.dao.PackageDetailDAO;
import com.dicks.engine.Allocate;
import com.dicks.engine.CreateTemplate;
import com.dicks.engine.EngineLog;
import com.dicks.engine.PackageE;
import com.dicks.engine.PackageTestResult;
import com.dicks.engine.Parcel;
import com.dicks.engine.ParcelResult;
import com.dicks.engine.Split;
import com.dicks.engine.EngineLog.Log;
import com.dicks.pojo.Orders;
import com.dicks.pojo.PackageDetail;
import com.dicks.pojo.PackageDetailId;
import com.dicks.pojo.Packages;
import com.dicks.pojo.Product;
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
	private ArrayList<Log> stage1Logs;
	
	private Collection<PackageE> packages;
	private Collection<Store> leftStores;
	private Collection<PackageTestResult> allocatedResults;
	private Collection<PackageTestResult> newAllocatedResults;
	private Collection<PackageTestResult> allAllocatedResults;
	
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
	
	public String placeorder() throws Exception{
		for(int i=0;i<quantity.length;i++){
			quantity[i] = quantity[i].toLowerCase();
			
			System.out.println("quantity :"+quantity[i]);
			System.out.println("product :"+product[i]);
		}
		System.out.println("product length: " + product.length);
		System.out.println("quantity length: " + quantity.length);
		
		
		Allocate test = new Allocate(product, quantity,shippingtype, shippingaddress, shippingzipcode);
		
		Orders order = test.getOrder();
		this.packages = test.getPackages();
		this.leftStores = test.getLeftStores();
		this.allocatedResults = test.getAllocatedResults();
		
		Split split = new Split(packages, leftStores, stage2, allocatedResults);	
		this.newAllocatedResults = split.getNewAllocatedResults();
		
		//System.out.println("order id in place order: " + test.getOrderId());
		
		this.stage1 = test.getStage1();
		this.stage2 = test.getStage2();
		this.stage3 = split.getStage3();
		this.stage1Logs = stage1.getLogs();	
		
		allAllocatedResults = new ArrayList<PackageTestResult>();
		allAllocatedResults.addAll(this.allAllocatedResults);
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
					PackageDetail packDetail = new PackageDetail(new PackageDetailId(order.getOrderId(), product.getProdId()),
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

	public ArrayList<Log> getStage1Logs() {
		return stage1Logs;
	}

	public void setStage1Logs(ArrayList<Log> stage1Logs) {
		this.stage1Logs = stage1Logs;
	}

	public Collection<PackageE> getPackages() {
		return packages;
	}

	public void setPackages(Collection<PackageE> packages) {
		this.packages = packages;
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

}
