package com.dicks.util;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;



public class HibernateUtil {
	//private static SessionFactory sessionFactory;
	
//	static {
//		try{
//			sessionFactory = new Configuration().configure().buildSessionFactory();
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
//	
//	public static Session openSession(){
//		Session session = sessionFactory.openSession();
//		return session;
//	}
//	
//	public static void close(Session session){
//		if(null!= session){
//			session.close();
//		}
//
//	}
	
	public static void main(String[] args) {
		RuleDAO rdDao = RuleDAO.getInstance();
		ProdCateDAO pcDao = ProdCateDAO.getInstance();
		ProductDAO productDao = ProductDAO.getInstance();
		Rule[] ruleList = null ;
		Product[] products = null;
		try {
			products = productDao.getProductsBySKUList(new String[]{"aa","bb","cc"});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Product product: products ){
			System.out.println(product.getProdName());
		}
	}
//	
	
	  
}
