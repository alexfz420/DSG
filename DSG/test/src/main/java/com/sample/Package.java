package com.sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Package {
	private ArrayList<Product> products = new ArrayList<Product>();
	private int splitNum;
	private Order order;
	private boolean forRemain;
	private boolean allocated;
	private boolean splitable = true;
	private int zoneID;
	
	public Package(Order order) {
		this.order = order;
	}
	
	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}
	
	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}
	
	public boolean isForRemain() {
		return forRemain;
	}

	public void setForRemain(boolean forRemain) {
		this.forRemain = forRemain;
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}
	
	public boolean isSeparable() {
		return this.splitNum < this.products.size();
	}
	
	public int getQuantity() {
		return this.products.size();
	}
	
	public boolean isSplitable() {
		return this.splitable;
	}
	
	public void setSplitable(boolean splitable) {
		this.splitable = splitable;
	}
	
	public int getSplitNum() {
		return splitNum;
	}

	public void setSplitNum(int splitNum) {
		this.splitNum = splitNum;
		if (splitNum == this.products.size()) this.splitable = false;
	}
	
//	public void addProduct(Product product) {
//		Integer qty = products.get(product);
//		if (qty == null) {
//			products.put(product, 1);
//		} else {
//			products.put(product, qty + 1);
//		}
//	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
//	public int getPackageID() {
//		return packageID;
//	}
//
//	public void setPackageID(int packageID) {
//		this.packageID = packageID;
//	}

	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(products.toArray());
	}
}
