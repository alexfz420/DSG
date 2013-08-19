package com.dicks.action;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import com.dicks.dao.LogDAO;
import com.dicks.dao.OrderDetailDAO;
import com.dicks.dao.OrdersDAO;
import com.dicks.engine.EngineLog;
import com.dicks.engine.EngineLog.LogE;
import com.dicks.pojo.Log;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.Orders;

public class OrderDetailAction {
	private Date date;
	private String address;
	private String product;
	private String quantity;
	private String id;
	private ArrayList<OrderDetail> details;
	private Orders order;
	private ArrayList<String> logs;
	
//	private ArrayList<LogE> stage1Logs;
//	private String stage2Logs;
//	private String stage3Logs;
//	private JSONObject stage2Obj;
//
//	private JSONArray packages;
//	private JSONArray stage3Arrays;
	
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public ArrayList<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<OrderDetail> details) {
		this.details = details;
	}

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String showDetails() throws Exception{
		System.out.println("id in order detail: " + this.id);
		order = OrdersDAO.getInstance().getById(Integer.parseInt(id));
		details = new ArrayList<OrderDetail>();
		details= OrderDetailDAO.getInstance().getOrderDetailsByOrder(order);
		
		LogDAO logDAO =  LogDAO.getInstance();
		
//		stage2Logs = logDAO.getLogByOrderStage(order.getOrderId(), 2).get(0).getRecord();
//		stage3Logs = logDAO.getLogByOrderStage(order.getOrderId(), 3).get(0).getRecord();
		
//		System.out.println("detail stage2 logs: " + stage2Logs);
		
//		JSONParser parser = new JSONParser();
//		stage2Obj = (JSONArray) parser.parse(stage2Logs);
//		packages = (JSONArray) stage2Obj.get("packages");
//		stage3Arrays = (JSONArray) parser.parse(this.stage3Logs);

//		ArrayList<Log> stage1Logs = LogDAO.getInstance().getLogByOrderStage(order.getOrderId(), 1);
//		this.stage1Logs = new EngineLog(stage1Logs).getLogs();
		
		return "success";
	}

	public ArrayList<String> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<String> logs) {
		this.logs = logs;
	}

//	public String getStage2Logs() {
//		return stage2Logs;
//	}
//
//	public void setStage2Logs(String stage2Logs) {
//		this.stage2Logs = stage2Logs;
//	}

//	public String getStage3Logs() {
//		return stage3Logs;
//	}
//
//	public void setStage3Logs(String stage3Logs) {
//		this.stage3Logs = stage3Logs;
//	}

//	public JSONObject getStage2Obj() {
//		return stage2Obj;
//	}
//
//	public void setStage2Obj(JSONObject stage2Obj) {
//		this.stage2Obj = stage2Obj;
//	}
//
//	public JSONArray getPackages() {
//		return packages;
//	}
//
//	public void setPackages(JSONArray packages) {
//		this.packages = packages;
//	}

//	public JSONArray getStage3Arrays() {
//		return stage3Arrays;
//	}
//
//	public void setStage3Arrays(JSONArray stage3Arrays) {
//		this.stage3Arrays = stage3Arrays;
//	}
//
//	public ArrayList<LogE> getStage1Logs() {
//		return stage1Logs;
//	}
//
//	public void setStage1Logs(ArrayList<LogE> stage1Logs) {
//		this.stage1Logs = stage1Logs;
//	}
}
