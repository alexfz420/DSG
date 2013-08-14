package com.dicks.action;

import java.util.ArrayList;
import java.util.Date;

import com.dicks.dao.OrdersDAO;
import com.dicks.pojo.Orders;

public class OrderListAction {
	private int id;
	private Date date;
	private String status;
	
	private OrdersDAO ordersDAO = OrdersDAO.getInstance();
	private ArrayList<Orders> ordersList;
	
	
	public ArrayList<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(ArrayList<Orders> ordersList) {
		this.ordersList = ordersList;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<Orders> getOrderList() throws Exception {
		ordersList = new ArrayList<Orders>();
		ordersList = ordersDAO.getAllOrders();
		return ordersList;
	}
}
