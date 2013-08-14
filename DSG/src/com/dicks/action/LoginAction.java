package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.OrdersDAO;
import com.dicks.pojo.Orders;

public class LoginAction {
	private String username;
	
	private String password;
	private ArrayList<Orders> ordersList;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute() throws Exception{
		
		ordersList = new ArrayList<Orders>();
		ordersList = OrdersDAO.getInstance().getAllOrders();
		return "success";
	}

	public ArrayList<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(ArrayList<Orders> ordersList) {
		this.ordersList = ordersList;
	}
}
