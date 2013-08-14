package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Customer;
import com.dicks.pojo.Product;

public class CustomerDAO extends BaseDao<Customer> {
	private static CustomerDAO instance = new CustomerDAO();

	public CustomerDAO() {
		super(Customer.class);
	}

	public static CustomerDAO getInstance() {
		return instance;
	}

	public void createCustomer(Customer customer) throws Exception {
		super.create(customer);
	}

	public Customer getById(int id) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("custId", id);
		criterions.add(criterion);
		return super.get(criterions);
	}

}
