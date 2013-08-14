package com.sample;

import java.util.ArrayList;
import java.util.Arrays;

public class PackageTestResult {
	private PackageTest test;
	private Store source;
	private double cost;
	private ArrayList<ParcelResult> results = new ArrayList<ParcelResult>();
	
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

	@Override
	public String toString() {		
		return "Package result: " + " result: " + Arrays.toString(results.toArray()) + ", Total cost: " + this.cost;
	}
}
