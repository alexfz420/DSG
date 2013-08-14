package com.dicks.engine;

import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;

import com.dicks.dao.OrderDetailDAO;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.Orders;

public class OrderE {
	private HashMap<Integer, Integer> products = new HashMap<Integer, Integer>();
	
	public OrderE(Orders order) throws Exception {
		ArrayList<OrderDetail> details = OrderDetailDAO.getInstance().getOrderDetailsByOrder(order);
		
		for (OrderDetail detail : details) {
			products.put(detail.getProduct().getProdId(), detail.getQty());
		}
		
	}
	
	public int getProductQty(int productId) {
		Integer qty = products.get(productId);
		if (qty == null) return 0;
		System.out.println("qty is "+qty);
		return qty;
	}
	
	public void addProduct(int productId, int qty) {
		Integer quantity = products.get(productId);
		if (quantity == null) {
			products.put(productId, qty);
		} else {
			products.put(productId, quantity + qty);
		}
	}
	
	public HashMap<Integer, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Integer, Integer> products) {
		this.products = products;
	}	
	
}
