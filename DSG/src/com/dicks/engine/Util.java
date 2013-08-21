package com.dicks.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import org.apache.commons.lang3.text.WordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dicks.dao.FeeDAO;
import com.dicks.dao.FeeDAO;
import com.dicks.dao.InventoryDAO;
import com.dicks.dao.OrderDetailDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.ShipmentDAO;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.Product;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Shipment;
import com.dicks.pojo.Store;

public class Util {
	public final static int OVER_SIZE_THRESHOLD = 10000;
	public final static int OVER_WEIGHT_THRESHOLD = 10000;
	static String operator = "";
	static String attribute = "";
	
	// Get package result from a list of potential stores
	public static PackageTestResult getTestResult(PackageTest test, ArrayList<Store> stores) throws Exception {
		PackageTestResult r = new PackageTestResult(test);
		ArrayList<Parcel> parcels = test.getParcels();
		
		if (parcels == null || parcels.size() < 1) return null;
		
		// check whether there is same product among parcels
		boolean isDuplicate = checkParcelDupliate(parcels);
		
		if (isDuplicate) {
			LinkedList<Parcel> parcelsQ = new LinkedList<Parcel>(parcels);
			
			for (int i = 0; i < parcels.size(); i++) {
				Parcel p = parcelsQ.removeFirst();
				
				p.shipmentPreparation();
				
				ArrayList<ParcelResult> parcelResults = new ArrayList<ParcelResult>();
				int count = 0;
				for (int j = 0; j < stores.size(); j++) {
					Store s = (Store) stores.get(j);
					ParcelResult parcelR = getParcelResult(p, s);
					if (parcelR != null) {
						parcelResults.add(parcelR);
						Util.calculateAttribute(parcelR);
						count++;
						//System.out.println("in get test result attribute");
					}
				}
				p.setStoreCount(count);
				if (parcelResults.size() < 1) {
					//System.out.println("in test result: no result for " + test);
					return null;
				}		
				
				Collections.sort(parcelResults, new Comparator<ParcelResult>() {
					@Override
					public int compare(ParcelResult arg0, ParcelResult arg1) {
						return Util.compareParcelResult(arg0, arg1); 
					}			
				});
				
				for (int k = 0; k < parcelResults.size(); k++) {
					ParcelResult parcelR = parcelResults.get(i);
					HashMap<Integer, HashMap<Product, Integer>> allocated = new HashMap<Integer, HashMap<Product, Integer>>();
					
					allocated.put(parcelR.getSource().getStoreId(), parcelR.getParcel().getProducts());
					
					for (int l = 0; l < parcelsQ.size(); l++) {
						
					}
				}
				
				
				parcelsQ.addLast(p);
			}			
		} else {
			//System.out.println("in get test result");	
			for (int i = 0; i < parcels.size(); i++) {
				Parcel p = (Parcel) parcels.get(i);
				p.shipmentPreparation();
				ArrayList<ParcelResult> parcelResults = new ArrayList<ParcelResult>();
				int count = 0;
				for (int j = 0; j < stores.size(); j++) {
					Store s = (Store) stores.get(j);
					ParcelResult parcelR = getParcelResult(p, s);
					if (parcelR != null) {
						parcelResults.add(parcelR);
						Util.calculateAttribute(parcelR);
						count++;
						//System.out.println("in get test result attribute");
					}
				}
				p.setStoreCount(count);
				if (parcelResults.size() < 1) {
					//System.out.println("in test result: no result for " + test);
					return null;
				}		
				Collections.sort(parcelResults, new Comparator<ParcelResult>() {
					@Override
					public int compare(ParcelResult arg0, ParcelResult arg1) {
						return Util.compareParcelResult(arg0, arg1); 
					}			
				});
				test.getPack().addTops(parcelResults, test);
				r.addResult((ParcelResult) parcelResults.get(0));
			}
			
			r.calculate();
			test.getPack().calculateTops();
		}
		
		return r;
	}
	
	public static boolean checkParcelDupliate(ArrayList<Parcel> parcels) {
		HashSet<Integer> productIdSet = new HashSet<Integer>();
		for (Parcel parcel : parcels) {
			for (Product p : parcel.getProducts().keySet()) {
				if (productIdSet.contains(p.getProdId())) return false;
				productIdSet.add(p.getProdId());
			}
		}
		return true;
	}
	
	public static ParcelResult getParcelResult(Parcel parcel, Store store) throws Exception {
		if (!InventoryDAO.getInstance().containAllProductsParcel(store, parcel)) return null;
		ParcelResult r = new ParcelResult(parcel);
		r.setSource(store);
		r.calculateCosts();	
//		System.out.println("For pacel " + parcel + ", store: " + store + 
//							", totalCosts: " + r.getCost() + ", shipping costs: " + r.getShippingCost() + 
//							", other costs: " + r.getOtherCost() + ", attribute: " + r.getAttribute());
		//System.out.println("shipping costs: " + r.getShippingCost());
		return r;
	}

	
	public static JSONArray getJsonCosts(ParcelResult r) {
		JSONArray costsJ = new JSONArray();
		
		Parcel parcel = r.getParcel();
		Store store = r.getSource();
		Set<Product> products = parcel.getProducts().keySet();		
		FeeDAO feeDao = FeeDAO.getInstance();
		try {
			ArrayList<Fee> fees = feeDao.getByType(store.getStoreType());		
			Integer[] costs = new Integer[fees.size()];
			for (Fee fee : fees) {
				JSONObject feeJ = new JSONObject();
				feeJ.put("name", fee.getCostName());
				
				long singleCost = 0;
				
				if (fee.getFlag().equals("v")) {
					singleCost = fee.getValue() * parcel.getProductList().size();
//					System.out.println(fee.getCostName() + ": " + ((double) fee.getValue()) / 100.0);
				} else if (fee.getFlag().equals("p")) {
					String attributeName = fee.getAttribute();
					int attributeValue = 0;
					String[] names = attributeName.split(",");
//					System.out.println("names: " + Arrays.toString(names));
					if (names[1].equals("cost")) {
						for (int i = 0; i < fees.size(); i++) {
							if (fees.get(i).getCostName().equals(names[0])) {
								attributeValue = costs[i];
							}
						}
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("product")) {
						for (Product p : products) {
							attributeValue += getAttribute(p, Product.class, names[0]) * parcel.getProductQty(p);
//							System.out.println("product: " + p.getProdName());
						}	
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("store")) {
						attributeValue = getAttribute(store, Store.class, names[0]);
					} else if (names[1].equals("orderDetail")) {	
						
						ArrayList<OrderDetail> details = OrderDetailDAO.getInstance().getDetailsByParcel(parcel);
						
						//System.out.println("details size: " + details.size());						
						for (OrderDetail detail : details) {
							int attribute = getAttribute(detail, OrderDetail.class, names[0]);
							int qty =  parcel.getProductQty(detail.getProduct());
//							System.out.println(names[1] + "-" + names[0] + ": " + attribute + " " + qty);

							attributeValue += attribute * qty;
						}
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("inventory")) {
						ArrayList<Inventory> inventories = InventoryDAO.getInstance().getInventoryByParcelStore(parcel, store);
						//System.out.println("inventory size: " + inventories.size());
						for (Inventory inventory : inventories) {
							attributeValue += getAttribute(inventory, Inventory.class, names[0]) * parcel.getProductQty(inventory.getProduct());
							//System.out.println("product: " + inventory.getProduct().getProdName() + ", price: " + inventory.getRetailPrice());
						}					
						System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("order")) {
						attributeValue = getAttribute(parcel.getPack().getOrder(), Orders.class, names[0]);
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					}
					singleCost = attributeValue * fee.getPercentage() / 10000;
//					System.out.println("total costs: " + totalCosts);					
				}
				feeJ.put("value", (double) singleCost / 100.0);
				System.out.println("feeJ: " + feeJ);
				costsJ.add(feeJ);
				System.out.println("costJ: " + costsJ);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject shippingCost = new JSONObject();
		shippingCost.put("name", "Shipping Cost");
		shippingCost.put("value", r.getShippingCost());
		costsJ.add(shippingCost);
		
		JSONObject totalCost = new JSONObject();
		totalCost.put("name", "Total Cost");
		totalCost.put("value", r.getCost());
		costsJ.add(totalCost);
		
		return costsJ;
	}
	
	public static long getShippingCosts(Parcel parcel, Store store) throws Exception {
		String supplyZip = store.getZip();
		String destinationZip = parcel.getPack().getOrder().getShippingZip();
		Shipment shipment = ShipmentDAO.getInstance().getShipmentBySupplyDesitin(supplyZip, destinationZip);
		int distance = shipment.getDistance();
		if (shipment == null) {
			//System.out.println("shipment null");
			return Integer.MAX_VALUE;		
		}
		int rate = 0;
		if (parcel.isOverWeight()) rate = shipment.getOverWeightRate();
		else if (parcel.isOverSize()) {
			rate = shipment.getOverSizeRate();
		} else {
			rate = shipment.getNormalRate();
		}		
		//System.out.println("rate: " + rate + " weight: " + parcel.getWeight() + "distance: " + (1+(distance/300)));
		return ((parcel.getWeight()*100) * (rate) * (100 + (distance * 100/ 300))) / 10000;
	}
	
	public static void calculateAttribute(ParcelResult parcelR) {
		if (attribute.equals("retailPrice")) {
			Parcel parcel = parcelR.getParcel();
			Store store = parcelR.getSource();
			
			ArrayList<Inventory> inventories = InventoryDAO.getInstance().getInventoryByParcelStore(parcel, store);
			int attribute = 0;			
			for (Inventory inventory : inventories) {
				attribute += inventory.getRetailPrice();
			}			
			parcelR.setAttribute((double) attribute / 100.0);
		} else if (attribute.equals("shippingCost")) {
			//System.out.println("caculate attribute: shipping costs");
			parcelR.setAttribute(parcelR.getShippingCost());
		} else if (attribute.equals("margin")) {
			Parcel parcel = parcelR.getParcel();
			Store store = parcelR.getSource();
			
			ArrayList<Inventory> inventories = InventoryDAO.getInstance().getInventoryByParcelStore(parcel, store);
			int attribute = 0;			
			for (Inventory inventory : inventories) {
				attribute += inventory.getRetailPrice();
			}			
			//System.out.println("attribute: margin, retail price: " + attribute);
			attribute -= parcelR.getShippingCost() * 100;
			parcelR.setAttribute((double) attribute / 100.0);
		} else if (attribute.equals("proximity")) {
			
		} else if (attribute.equals("totalCost")) {
			parcelR.setAttribute(parcelR.getCost());
			//System.out.println("caculate attribute: total costs");
		} else if (attribute.equals("otherCost")) {
			parcelR.setAttribute(parcelR.getOtherCost());
		}
	}
	
	public static int compareParcelResult(ParcelResult arg0, ParcelResult arg1) {
		if (Util.operator.equals("max")) {
			return Double.compare(arg1.getAttribute(), arg0.getAttribute());
		} else if (Util.operator.equals("min")) {
			return Double.compare(arg0.getAttribute(), arg1.getAttribute());
		}
		return 0;
	}
	
	public static int comparePackageTestResult(PackageTestResult arg0, PackageTestResult arg1) {
		if (Util.operator.equals("max")) {
			return Double.compare(arg1.getAttribute(), arg0.getAttribute());
		} else if (Util.operator.equals("min")) {
			return Double.compare(arg0.getAttribute(), arg1.getAttribute());
		}
		return 0;
	}
	
	public static long calculateCosts(Parcel parcel, Store store) {
		Set<Product> products = parcel.getProducts().keySet();		
		long totalCosts = 0;
		FeeDAO feeDao = FeeDAO.getInstance();
		try {
			ArrayList<Fee> fees = feeDao.getByType(store.getStoreType());		
			Integer[] costs = new Integer[fees.size()];
			for (Fee fee : fees) {
				if (fee.getFlag().equals("v")) {
					totalCosts += fee.getValue() * parcel.getProductList().size();
//					System.out.println(fee.getCostName() + ": " + ((double) fee.getValue()) / 100.0);
				} else if (fee.getFlag().equals("p")) {
					String attributeName = fee.getAttribute();
					int attributeValue = 0;
					String[] names = attributeName.split(",");
//					System.out.println("names: " + Arrays.toString(names));
					if (names[1].equals("cost")) {
						for (int i = 0; i < fees.size(); i++) {
							if (fees.get(i).getCostName().equals(names[0])) {
								attributeValue = costs[i];
							}
						}
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("product")) {
						for (Product p : products) {
							attributeValue += getAttribute(p, Product.class, names[0]) * parcel.getProductQty(p);
//							System.out.println("product: " + p.getProdName());
						}	
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("store")) {
						attributeValue = getAttribute(store, Store.class, names[0]);
					} else if (names[1].equals("orderDetail")) {	
						
						ArrayList<OrderDetail> details = OrderDetailDAO.getInstance().getDetailsByParcel(parcel);
						
						//System.out.println("details size: " + details.size());
						
						for (OrderDetail detail : details) {
							int attribute = getAttribute(detail, OrderDetail.class, names[0]);
							int qty =  parcel.getProductQty(detail.getProduct());
//							System.out.println(names[1] + "-" + names[0] + ": " + attribute + " " + qty);

							attributeValue += attribute * qty;
						}
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("inventory")) {
						ArrayList<Inventory> inventories = InventoryDAO.getInstance().getInventoryByParcelStore(parcel, store);
						//System.out.println("inventory size: " + inventories.size());
						for (Inventory inventory : inventories) {
							attributeValue += getAttribute(inventory, Inventory.class, names[0]) * parcel.getProductQty(inventory.getProduct());
							//System.out.println("product: " + inventory.getProduct().getProdName() + ", price: " + inventory.getRetailPrice());
						}					
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					} else if (names[1].equals("order")) {
						attributeValue = getAttribute(parcel.getPack().getOrder(), Orders.class, names[0]);
						//System.out.println("product: " + fee.getPercentage()/100.0 + "% of " + names[0] + ": " + attributeValue);
					}
					totalCosts += attributeValue * fee.getPercentage() / 10000;
//					System.out.println("total costs: " + totalCosts);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCosts;
	}
	
	public static <T> int getAttribute(T t, Class<T> clz, String name) {
		Field[] fields = clz.getDeclaredFields();
		for(Field field:fields) {
			if(field.getName().equals(name)) {				
				try {
					String methodName = "get" + WordUtils.capitalize(name);
					Method method = clz.getDeclaredMethod(methodName, new Class[0]);
					int attribute = (Integer) method.invoke(t, new Object[0]);
					return attribute;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		return 0;
	}
}
