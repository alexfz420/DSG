package com.dicks.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields

	private Integer custId;
	private String password;
	private String address;
	private String zip;
	private String tel;
	private String sex;
	private String custName;
	private Set orderses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String password, String address, String zip, String tel,
			String sex, String custName) {
		this.password = password;
		this.address = address;
		this.zip = zip;
		this.tel = tel;
		this.sex = sex;
		this.custName = custName;
	}

	/** full constructor */
	public Customer(String password, String address, String zip, String tel,
			String sex, String custName, Set orderses) {
		this.password = password;
		this.address = address;
		this.zip = zip;
		this.tel = tel;
		this.sex = sex;
		this.custName = custName;
		this.orderses = orderses;
	}

	// Property accessors

	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

}