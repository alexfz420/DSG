package com.dicks.action;

import java.util.ArrayList;

import com.dicks.engine.Allocate;
import com.dicks.engine.CreateTemplate;
import com.dicks.engine.EngineLog;
import com.dicks.engine.EngineLog.Log;

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
		System.out.println("order id in place order: " + test.getOrderId());
		this.id = test.getOrderId();
		
		this.stage1 = test.getStage1();
		this.stage2 = test.getStage2();
		this.stage3 = test.getStage3();
		this.stage1Logs = stage1.getLogs();
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

}
