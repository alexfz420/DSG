package com.dicks.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.metamodel.relational.Database;







import com.dicks.dao.InventoryDAO;
import com.dicks.dao.ShipmentDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.InventoryId;
import com.dicks.pojo.Shipment;
import com.dicks.pojo.ShipmentId;
import com.dicks.pojo.Store;

public class DatabaseUtil {
	public static void main(String[] args) {
		try {
			
//			StoreDAO.getInstance(). deletePie();
			
			
			
			
//			createShipment("94065");

			
			InventoryDAO.getInstance().deleteAll();
			ceateInventory(1,100,80000,110000,10,20,5,10,0.2,0.7);
			ceateInventory(2,300,13000,15000,1,30,0,5,0.1,0.9);
			ceateInventory(3,300,11000,13000,1,30,0,5,0.1,0.9);
			ceateInventory(4,300,4000,5000,1,30,0,5,0.1,0.9);
			ceateInventory(5,300,7000,8000,1,30,0,5,0.1,0.9);
			ceateInventory(6,300,3000,4000,1,30,0,5,0.1,0.9);
			ceateInventory(7,300,13000,14000,1,30,0,5,0.1,0.9);			
			ceateInventory(8,500,65000,75000,0,70,0,10,0.1,0.9);
			ceateInventory(9,500,9900,11000,0,70,0,10,0.1,0.9);
			System.out.println(InventoryDAO.getInstance().count());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void ceateInventory(int prodId, int storeNum, int priceMin, int priceMax, int inventoryMin, int inventoryMax,int safetyStockMin, int safetyStockMax, double competitionMin, double competitionMax) throws Exception{
		int[] storeIds = getStoreIds(storeNum);
		for(int storeId : storeIds){
			InventoryId inventoryId =  new InventoryId(prodId,storeId);
			Inventory inventory = new Inventory();
			inventory.setId(inventoryId);
			inventory.setRetailPrice(getRamdomInt(priceMin,priceMax));
			int inven = getRamdomInt(inventoryMin,inventoryMax);
			inventory.setInventory(inven);
			int i = getRamdomInt(safetyStockMin,safetyStockMax);
			int j = inven-i;
			if(j<0) j=0;
			inventory.setSafetyStock(j);
			inventory.setCompetition(getRamdomDouble(competitionMin, competitionMax));
			InventoryDAO.getInstance().createInventory(inventory);
		}

	}
	
	private static double getRamdomDouble(double a,double b){
		double c = b-a;
		return a+c*(Math.random());
	}
	
	private static int getRamdomInt(int a, int b){
		double c = b-a;
		return (int) (a+c*(Math.random()));
	}
	
	private static int[] getStoreIds(int num){
		Set<Integer> set = new HashSet<Integer>();
		int[] array = new int[num];
		while(set.size()<num){
			set.add(getRamdomInt(9,563));
		}
		int j = 0;
		for(Integer i : set){
			array[j] = i;
			j++;
		}
		
		return array;
	}
	
	private static String getDistance(String zip1, String zip2){
	
		String url = "http://zipcodedistanceapi.redline13.com/rest/orafELjK7PoLCdtHdZG9K98KtYeAmNLLfW0THkEOa2AMf4us3aCNjLyNJMo5zaRO/distance.csv/"+zip1+"/"+zip2+"/mile";
		
		
		String str = null;
		
		while("null".equals(str)||null==str){
			
			str = run(url);
		}

		return str.replaceFirst("distance", "");
	}
	
	private static String run(String url){
		String str = null;
		try {
			Thread.sleep(1000);

			str = NetworkUtil.getStringContentFromURL(url);
		} catch (Exception e) {
			return "null";
			
		}
		return str;
	}
	
	private static void createShipment(String originZip) throws Exception{
	//	ShipmentDAO.getInstance().deleteAll();
		List<Store> stores = StoreDAO.getInstance().getAllStores();
		int i = 0;
		for(Store store:stores){
			if(i<410){
				i++;
				continue;
			}
			ShipmentId id = new ShipmentId(originZip, store.getZip());
			String miles = getDistance(originZip, store.getZip());
			int[] rates = getRates(miles);
			Shipment shipment = new Shipment();
			shipment.setId(id);
			shipment.setDistance((int)(Double.parseDouble(miles)));
			shipment.setNormalRate(rates[0]);
			shipment.setOverSizeRate(rates[1]);
			shipment.setOverWeightRate(rates[2]);
			ShipmentDAO.getInstance().create(shipment);
		}
	}
	
	private static int[] getRates(String miles){
		int[] rates  = new int[3];
		double d = Double.parseDouble(miles);
		int m = (int)d;
		if(m>=0 && m<500){
			rates[0]= 678;
	
		}else if(m>=500 && m<1000){
			rates[0]= 778;
		}else if(m>=1000 && m<1500){
			rates[0]= 878;
		}else if(m>=1500 && m<2000){
			rates[0]= 978;
		}else if(m>=2000 && m<2500){
			rates[0]= 1078;
		}else if(m>=2500 && m<3000){
			rates[0]= 1178;
		}else if(m>=3000 && m<3500){
			rates[0]= 1278;
		}else if(m>=3500){
			rates[0]= 1378;
		}	
		rates[1]= rates[0]+150;
		rates[2]= rates[1]+100;	
		return rates;
	}
	
}
