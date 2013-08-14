package com.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class Order {
	private int orderID;
	private CustomerInfo cusInfo;
	private HashMap<Product, Integer> productList;
	private double desireScore;
	private Date estimatedDate;
	private Date orderDate;
	private int maxItem;
	private int zoneID;
	private int exploredItem;
	private int quantity;
	private ArrayList<Package> packages = new ArrayList<Package>();
//	private ArrayList<Store> stores = new ArrayList<Store>();
	
	public Order(int zoneID) {
		this.zoneID = zoneID;
		productList = new HashMap<Product, Integer>();
	}
	
	public ArrayList<Package> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}

//	public ArrayList<Store> getStores() {
//		return stores;
//	}
//
//	public void setStores(ArrayList<Store> stores) {
//		this.stores = stores;
//	}
//	
//	public void addStore(Store s) {
//		stores.add(s);
//	}
	
	public String packages() {
		return Arrays.toString(packages.toArray());
	}
	
	public void addPackage(Package p) {
		packages.add(p);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getExploredItem() {
		return exploredItem;
	}

	public void setExploredItem(int exploredItem) {
		this.exploredItem = exploredItem;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public ArrayList<Product> getProducts () {
		ArrayList<Product> list =  new ArrayList<Product>();
		
		for (Product p : productList.keySet()) {
			int qty = productList.get(p);
			for (int i = 0; i < qty; i++) {
				list.add(p);
			}
		}
			
		return list;
	}
	
	public int getQuantitys (String productID) {
		int i = 0;
		for (Product p : productList.keySet()) {
			if ((p.productID).equals(produtID))
				i++;
		}
			
		return i;
	}

	public void addProducts(Product prod,int number){
		productList.put(prod, number);
		this.quantity += number;
	}
	public int getZoneID() {
		return zoneID;
	}
	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}
	public CustomerInfo getCusInfo() {
		return cusInfo;
	}
	public void setCusInfo(CustomerInfo cusInfo) {
		this.cusInfo = cusInfo;
	}
	public HashMap<Product, Integer> getProductList() {
		System.out.println(productList);
		return productList;
	}
	public void setProductList(HashMap<Product, Integer> productList) {
		this.productList = productList;
	}
	public double getDesireScore() {
		return desireScore;
	}
	public void setDesireScore(double desireScore) {
		this.desireScore = desireScore;
	}
	public Date getEstimatedDate() {
		return estimatedDate;
	}
	public void setEstimatedDate(Date estimatedDate) {
		this.estimatedDate = estimatedDate;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getMaxItem() {
		return maxItem;
	}
	public void setMaxItem(int maxItem) {
		this.maxItem = maxItem;
	}
	
	

}
