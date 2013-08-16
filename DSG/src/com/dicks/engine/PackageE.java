package com.dicks.engine;

import java.util.ArrayList;
import java.util.Arrays;

import com.dicks.pojo.Product;
import com.dicks.pojo.Orders;

public class PackageE {
	private ArrayList<Product> products = new ArrayList<Product>();
	private int splitNum;
	private Orders order;
	private boolean forRemain;
	private boolean allocated;
	private boolean splitable = true;
	private PackageTestResult bestResult = null;
//	private int zoneID;
	
	public PackageE(Orders order) {
		this.order = order;
	}
	
//	public int getZoneID() {
//		return zoneID;
//	}
//
//	public void setZoneID(int zoneID) {
//		this.zoneID = zoneID;
//	}
	
	// n is index
	public Product getProduct(int index) {
//		System.out.println("index: " + index);
//		System.out.println("boolean: " + );
		if (index > products.size() - 1) return null;
		return products.get(index);
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
	
	public void addProduct(Product p, int qty) {
		for (int i = 0; i < qty; i++) {
			products.add(p);
		}	
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

	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(products.toArray());
	}

	public PackageTestResult getBestResult() {
		return bestResult;
	}

	public void setBestResult(PackageTestResult bestResult) {
		this.bestResult = bestResult;
	}
}
