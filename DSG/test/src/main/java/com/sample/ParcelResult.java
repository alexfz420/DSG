package com.sample;

public class ParcelResult {
	private Parcel parcel;
	private Store source;
	private double cost;
	
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
		return this.parcel + ", source: " + this.source + ", cost: " + this.cost;
	}
	
}
