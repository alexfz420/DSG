package com.dicks.engine;

import java.util.ArrayList;
import java.util.Arrays;

import com.dicks.engine.PackageE;

public class PackageTest {
	private boolean tested;
	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
	private PackageE pack;
//	private int zoneID;
	
	public PackageTest(PackageE pack) {
		this.pack = pack;
	}
	
//	public int getZoneID() {
//		return zoneID;
//	}
//
//	public void setZoneID(int zoneID) {
//		this.zoneID = zoneID;
//	}
	
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

	public PackageE getPack() {
		return pack;
	}

	public void setPack(PackageE pack) {
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
