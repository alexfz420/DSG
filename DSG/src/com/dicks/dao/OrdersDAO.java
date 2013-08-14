package com.dicks.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Orders;

public class OrdersDAO extends BaseDao<Orders> {
	private static OrdersDAO instance = new OrdersDAO();

	public OrdersDAO() {
		super(Orders.class);
	}

	public static OrdersDAO getInstance() {
		return instance;
	}

	public static void setInstance(OrdersDAO instance) {
		OrdersDAO.instance = instance;
	}

	public void createOrder(Orders orders) throws Exception {
		super.create(orders);
	}
	
	public Orders getById(Integer id) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("orderId", id);
		criterions.add(criterion);
		return super.get(criterions);
	}
	
	public ArrayList<Orders> getAllOrders() throws Exception{
		return (ArrayList<Orders>) super.getList();
	}
}
