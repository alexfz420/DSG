package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.OrdersDAO;
import com.dicks.pojo.Orders;

public class Redirect {
	private ArrayList<Orders> ordersList;
	public ArrayList<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(ArrayList<Orders> ordersList) {
		this.ordersList = ordersList;
	}

//	public String gotorulelist() {
//
//		return "gotorulelist";
//	}

	public String gotoorderlist() throws Exception {
		ordersList = new ArrayList<Orders>();
		ordersList = OrdersDAO.getInstance().getAllOrders();
		return "gotoorderlist";
	}

	public String gotocategorylist() {
		return "goToCategoryList";
	}

	public String gotonewbizrulelist() {
		return "gotonewbizrulelist";
	}

	public String gototemplate() {
		return "gototemplate";
	}

	public String gotoplaceorder() {
		return "gotoplaceorder";
	}
	public String gotonewcategory(){
		return "gotonewcategory";
	}
	
	public String gotoruleprioritylist(){
		return "success";
	}
	
	public String goToStatistic(){
		return "success";
	}
}
