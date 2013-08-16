package com.dicks.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Packages entity. @author MyEclipse Persistence Tools
 */

public class Packages implements java.io.Serializable {

	// Fields
	private Integer packageId;
	private Orders orders;
	private Integer custId;
	private Store store;
	private Timestamp shippingDate;
	private Integer totAmt;
	private String shippingStatus;
	private String shippingCompany;
	private Integer shippingDay;
	private Integer weight;
	private Set packageDetails = new HashSet(0);

	// Constructors
	/** default constructor */
	public Packages() {
	}

	/** minimal constructor */
	public Packages(Orders orders, Integer custId, Timestamp shippingDate,
			Integer totAmt, String shippingStatus, String shippingCompany,
			Integer shippingDay, Integer weight) {
		this.orders = orders;
		this.custId = custId;
		this.shippingDate = shippingDate;
		this.totAmt = totAmt;
		this.shippingStatus = shippingStatus;
		this.shippingCompany = shippingCompany;
		this.shippingDay = shippingDay;
		this.weight = weight;
	}

	/** full constructor */
	public Packages(Orders orders, Integer custId, Timestamp shippingDate,
			Integer totAmt, String shippingStatus, String shippingCompany,
			Integer shippingDay, Integer weight, Set packageDetails) {
		this.orders = orders;
		this.custId = custId;
		this.shippingDate = shippingDate;
		this.totAmt = totAmt;
		this.shippingStatus = shippingStatus;
		this.shippingCompany = shippingCompany;
		this.shippingDay = shippingDay;
		this.weight = weight;
		this.packageDetails = packageDetails;
	}

	// Property accessors

	public Integer getPackageId() {
		return this.packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Timestamp getShippingDate() {
		return this.shippingDate;
	}

	public void setShippingDate(Timestamp shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Integer getTotAmt() {
		return this.totAmt;
	}

	public void setTotAmt(Integer totAmt) {
		this.totAmt = totAmt;
	}

	public String getShippingStatus() {
		return this.shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getShippingCompany() {
		return this.shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	public Integer getShippingDay() {
		return this.shippingDay;
	}

	public void setShippingDay(Integer shippingDay) {
		this.shippingDay = shippingDay;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Set getPackageDetails() {
		return this.packageDetails;
	}

	public void setPackageDetails(Set packageDetails) {
		this.packageDetails = packageDetails;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
