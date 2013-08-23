package com.dicks.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.dicks.pojo.Product;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Store;

public class PackageE {
	private ArrayList<Product> products = new ArrayList<Product>();
	private int splitNum;
	private Orders order;
	private boolean forRemain;
	private boolean allocated;
	private boolean splitable = true;
	private boolean unable = false;
	private boolean special = false;
	private Store source;
	private ArrayList<PackageTestResult> bestResults = new ArrayList<PackageTestResult>();
	private JSONArray splits = new JSONArray();

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
	
	public void calculateTops() {
		for (PackageTestResult r : this.bestResults) {
			double cost = 0;
			double attribute = 0;
			double shippingCost = 0;
			ArrayList<ParcelResult> parcelResults = r.getResults();
			for (int i = 0; i < parcelResults.size(); i++) {
				cost += ((ParcelResult) parcelResults.get(i)).getCost();
				attribute += ((ParcelResult) parcelResults.get(i)).getAttribute();
				shippingCost += ((ParcelResult) parcelResults.get(i)).getShippingCost();
			}
			r.setCost(cost);
			r.setAttribute(attribute);
			r.setShippingCost(shippingCost);
		}
	}
	
	public void addTops(ArrayList parcelResults, PackageTest test) {
		for (PackageTestResult testResult : bestResults) {
			testResult.addResult((ParcelResult) parcelResults.get(0));
		}
		
		ArrayList<PackageTestResult> newList = new ArrayList<PackageTestResult>();
		newList.addAll(this.bestResults);
		
		if (newList.size() > 0) {
			for (int i = 1; i < parcelResults.size() && newList.size() < 5; i++) {
				ParcelResult newParcelR = (ParcelResult) parcelResults.get(i);
				for (int j = 0; j < bestResults.size() && newList.size() < 5; j++) {
					PackageTestResult oldTestResult = bestResults.get(j);
					PackageTestResult newTestResult = new PackageTestResult(oldTestResult.getTest());
					ArrayList<ParcelResult> parcelRs = oldTestResult.getResults();
					for (int k = 0; k < parcelRs.size() - 1; k++) {
						newTestResult.addResult(parcelRs.get(k));
					}
					newTestResult.addResult(newParcelR);
					newList.add(newTestResult);
				}
			}
		} else {
			for (int i = 0; i < parcelResults.size() && newList.size() < 5; i++) {
				PackageTestResult testResult = new PackageTestResult(test);
				testResult.addResult((ParcelResult) parcelResults.get(i));
				newList.add(testResult);
			}
		}

		this.bestResults = newList;
	}
	
	public void addSplitTest(PackageTest test) {
		JSONObject split;
		int maxCount = 0;
		if (splitNum >= splits.size())  {
			split = new JSONObject();
			splits.add(split);
		} else {
			split = (JSONObject) splits.get(splitNum);
		}
		
		JSONArray testsJ = (JSONArray) split.get("tests");
		if (testsJ == null) {
			testsJ = new JSONArray();
			split.put("tests", testsJ);
		}
		
		JSONArray testJ = new JSONArray();
		testsJ.add(testJ);
		ArrayList<Parcel> parcels = test.getParcels();
		
		for (Parcel parcel : parcels) {
			JSONObject obj = new JSONObject();
			JSONArray productList = new JSONArray();
			HashMap<Product, Integer> map = parcel.getProducts();
			for (Product product : map.keySet()) {
				JSONObject p = new JSONObject();
				p.put("prodName", product.getProdName());
				p.put("quantity", map.get(product));
				productList.add(p);
			}
			obj.put("products", productList);
			obj.put("storeCount", parcel.getStoreCount());
			testJ.add(obj);
			if (parcel.getStoreCount() > maxCount) {
				maxCount = parcel.getStoreCount();
			}
		}
	
		split.put("maxCount", maxCount);
		
//		System.out.println("split " + this.splitNum + " text: " + text + " splits: " + Arrays.toString(splits.toArray()));
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
		packageE.put("unable", this.isUnable());
		packageE.put("special", this.isSpecial());
		packageE.put("source", this.source == null? null : this.source.getStoreType() + " " + this.source.getStoreName());
		packageE.put("explored", SplitGenerater.getSizeFrom0ToN(this.getProducts().size(), this.splitNum == this.getProducts().size()?splitNum:splitNum + 1));
		System.out.println("!!!!explored: " + SplitGenerater.getSizeFrom0ToN(this.getProducts().size(), this.splitNum) + " splitNum: " + this.splitNum);
		packageE.put("total", SplitGenerater.getTotalSize(this.getProducts().size()));
		//System.out.println("total: " + SplitGenerater.getTotalSize(this.getProducts().size()));
		JSONArray productList = new JSONArray();
		for (Product p : map.keySet()) {
			JSONObject product = new JSONObject();
			product.put("prodName", p.getProdName());
			product.put("quantity", map.get(p));
			productList.add(product);
		}
		packageE.put("products", productList);

//		System.out.println("splits size: " + splits.size());
		packageE.put("splits", splits);
		
		int maxCount = 0;
		for (int i = 0; i < splits.size(); i++) {
			JSONObject split = (JSONObject) splits.get(i);
			int count = (Integer) split.get("maxCount");
			System.out.println("count: " + count);
			if (maxCount < count) {
				maxCount = count;
			}
		}
		packageE.put("maxCount", maxCount);
		
//		System.out.println("json splits: " + splitsArray.toString());	
		return packageE;
	}
	
	public JSONArray getStage3Json() {
		JSONArray array = new JSONArray();
		
		for (PackageTestResult r : this.bestResults) {
			JSONObject rJ = new JSONObject();
			JSONArray resultsJ = new JSONArray();
			for (ParcelResult parcelR : r.getResults()) {
				JSONObject parcelRJ = new JSONObject();
				
				//products
				JSONArray productsJ = new JSONArray();
				HashMap<Product, Integer> map = parcelR.getParcel().getProducts();
				for (Product p : map.keySet()) {
					JSONObject pJ = new JSONObject();
					pJ.put("prodName", p.getProdName());
					pJ.put("quantity", map.get(p));
					productsJ.add(pJ);
				}
				parcelRJ.put("products", productsJ);
				String type = parcelR.getSource().getStoreType();
				parcelRJ.put("source", type.substring(0,1).toUpperCase() + type.substring(1) + " " + parcelR.getSource().getStoreName());
				parcelRJ.put("totalCost", parcelR.getCost());
				
				//costs
				parcelRJ.put("costs", Util.getJsonCosts(parcelR));
				
				resultsJ.add(parcelRJ);
			}
			
			rJ.put("results", resultsJ);
			rJ.put("totalCost", r.getCost());
			rJ.put("attribute", r.getAttribute());
			
			array.add(rJ);
		}
		
		//System.out.println("array: " + array);
		
		return array;
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
	
	public ArrayList<PackageTestResult> getBestResults() {
		return bestResults;
	}

	public void setBestResults(ArrayList<PackageTestResult> bestResults) {
		this.bestResults = bestResults;
	}
	
	public void addTop(PackageTestResult testResult) {
		this.bestResults.add(testResult);
	}

	public boolean isUnable() {
		return unable;
	}

	public void setUnable(boolean unable) {
		this.unable = unable;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public Store getSource() {
		return source;
	}

	public void setSource(Store source) {
		this.source = source;
	}
}
