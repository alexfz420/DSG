package com.sample;

import java.util.ArrayList;
import java.util.HashMap;

public class Parcel {
//	private int packageTestID;
	private HashMap<Product, Integer> products = new HashMap<Product, Integer>();
	private int deliveryDays;
	private int zoneID;
//	private String source;
//	private Package p;
	
//	public int getPackageTestID() {
//		return packageTestID;
//	}
//	public void setPackageTestID(int packageTestID) {
//		this.packageTestID = packageTestID;
//	}
	
	public Parcel(int zoneID) {
		this.zoneID = zoneID;
	}
	
	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}
	
	public void addProduct(Product p) {
		Integer qty = products.get(p);
		if (qty == null) {
			products.put(p, 1);
		} else {
			products.put(p, qty + 1);
		}
	}
	
	public HashMap<Product, Integer> getProducts() {
		return products;
	}
	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}
	
	public ArrayList<Product> getProductList() {
		ArrayList<Product> list =  new ArrayList<Product>();
		list.addAll(products.keySet());
		return list;
	}
	
	public int getDeliveryDays() {
		return deliveryDays;
	}
	public void setDeliveryDays(int deliveryDays) {
		this.deliveryDays = deliveryDays;
	}
//	public String getSource() {
//		return source;
//	}
//	public void setSource(String source) {
//		this.source = source;
//	}
//	public Package getP() {
//		return p;
//	}
//	public void setP(Package p) {
//		this.p = p;
//	}
	
	@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Parcel: [");
			for (Product p : this.products.keySet()) {
				sb.append(p.getProductName());
				sb.append(" (");
				sb.append(this.products.get(p));
				sb.append(") ");
			}
			sb.append("]");
			
			return sb.toString();
		}
}
