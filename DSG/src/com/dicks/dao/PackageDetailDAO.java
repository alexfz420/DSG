package com.dicks.dao;

import com.dicks.pojo.PackageDetail;
import com.dicks.pojo.Packages;

public class PackageDetailDAO extends BaseDao<PackageDetail> {
	private static PackageDetailDAO instance = new PackageDetailDAO();

	public PackageDetailDAO() {
		super(PackageDetail.class);
	}

	public static PackageDetailDAO getInstance() {
		return instance;
	}

	public static void setInstance(PackageDetailDAO instance) {
		PackageDetailDAO.instance = instance;
	}

	public void createPackageDetail(PackageDetail packageDetail) throws Exception {
		super.create(packageDetail);
	}

}
