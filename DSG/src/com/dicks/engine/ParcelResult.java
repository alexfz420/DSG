package com.dicks.engine;

import com.dicks.pojo.Store;

public class ParcelResult {
	private Parcel parcel;
	private Store source;
	private double cost;
	private double attribute;
	private double shippingCost;
	private double otherCost;
	
	public ParcelResult(Parcel parcel) {
		this.parcel = parcel;
	}
	
	public Parcel getParcel() {
		return parcel;
	}
	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}
	public Store getSource() {
		return source;
	}
	public void setSource(Store source) {
		this.source = source;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return this.parcel + ", source: " + this.source + ", shippingCosts: " + this.shippingCost + ", cost: " + this.cost;
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
	
	public void calculateCosts() throws Exception {
		long shippingCosts = Util.getShippingCosts(parcel, source);
		long otherCosts = Util.calculateCosts(parcel, source);
		long totalCosts = shippingCosts + otherCosts;
		this.shippingCost = (double) shippingCosts / 100.0;
		this.cost = (double) totalCosts / 100.0;
		this.otherCost = (double) otherCosts / 100.0;
		Util.calculateAttribute(this);
	}

	public double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(double otherCost) {
		this.otherCost = otherCost;
	}
	
}
