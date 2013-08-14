package com.dicks.dao;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.dicks.engine.Parcel;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Product;

public class OrderDetailDAO extends BaseDao<OrderDetail> {
	private static OrderDetailDAO instance = new OrderDetailDAO();

	public OrderDetailDAO() {
		super(OrderDetail.class);
	}

	public static OrderDetailDAO getInstance() {
		return instance;
	}

	public static void setInstance(OrderDetailDAO instance) {
		OrderDetailDAO.instance = instance;
	}
	
	public void createOrderDetail(OrderDetail orderDetail) throws Exception {
		super.create(orderDetail);
	}

	public ArrayList<OrderDetail> getOrderDetailsByOrder(Orders orders) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("orders.id", orders.getOrderId());
		criterions.add(criterion);
		return (ArrayList<OrderDetail>) super.getList(criterions);
	}
	
	public ArrayList<Product> getProductsByOrder(Orders orders) throws Exception {
		ArrayList<OrderDetail> details = getOrderDetailsByOrder(orders); 
		ArrayList<Product> products = new ArrayList<Product>();
		for (OrderDetail detail : details) {
			products.add(detail.getProduct());
		}
		return products;
	}
	
	public OrderDetail getDetailsByProductOrder(Product product, Orders order) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("product.id", product.getProdId());
		Criterion criterion2 = Restrictions.eq("order.id", order.getOrderId());
		criterions.add(criterion1);
		criterions.add(criterion2);
		return super.get(criterions);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<OrderDetail> getDetailsByParcel(Parcel parcel) {
		Session session = null;
		ArrayList<OrderDetail> details = null;
		try {
			session = HibernateUtil.getSession();
			session.setFlushMode(FlushMode.AUTO);
			session.getTransaction().begin();
			
			Disjunction disjunctions = Restrictions.disjunction();
			for (Product p : parcel.getProducts().keySet()) {								
					disjunctions.add(Restrictions.eq("product.id", p.getProdId()));						
			}
			
			Criteria criteria = session.createCriteria(OrderDetail.class)
					.add(Restrictions.eq("orders.id", parcel.getPack().getOrder().getOrderId()))
					.add(disjunctions);
			
			details = (ArrayList<OrderDetail>) criteria.list();
				
			session.flush();
			session.getTransaction().commit();
			
			return details;
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}