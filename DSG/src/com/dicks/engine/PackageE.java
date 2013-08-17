package com.dicks.engine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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

	public PackageE(Orders order) {
		this.order = order;
	}
	
	public Product getProduct(int index) {
		if (index > products.size() - 1) return null;
		return products.get(index);
	}	

	@Override
	public String toString() {
		return Arrays.toString(products.toArray());
	}
	
	@SuppressWarnings({ "unchecked"})
	public JSONObject getJson() {
		HashMap<Product, Integer> map = new HashMap<Product, Integer>();
		
		for (Product p : products) {
			Integer qty = map.get(p);
			if (qty == null) {
				map.put(p, 1);
			} else {
				map.put(p, qty + 1);
			}
		}
		
		JSONObject packageE = new JSONObject();
		packageE.put("splitNum", this.splitNum);
		JSONArray productList = new JSONArray();
		for (Product p : map.keySet()) {
			JSONObject product = new JSONObject();
			product.put("prodName", p.getProdName());
			product.put("quantity", map.get(p));
			productList.add(product);
		}
		packageE.put("products", productList);
//		StringWriter out = new StringWriter();
//		try {
//			packageE.writeJSONString(out);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String jsonText = out.toString();		
		return packageE;
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
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	


	public PackageTestResult getBestResult() {
		return bestResult;
	}

	public void setBestResult(PackageTestResult bestResult) {
		this.bestResult = bestResult;
	}
}
