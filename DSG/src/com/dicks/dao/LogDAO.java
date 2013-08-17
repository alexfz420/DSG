package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metamodel.domain.Superclass;

import com.dicks.pojo.Fee;
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



	
	public void createLog(Log log) throws Exception {
		super.create(log);
	}
}
