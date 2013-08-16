package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Fee;
import com.dicks.pojo.Product;
import com.dicks.pojo.Vendor;

public class VendorDAO extends BaseDao<Vendor> 
{
	private static VendorDAO instance = new VendorDAO();
	
	public VendorDAO() {
		super(Vendor.class);
	}

	public static VendorDAO getInstance() {
		return instance;
	}

	public static void setInstance(VendorDAO instance) {
		VendorDAO.instance = instance;
	}
	
	public void createVendor(Vendor vendor) throws Exception {
		super.create(vendor);
	}
	
	public Vendor getById(int id) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("vendId", id);
		criterions.add(criterion);
		return super.get(criterions);
	}

}
