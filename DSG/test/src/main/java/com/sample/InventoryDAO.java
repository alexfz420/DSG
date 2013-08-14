package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.dicks.engine.Parcel;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.InventoryId;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Product;
import com.dicks.pojo.Store;

public class InventoryDAO extends BaseDao<Inventory> {
	private static InventoryDAO instance = new InventoryDAO();

	public static InventoryDAO getInstance() {
		return instance;
	}

	public static void setInstance(InventoryDAO instance) {
		InventoryDAO.instance = instance;
	}

	public InventoryDAO() {
		super(Inventory.class);
	}

	public int getSafetyStock(int prodId, int storeId) throws Exception{
		InventoryId id = new InventoryId(prodId, storeId);
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("InventoryId", id);
		criterions.add(criterion);
		Inventory inventory  = get(criterions);
		return inventory.getSafetyStock();
	}

	public void createInventory(Inventory inventory) throws Exception {
		super.create(inventory);
	}

	public Inventory getInventoryByStoreProduct(int storeId, int productId) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("store.id", storeId);
		Criterion criterion2 = Restrictions.eq("product.id", productId);
		criterions.add(criterion1);
		criterions.add(criterion2);
		return super.get(criterions);
	}

	public boolean containNumProduct(Store store, Product product, int num) throws Exception {
		Inventory inventory = getInventoryByStoreProduct(store.getStoreId(), product.getProdId());

		if (inventory == null) return false;

		if (inventory.getInventory() - inventory.getSafetyStock() < num) return false;
		return true;
	}

	public boolean containProduct(Store store, Product product) throws Exception  {
		return containNumProduct(store, product, 1);
	}

	public boolean containAllroductsParcel(Store store, Parcel parcel) throws Exception {
		HashMap<Product, Integer> products = parcel.getProducts();
		Set<Product> productSet = products.keySet();

		List<Criterion> criterion = new ArrayList<Criterion>();
		Disjunction disjunctions = Restrictions.disjunction();

		for (Product p : productSet) {
			disjunctions.add(Restrictions.conjunction()
					.add(Restrictions.eq("store.id", store.getStoreId()))
					.add(Restrictions.eq("product.id", p.getProdId())));
		}
		criterion.add(disjunctions);

		return (int) super.getCount(criterion) == productSet.size();
	}


	public boolean containAnyProductOrder(Store store, Orders order) throws Exception {
		OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();

		ArrayList<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrder(order);

		List<Criterion> criterion = new ArrayList<Criterion>();
		Disjunction disjunctions = Restrictions.disjunction();

		for (OrderDetail orderDetail : orderDetails) {
			Product p = orderDetail.getProduct();
			disjunctions.add(Restrictions.conjunction()
					.add(Restrictions.eq("store.id", store.getStoreId()))
					.add(Restrictions.eq("product.id", p.getProdId())));
		}
		criterion.add(disjunctions);
		return (int) super.getCount(criterion) > 0;    
	}

	public boolean checkProduct(Store store, Product product, String operator, int mar) throws Exception {
		Inventory in = getInventoryByStoreProduct(store.getStoreId(), product.getProdId());
		if (operator.equals(">")){
			if (in == null || in.getInventory() - in.getSafetyStock() <= mar) {
				return false;
			}				
			return in.getInventory() - in.getSafetyStock() > mar;
		} 
		
		if (operator.equals("<")){
			if (in == null || in.getInventory() - in.getSafetyStock() >= mar) return false;
			int margin = in.getInventory() - in.getSafetyStock();
			return margin < mar && margin > 0;
		}
		
		if (operator.equals("=")){
			if (in == null || in.getInventory() - in.getSafetyStock() != mar) return false;
//			int margin = in.getInventory() - in.getSafetyStock();
//			System.out.println("store: " + store.getStoreId() + " margin: " + margin);
			return in.getInventory() - in.getSafetyStock() == mar;			
		}
		
		return false;
	}
}
