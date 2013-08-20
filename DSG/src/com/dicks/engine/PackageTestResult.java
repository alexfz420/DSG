package com.dicks.engine;

import java.util.ArrayList;
import java.util.Arrays;

import com.dicks.pojo.Store;

public class PackageTestResult {
	private PackageTest test;
	private Store source;
	private double cost;
	private double attribute;
	private ArrayList<ParcelResult> results = new ArrayList<ParcelResult>();
	private double shippingCost;
	
	public PackageTestResult(PackageTest test) {
		this.test = test;
	}
	
	public PackageTestResult(Parcel test) {

	}
	
	public void addResult(ParcelResult r) {
		results.add(r);
	}
	
	public ArrayList<ParcelResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<ParcelResult> results) {
		this.results = results;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public PackageTest getTest() {
		return test;
	}

	public void setTest(PackageTest test) {
		this.test = test;
	}

	public Store getSource() {
		return source;
	}

	public void setSource(Store source) {
		this.source = source;
	}

	public void calculate() {
		double cost = 0;
		double attribute = 0;
		double shippingCost = 0;
		ArrayList<ParcelResult> parcelResults = getResults();
		for (int i = 0; i < parcelResults.size(); i++) {
			cost += ((ParcelResult) parcelResults.get(i)).getCost();
			attribute += ((ParcelResult) parcelResults.get(i)).getAttribute();
			shippingCost += ((ParcelResult) parcelResults.get(i)).getShippingCost();
		}
		setCost(cost);
		setAttribute(attribute);
		setShippingCost(shippingCost);
	}
	
	@Override
	public String toString() {		
		return "Package result: " + Arrays.toString(results.toArray()) + ", Total cost: " + this.cost;
	}

	public double getAttribute() {
		return attribute;
	}

	public void setAttribute(double attribute) {
		this.attribute = attribute;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

}
