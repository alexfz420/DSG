package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metamodel.domain.Superclass;

import com.dicks.pojo.Fee;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.Log;
import com.dicks.pojo.Product;
import com.dicks.pojo.Vendor;

public class LogDAO extends BaseDao<Log> 
{
	private static LogDAO instance = new LogDAO();
	
	public LogDAO() {
		super(Log.class);
	}



	public static LogDAO getInstance() {
		return instance;
	}

	public static void setInstance(LogDAO instance) {
		LogDAO.instance = instance;
	}
	
	public ArrayList<Log> getLogByOrderStage(int orderId, int stage) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("orders.id", orderId);
		Criterion criterion2 = Restrictions.eq("stage", stage);
		criterions.add(criterion1);
		criterions.add(criterion2);
		return (ArrayList<Log>) super.getList(criterions);
	}
	
	public void createLog(Log log) throws Exception {
		super.create(log);
	}
}
