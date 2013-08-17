package com.dicks.dao;

import com.dicks.pojo.Log;

public class LogDAO extends BaseDao<Log> {
	private static LogDAO instance = new LogDAO();

	public static LogDAO getInstance() {
		return instance;
	}

	public static void setInstance(LogDAO instance) {
		LogDAO.instance = instance;
	}

	public LogDAO() {
		super(Log.class);
	}
	
	public void createLog(Log log) throws Exception {
		super.create(log);
	}
}
