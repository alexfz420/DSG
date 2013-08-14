package com.sample;

import java.util.ArrayList;
import java.util.Arrays;

public class PackageTest {
	private boolean tested;
	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
	private Package pack;
	private int zoneID;
	
	public PackageTest(Package pack, int zoneID) {
		this.pack = pack;
	}
	
	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}
	
	public boolean isTested() {
		return tested;
	}

	public void setTested(boolean tested) {
		this.tested = tested;
	}

	
	public ArrayList<Parcel> getParcels() {
		return parcels;
	}

	public void setParcels(ArrayList<Parcel> parcels) {
		this.parcels = parcels;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}
	
	public void addParcel(Parcel parcel) {
		parcels.add(parcel);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Package test: [");
		sb.append(Arrays.toString(parcels.toArray()));
		sb.append("]");
		
		return sb.toString();
	}
}
