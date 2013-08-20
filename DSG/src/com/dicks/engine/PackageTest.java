package com.dicks.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.dicks.engine.PackageE;
import com.dicks.pojo.Product;

public class PackageTest {
	private boolean tested;
	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
	private PackageE pack;
	
	public PackageTest(PackageE pack) {
		this.pack = pack;
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

	public PackageE getPack() {
		return pack;
	}

	public void setPack(PackageE pack) {
		this.pack = pack;
	}
	
	public void addParcel(Parcel parcel) {
		parcels.add(parcel);
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parcels.size(); i++) {
			Parcel parcel = parcels.get(i);
			sb.append("{");
			HashMap<Product, Integer> map = parcel.getProducts();
			for (Product p : map.keySet()) {
				sb.append(p.getProdName());
				sb.append("(" + map.get(p) + ")");
			}
			sb.append("}");
			if (i != parcels.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
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
