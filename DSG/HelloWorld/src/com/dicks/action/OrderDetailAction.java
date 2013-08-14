package com.dicks.action;

import java.util.ArrayList;
import java.util.Date;


import com.dicks.dao.OrderDetailDAO;
import com.dicks.dao.OrdersDAO;
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
		
		return "success";
	}

	public ArrayList<String> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<String> logs) {
		this.logs = logs;
	}
}
