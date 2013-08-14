package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.dicks.dao.OrderDetailDAO;
import com.dicks.dao.OrdersDAO;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.Product;
import com.dicks.pojo.Orders;

public class Store {
	private int storeID;
	private String location;
	private HashMap<Product, Inventory> stock;
	private int zoneID;
	private int value=0;
	
	public Store(int storeID, int zoneID) {
		this.storeID = storeID;
		this.zoneID = zoneID;
		stock = new HashMap<Product, Inventory>();
	}

	
	public boolean containProductsInOrder(Orders order) throws Exception {
		List<OrderDetail> details = OrderDetailDAO.getInstance().getOrderDetailsByOrder(order);
		int notContainingNum = 0;
		for (OrderDetail d : details) {
			if (!this.containProduct(d.getProduct())) notContainingNum++;
		}
		return notContainingNum < 
					OrderDetailDAO.getInstance().getOrderDetailsByOrder(order).size();
	}
	
	public boolean containParcel(Parcel parcel) {
		HashMap<Product, Integer> products = parcel.getProducts();
		Set<Product> productSet = products.keySet();
		
		for (Product p : productSet) {
			int qty = products.get(p);
			if (!containNumProduct(p, qty)) {
//				System.out.println("rule out product: " + p + ", qty: " + qty);
				return false;
			}
		}
		
		return true;		
	}
	
	public boolean containProducts(ArrayList<Product> products) {
		for (Product p : products) {
			if (!this.containProduct(p)) {
				System.out.println("rule out product: " + p.getProdName());
				return false;
			}
		}		
		return true;
	}
	
	public boolean containNumProduct(Product product, int num) {
		Inventory in = stock.get(product);
		if (in == null) return false;
		//System.out.println("store: " + this.zoneID + " margin: " + in.getMargin());
		if (in.getMargin() >= num) return true;
		return false;
	}
	
	public boolean containProduct(Product product) {
		//System.out.println("-----------");
		//System.out.println("checking filter1 for store "+this.storeID);
		Inventory in = stock.get(product);
		if (in == null) return false;
		//System.out.println("store: " + this.storeID + " margin: " + in.getMargin());
		if (in.getMargin() > 0) {
			
			//System.out.println("filter1 return true");
			return true;
		}
		else{
			//System.out.println("filter1 return false");
			return false;
		}
	}
	
	
	
	public boolean checkProduct(Product product, String operator , int mar) {
		//System.out.println("-----------");
		//System.out.println("checking filter2 for store "+this.storeID);
		Inventory in = stock.get(product);
		//System.out.println("operator  "+operator+"   "+mar);
		if (operator.equals(">")){
			//System.out.println(">>>>>>>>>>>>"+in.getMargin());
			if (in == null || in.getMargin() <= mar) 
			{
				//System.out.println("filter2 return false");
				return false;
			}
			//System.out.println("store: " + this.storeID + " margin: " + in.getMargin());
			if (in.getMargin() > mar) 
				{
				//System.out.println("filter2 return true");
				return true;}
			else{
				//System.out.println("filter2 return false");
			return false;
			}
		}
		else if (operator.equals("<")){
			if (in == null || in.getMargin() >= mar) return false;
			System.out.println("store: " + this.storeID + " margin: " + in.getMargin());
			if (in.getMargin() < mar) return true;
			return false;
			
		}
		else if (operator.equals("=")){
			if (in == null || in.getMargin() != mar) return false;
			System.out.println("store: " + this.storeID + " margin: " + in.getMargin());
			if (in.getMargin() == mar) return true;
			return false;
			
		}
		else{
			return false;
		}
		
	}
	
	public boolean checkStore(Product product, String attribute, String operator , int mar) {
		Inventory in = stock.get(product);
		if (attribute.equalsIgnoreCase("UPS Zone")){
			/*if (operator.equals(">")){
				
				System.out.println("Order Zone: " + order.getZoneID() + "Store Zone: " + this.zoneID);
				if ((order.getZoneID()-this.zoneID) > mar) return true;
				return false;
			}
			else if (operator.equals("<")){
				System.out.println("Order Zone: " + order.getZoneID() + "Store Zone: " + this.zoneID);
				if ((order.getZoneID()-this.zoneID) < mar)  return true;
				return false;
				
			}
			else if (operator.equals("=")){
				System.out.println("Order Zone: " + order.getZoneID() + "Store Zone: " + this.zoneID);
				if ((order.getZoneID()-this.zoneID) == mar)  return true;
				return false;
				
			}
			else{
				return false;
			}*/
			return false;
		}
		else if (attribute.equalsIgnoreCase("margin")){
			
			return this.checkProduct(product, operator, mar);
		}
		
		else{
			return false;
		}
		
		
	}
	
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public int getZoneID() {
		return zoneID;
	}
	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}
	
	public static class Inventory {
		private int instockNum;
		private int safetyStock;
		
		public Inventory(int instockNum, int safetyStock) {
			this.instockNum = instockNum;
			this.safetyStock = safetyStock;
		}
		
		public int getMargin() {
			if (instockNum <= safetyStock) return 0;
			return instockNum - safetyStock;
		}
		
		public int getInstockNum() {
			return instockNum;
		}
		public void setInstockNum(int instockNum) {
			this.instockNum = instockNum;
		}
		public int getSafetyStock() {
			return safetyStock;
		}
		public void setSafetyStock(int safetyStock) {
			this.safetyStock = safetyStock;
		}		
	}
	
	@Override
	public String toString() {
		return "store zoneID: " + this.getZoneID();
	}
}
