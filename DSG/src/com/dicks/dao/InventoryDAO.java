package com.dicks.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
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
	
	public Double getCompetition(int prodId, int storeId) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("store.id", storeId);
		Criterion criterion2 = Restrictions.eq("product.id", prodId);
		criterions.add(criterion1);
		criterions.add(criterion2);
		Inventory inventory  = get(criterions);
		System.out.println("store id " + storeId + " prodId: " + prodId);
		if (inventory == null) return 1.0;
		System.out.println("competition" + inventory.getCompetition());
		return inventory.getCompetition();
	}

	public int getSafetyStock(int prodId, int storeId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("store.id", storeId);
		Criterion criterion2 = Restrictions.eq("product.id", prodId);
		criterions.add(criterion1);
		criterions.add(criterion2);
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

	public boolean containAllProductsParcel(Store store, Parcel parcel) throws Exception {
		HashMap<Product, Integer> products = parcel.getProducts();
		Set<Product> productSet = products.keySet();
		
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.setFlushMode(FlushMode.AUTO);
			session.getTransaction().begin();
					
			for (Product p : productSet) {			
				Criteria criteria = session.createCriteria(Inventory.class)
						.add(Restrictions.conjunction()
						.add(Restrictions.eq("store.id", store.getStoreId()))
						.add(Restrictions.eq("product.id", p.getProdId())));
				Inventory inventory = (Inventory) criteria.uniqueResult();
				if (inventory == null) return false;
				if (inventory.getMargin() < products.get(p)) return false;
			}
				
			session.flush();
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}


	public boolean containAnyProductOrder(Store store, Orders order) throws Exception {
		OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();

		ArrayList<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrder(order);
		Session session = null;
		int noProductNum = 0;
		try {
			session = HibernateUtil.getSession();
			session.setFlushMode(FlushMode.AUTO);
			session.getTransaction().begin();
					
			for (OrderDetail detail : orderDetails) {
				Criteria criteria = session.createCriteria(Inventory.class)
									.add(Restrictions.conjunction()
									.add(Restrictions.eq("store.id", store.getStoreId()))
									.add(Restrictions.eq("product.id", detail.getProduct().getProdId())));
				Inventory inventory = (Inventory) criteria.uniqueResult();
				if (inventory == null) noProductNum++;
				else if (inventory.getInventory() - inventory.getSafetyStock() < detail.getQty()) {
					noProductNum++;
				}
			}		
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return noProductNum < orderDetails.size();
	}

	public boolean checkProduct(Store store, Product product, String operator, int mar) throws Exception {
		Inventory in = getInventoryByStoreProduct(store.getStoreId(), product.getProdId());
		
		if (in != null){
			
			System.out.println("margin!"+(in.getInventory() - in.getSafetyStock()));
		}
		
		
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
	
	public ArrayList<Inventory> getInventoryByParcelStore(Parcel parcel, Store store) {
		Session session = null;
		ArrayList<Inventory> inventories = null;
		try {
			session = HibernateUtil.getSession();
			session.setFlushMode(FlushMode.AUTO);
			session.getTransaction().begin();
			
			Disjunction disjunctions = Restrictions.disjunction();
			for (Product p : parcel.getProducts().keySet()) {								
					disjunctions.add(Restrictions.eq("product.id", p.getProdId()));						
			}
			
			Criteria criteria = session.createCriteria(Inventory.class)
					.add(Restrictions.eq("store.id", store.getStoreId()))
					.add(disjunctions);
			
			inventories = (ArrayList<Inventory>) criteria.list();
				
			session.flush();
			session.getTransaction().commit();
			
			return inventories;
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	public void deleteAll() throws Exception{
		List<Inventory> inventorys =(List<Inventory>)super.getList();
		for(Inventory i : inventorys){
			super.delete(i);
		}
	}
	
	public long count() throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		return super.getCount(criterions);	
	}
}
