package Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.json.simple.JSONObject;
import org.junit.Test;

import com.dicks.dao.CustomerDAO;
import com.dicks.dao.FeeDAO;
import com.dicks.dao.InventoryDAO;
import com.dicks.dao.OrderDetailDAO;
import com.dicks.dao.OrdersDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.dao.VendorDAO;
import com.dicks.pojo.Customer;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.InventoryId;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.OrderDetailId;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.Store;
import com.dicks.pojo.Vendor;

import com.dicks.engine.PackageE;
import com.dicks.engine.Parcel;
import com.dicks.engine.Util;
import java.sql.Timestamp;

public class TestUtil {	
//	@Test
	public void testFee() {
		FeeDAO feeDao = FeeDAO.getInstance();
		try {
			ArrayList<Fee> fees = feeDao.getByType("store");			
			for (Fee f : fees) {
				System.out.println("fee: " + f.getCostName() + ", " + f.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRule2() throws Exception {
		//System.out.println(InventoryDAO.getInstance().(1, 1));
	}
	
//	@Testå
	public void testRule() {
		try {
			Rule r = RuleDAO.getInstance().getRuleById(1+"");
			r.setRuleName("");
			r.setValue("1");
			RuleDAO.getInstance().update(r);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void testParcel() {
		class MyIntComparable implements Comparator<Integer>{
			 
		    @Override
		    public int compare(Integer o1, Integer o2) {
		        return (o1>o2 ? 1 : (o1==o2 ? 0 : -1));
		    }
		} 

	    List<Integer> list = new ArrayList<Integer>();
	    list.add(5);
	    list.add(4);
	    list.add(3);
	    list.add(7);
	    list.add(2);
	    list.add(1);
	    Collections.sort(list, new MyIntComparable());
	    for (Integer integer : list) {
	      System.out.println(integer);
	    }

	}

	
//	@Test
	public void testInventoryEvaluate() {
		InventoryDAO inventoryDAO = InventoryDAO.getInstance();
		try {
			Orders order = OrdersDAO.getInstance().getById(17);
			Store store = StoreDAO.getInstance().getById(4);
			System.out.println(inventoryDAO.containAnyProductOrder(store, order));
			
			Product hat = ProductDAO.getInstance().getById(6);
			Product shirt = ProductDAO.getInstance().getById(7);
			
			PackageE pack = new PackageE(order);
			Parcel parcel = new Parcel(pack);
			parcel.addProduct(hat);
			parcel.addProduct(shirt);
			
			System.out.println(inventoryDAO.containAllProductsParcel(store, parcel));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void createRule() {
		Rule rule = new com.dicks.pojo.Rule("abc", " ", "", 1, "1", "1", "1", "a", "a", "a", "5", "", "");
		try {
			RuleDAO.getInstance().createRule(rule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
	public void createCosts() {
		FeeDAO feeDao = FeeDAO.getInstance();

		Fee transactionFee = new Fee("Transaction Fee", "store", 939, "factoryPrice|product");
		Fee freightFee = new Fee("Freight In to Store", "store", 148, "factoryPrice|product");
		Fee distributionFee = new Fee("Distribution Center Cost Allocation", "store", 148, "factoryPrice|product");
		Fee packageCost = new Fee("Packaging Cost", "store", 112);
		Fee laborCost = new Fee("Labor Cost", "store", 117);

		try {
			feeDao.createFee(transactionFee);
			feeDao.createFee(freightFee);
			feeDao.createFee(distributionFee);
			feeDao.createFee(packageCost);
			feeDao.createFee(laborCost);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

//	@Test
	public void createStore() {
		Store store = new Store("abc", "abc", "15213", "", "store");
		try {
			StoreDAO.getInstance().createStore(store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
	public void createVendor() {
		Vendor vendor = new Vendor("nike", "abc", "21253");
		VendorDAO vendorDAO = VendorDAO.getInstance();
		try {
			vendorDAO.createVendor(vendor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
	public void createProduct() {
		try {
			Vendor vendor = VendorDAO.getInstance().getById(7);

			Product product = new Product(vendor, "hat", "123-456-789", "nike hat", 5000, 2000, "TH-A,ST-A,SP-A");
			ProductDAO productDao = ProductDAO.getInstance();
			productDao.createProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
//	@Test
	public void createOrder() {
		try {
//			Product shoes = ProductDAO.getInstance().getById(5);
//			Product shirt = ProductDAO.getInstance().getById(7);
//			Customer customer = CustomerDAO.getInstance().getById(1);
//			Orders orders = new Orders(customer, 10000, "f", new Timestamp(new Date().getTime()), "cmu", "15213", "412-637-2008", "mike");
//			OrdersDAO.getInstance().createOrder(orders);
			
			Orders orders = OrdersDAO.getInstance().getById(3);
			Product p = ProductDAO.getInstance().getById(7);
			
			OrderDetail detail = new OrderDetail();
			detail.setId(new OrderDetailId(orders.getOrderId(), p.getProdId()));
			detail.setOrders(orders);
			detail.setProduct(p);
			detail.setQty(1);
			detail.setUnitPrice(1000);
			OrderDetailDAO.getInstance().createOrderDetail(detail);
			
//			Orders orders = OrdersDAO.getInstance().getById(2);
//			
//			Set<OrderDetail> details = orders.getOrderDetails();
//			for (OrderDetail d : details) {
//				System.out.println(d.getProduct().getProdName());
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
	public void createInventory() {

		try {
			ProductDAO productDAO = ProductDAO.getInstance();
			Product shoes = productDAO.getById(5);
			Product shirt = productDAO.getById(7);
			Product hat = productDAO.getById(6);

			StoreDAO storeDAO = StoreDAO.getInstance();
			Store s1 = storeDAO.getById(1);
			Store s2 = storeDAO.getById(2);
			Store s3 = storeDAO.getById(3);
			Store s4 = storeDAO.getById(4);
			Store s6 = storeDAO.getById(6);

			Product p = shirt;

			Inventory in1 = new Inventory();
			in1.setId(new InventoryId(p.getProdId(), s1.getStoreId()));
			in1.setProduct(p);
			in1.setStore(s1);
			in1.setRetailPrice(2000);
			in1.setInventory(7);
			in1.setSafetyStock(5);

			Inventory in2 = new Inventory();
			in2.setId(new InventoryId(p.getProdId(), s2.getStoreId()));
			in2.setProduct(p);
			in2.setStore(s2);
			in2.setRetailPrice(2000);
			in2.setInventory(7);
			in2.setSafetyStock(5);	

			Inventory in3 = new Inventory();
			in3.setId(new InventoryId(p.getProdId(), s3.getStoreId()));
			in3.setProduct(p);
			in3.setStore(s3);
			in3.setRetailPrice(2000);
			in3.setInventory(7);
			in3.setSafetyStock(5);	

			Inventory in4 = new Inventory();
			in4.setId(new InventoryId(p.getProdId(), s4.getStoreId()));
			in4.setProduct(p);
			in4.setStore(s4);
			in4.setRetailPrice(2000);
			in4.setInventory(7);
			in4.setSafetyStock(5);	

			Inventory in5 = new Inventory();
			in5.setId(new InventoryId(p.getProdId(), s6.getStoreId()));
			in5.setProduct(p);
			in5.setStore(s6);
			in5.setRetailPrice(2000);
			in5.setInventory(7);
			in5.setSafetyStock(5);	

			InventoryDAO.getInstance().createInventory(in1);
			InventoryDAO.getInstance().createInventory(in2);
			InventoryDAO.getInstance().createInventory(in3);
			InventoryDAO.getInstance().createInventory(in4);
			InventoryDAO.getInstance().createInventory(in5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


//	@Test
	public void testInventory() {
		Product shoes;
		Store s1;
		try {
			s1 = StoreDAO.getInstance().getById(2);
			shoes = ProductDAO.getInstance().getById(5);
			System.out.println(InventoryDAO.getInstance().containProduct(s1, shoes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
//	@Test
	public void testCosts() {
		try {
			Orders orders = OrdersDAO.getInstance().getById(3);
			
			PackageE pack = new PackageE(orders);
			Parcel parcel = new Parcel(pack);
			Product shoes = ProductDAO.getInstance().getById(5);
			Product shirt = ProductDAO.getInstance().getById(7);
			Product hat = ProductDAO.getInstance().getById(6);
			parcel.addProduct(shoes);
//			parcel.addProduct(shirt);
//			parcel.addProduct(hat);
			
			Store store = StoreDAO.getInstance().getById(1);
			
			System.out.println(Util.calculateCosts(parcel, store));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Package pack = new Package(order);
//		Parcel parcel = new Parcel(pack, 1);
//		parcel.addProduct(p1);
//		
//		Store store = new Store("cmu", "15213", "store");

//		double costs = Util.calculateCosts(parcel, store);
//		
//		System.out.println("total costs: " + costs);
	}
}
