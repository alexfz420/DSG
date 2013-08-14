package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Fee;

public class FeeDAO extends BaseDao<Fee> {
	private static FeeDAO instance = new FeeDAO();

	public FeeDAO() {
		super(Fee.class);
	}

	public static FeeDAO getInstance() {
		return instance;
	}

	public static void setInstance(FeeDAO instance) {
		FeeDAO.instance = instance;
	}

	public void createFee(Fee fee) throws Exception {
		super.create(fee);
	}
	
	public void updateFee(Fee fee) throws Exception {
		super.update(fee);
	}
	
	public void deleteFee(Fee fee) throws Exception {
		super.delete(fee);
	}

	public ArrayList<Fee> getByType(String type) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("shippingType", type);
		criterions.add(criterion);
		return (ArrayList<Fee>) super.getList(criterions);
	}
}