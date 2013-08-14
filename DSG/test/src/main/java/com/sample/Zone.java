package com.sample;

import java.util.ArrayList;

public class Zone {
	private String zoneID;
	private ArrayList<Store> storeList;
	public void addStore(Store newStore){
		storeList.add(newStore);
	}
	public String getZoneID() {
		return zoneID;
	}
	public void setZoneID(String zoneID) {
		this.zoneID = zoneID;
	}
	public ArrayList<Store> getStoreList() {
		return storeList;
	}
	public void setStoreList(ArrayList<Store> storeList) {
		this.storeList = storeList;
	}

}

