package com.dicks.dao;

import com.dicks.pojo.Fee;
import com.dicks.pojo.Packages;

public class PackageDAO extends BaseDao<Packages>{
	private static PackageDAO instance = new PackageDAO();

	public PackageDAO() {
		super(Packages.class);
	}

	public static PackageDAO getInstance() {
		return instance;
	}

	public static void setInstance(PackageDAO instance) {
		PackageDAO.instance = instance;
	}

	public void createPackage(Packages packages) throws Exception {
		super.create(packages);
	}
}
