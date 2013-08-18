package com.dicks.engine;

import java.util.ArrayList;
import java.util.HashMap;

import com.dicks.pojo.Product;

public class Parcel {
	private HashMap<Product, Integer> products = new HashMap<Product, Integer>();
	private PackageE pack;	
	private int weight;
	private double girth;
	private boolean overSize;
	private boolean overWeight;
	private int storeCount = -1;
	
	public int getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(int storeCount) {
		this.storeCount = storeCount;
	}

	public Parcel(PackageE pack) {
		this.setPack(pack);
	}
	
	public void shipmentPreparation() {
		int totalWeight = 0;
		double maxWidth = 0;
		double totalHeight = 0;
		double maxLength = 0;
				
		for (Product product : products.keySet()) {
			totalWeight += product.getWeight();
			if (product.getWidth() > maxWidth) maxWidth = product.getWidth();
			if (product.getLength() > maxLength) maxLength = product.getLength();
			totalHeight += product.getHeight();
		}
		
		double length = 0;
		double width = maxWidth;
		double height = 0;
		if (totalHeight > maxLength) {
			length = totalHeight;
			height = maxLength;
		}
		this.girth = length + width * 2 + height * 2;
		this.weight = totalWeight;
		this.overSize = this.girth > Util.OVER_SIZE_THRESHOLD;
		this.overWeight = this.weight > Util.OVER_WEIGHT_THRESHOLD;
	}
	
	public void addProduct(Product p) {
		Integer qty = products.get(p);
		if (qty == null) {
			products.put(p, 1);
		} else {
			products.put(p, qty + 1);
		}
	}
	
	public int getProductQty(Product product) {
		Integer qty = products.get(product);
		
		if (qty == null) return 0;
		
		return products.get(product);
	}
	
	public void addNumProduct(Product p, int num) {
		Integer qty = products.get(p);
		if (qty == null) {
			products.put(p, num);
		} else {
			products.put(p, qty + num);
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
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


	public boolean isOverSize() {
		return overSize;
	}

	public void setOverSize(boolean overSize) {
		this.overSize = overSize;
	}

	public boolean isOverWeight() {
		return overWeight;
	}

	public void setOverWeight(boolean overWeight) {
		this.overWeight = overWeight;
	}
	
	
	@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Parcel: [");
			for (Product p : this.products.keySet()) {
				sb.append(p.getProdName());
				sb.append(" (");
				sb.append(this.products.get(p));
				sb.append(") ");
			}
			sb.append("]");
			
			return sb.toString();
		}

	public PackageE getPack() {
		return pack;
	}

	public void setPack(PackageE pack) {
		this.pack = pack;
	}

	public double getGirth() {
		return girth;
	}

	public void setGirth(double girth) {
		this.girth = girth;
	}
}
