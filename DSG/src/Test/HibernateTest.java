package Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.Customer;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.Orders;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.PackageDetail;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.RuleCate;
import com.dicks.pojo.RuleCateId;
import com.dicks.pojo.Store;
import com.dicks.pojo.User;
import com.dicks.pojo.Vendor;


public class HibernateTest
{
//	private static SessionFactory sessionFactory;
	
	
	public static void main(String[] args) throws Exception
	{

		RuleCateDAO.getInstance().createRuleCate(1, new String[]{"1","2","3"}, true);
////		System.out.println(RuleCateDAO.getInstance().getCateNamesByRuleId("2").length);
//		Rule[] allRule = RuleDAO.getInstance().getAllSortedListFromStageOne();
//		for (Rule rule : allRule) {
//			System.out.println(rule.getRuleName());
//		}
//		
		
		
//		RuleCate cate1 = new RuleCate(new RuleCateId(1, 2), null, true);
//		RuleCate cate2 = new RuleCate(new RuleCateId(2, 2), null, true);
//		RuleCate cate3 = new RuleCate(new RuleCateId(3, 2), null, true);
//		RuleCate cate4 = new RuleCate(new RuleCateId(4, 2), null, false);
//		RuleCate cate5 = new RuleCate(new RuleCateId(5, 2), null, false);
//		
//		getCateNamesByRuleId
//		RuleCateDAO.getInstance().create(cate2);
//		RuleCateDAO.getInstance().create(cate3);
//		RuleCateDAO.getInstance().create(cate4);
//		RuleCateDAO.getInstance().create(cate5);
		
//		ProductDAO pDAO = ProductDAO.getInstance();
//		Product p = pDAO.getById(1);
//		
//		System.out.println(RuleDAO.getInstance().getAllRuleList());
		
//		System.out.println(inventoryDAO.g(1, 1));
//		p.setFlag("f");
//		pDAO.updateProducts(new Product[]{p});
//		

////		User user = new User();
////		user.setEmail("ssd");
////		user.setPassword("sgsg");
////		user.setUserName("aa");
//			
////		RuleCategory rc = new RuleCategory();
////		rc.setRule(rule);
////		rc.setRuleID(rule.getRuleID());
////		rc.setCategoryID(2);
////		rc.setIsProductRelated(false);
//		
////		Set<RuleCategory> set = new HashSet<RuleCategory>();
////		set.add(rc);
////
////		rule.setRuleCategorys(set);
//
////		Rule rule = new Rule("ad", "dfgadg", "sdgd", 1, "d", 'd', 'd', "sdgdgfs", "sdfgsdfg", "sfggfd", "sgds", "sgfdg", "sdfggfd");
////		
////		RuleDAO ruleDAOImpl =  RuleDAOImpl.getInstance();
////		ruleDAOImpl.createRule(rule);
//
//		
////		Fee fee= new Fee();
////		fee.setAttribute("afaf");
////		fee.setCostName("Nike");
////		fee.setFlag('a');;
////		fee.setPercentage(134);
////		fee.setShippingType("dfagda");
////		fee.setValue(234);
////		
//////		User user = new User();
//////		user.setEmail("ssd");
//////		user.setPassword("sgsg");
//////		user.setUserName("aa");
////		
////		Rule rule = new Rule("ad", "dfgadg", "sdgd", 1, "d", 'd', 'd', "sdgdgfs", "sdfgsdfg", "sfggfd", "sgds", "sgfdg", "sdfggfd");
////	
////		Vendor vendor = new Vendor();
////		vendor.setAddress("sfdg");
////		vendor.setVendorName("sgdsd");
////		vendor.setZip("23424");
////		
////		RuleCategory rc = new RuleCategory();
////		rc.setCategoryID(2);
////		rc.setIsProductRelated(false);
////		
////		Set<RuleCategory> set = new HashSet<RuleCategory>();
////		set.add(rc);
////
////		rule.setRuleCategorys(set);
////		
////		Store store = new Store();
////		store.setAddress("sdgsdfg");
////		store.setStoreName("sdgds");
////		store.setZip("sdgsd");
////		
////		
////		Product product = new Product();
////		product.setFactoryPrice(123);
////		product.setFlag("g");
////		product.setHeight(323.43);
////		product.setLength(45.34);
////		product.setProductDescr("fhs");
////		product.setProductName("shfsh");
////		product.setSku("sfhfhsfhfsh");
////		product.setVendorID(45);
////		
//	
//		Orders order = new Orders(55,'g',new java.sql.Date(2003, 5, 4), "sg","dsggd","sfdg","gddfg");
////		Orders order = new Orders(customer, totAmt, orderStatus, orderDate, shippingAddr, shippingZip, telNo, custName, packageses, orderDetails)
//		
////		Inventory inventory = new Inventory();
////		inventory.setProductID(2);
////		inventory.setRetailPrice(234);
////		inventory.setSaftyStock(343);
////		inventory.setStorage(343);
////		inventory.setStoreID(435);
////		
////		
//		Customer customer = new Customer();
//		customer.setAddress("gsdgdf");
//		customer.setCustName("sfdgsdg");
//		customer.setPassword("sdgsdfg");
//		customer.setSex("m");
//		customer.setTel("gddgd");
//		customer.setZip("sfhsdfg");
//		customer.getOrderses().add(order);
////			
//
//		Session session = sessionFactory.openSession();
//		Transaction tx = null;
//		
////		Package package1 = new Package();
////		package1.setCustomerID(2);
////		package1.setDeliveryDate(new java.sql.Date(20000));
////		package1.setOrderID(43);
////		package1.setShippingCompany("fedex");
////		package1.setShippingDay(32);
////		package1.setShippingStatus('d');
////		package1.setTotalAmount(34);
////		package1.setWeight(45.43);
////		
////		PackageDetail pd = new  PackageDetail();
////		pd.setPackageID(1);
////		pd.setProductID(3);
////		pd.setQty(32);
////		pd.setUnitPrice(24);
////		
////		OrderDetail od = new OrderDetail();
////		od.setOrderID(2);
////		od.setProductID(3);
////		od.setQty(13);
////		od.setUnitPrice(43);
////		
////		ProductCategory pc = new ProductCategory();
////		pc.setCategoryDescr("dfgsgsf");
////		pc.setCategoryName("sdgf");
////		pc.setProdCategoryID(2);
////		pc.setProdID(4);
////		
////		StoreCategory sc = new StoreCategory();
////		sc.setCategoryDescr("dfgsgsf");
////		sc.setCategoryName("sdgf");
////		sc.setStoreCategoryID(1);
////		sc.setStoreID(4);
////		
////		
//
//		try
//		{
//			tx = session.beginTransaction();
//			
//			session.save(fee);
////			session.save(user);
//			session.save(rule);
//			
//			RuleCategory rc = new RuleCategory();
//			rc.setRule(rule);
//			rc.setRuleID(rule.getRuleID());
//			rc.setCategoryID(2);
//			rc.setIsProductRelated(false);
//			
//			session.save(rc);
//			
////			session.save(fee);
//////			session.save(user);
//////			session.save(rule);
////			session.save(vendor);
////			session.save(store);
////			session.save(product);
////			session.save(order);
////			session.save(inventory);
////			session.save(customer);
//			//session.save(package1);
////			session.save(pd);
//			session.save(customer);
//			tx.commit();
//		}
//		catch(Exception ex)
//		{
//			if(null != tx)
//			{
//				tx.rollback();
//			}
//			
//			ex.printStackTrace();
//		}
//		finally
//		{
//			session.close();
//
//	}
}}
