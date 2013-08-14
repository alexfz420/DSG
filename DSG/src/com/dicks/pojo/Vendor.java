package com.dicks.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Vendor entity. @author MyEclipse Persistence Tools
 */

public class Vendor implements java.io.Serializable {

	// Fields

	private Integer vendId;
	private String vendName;
	private String address;
	private String zip;
	private Set products = new HashSet(0);

	// Constructors

	/** default constructor */
	public Vendor() {
	}

	/** minimal constructor */
	public Vendor(String vendName, String address, String zip) {
		this.vendName = vendName;
		this.address = address;
		this.zip = zip;
	}

	/** full constructor */
	public Vendor(String vendName, String address, String zip, Set products) {
		this.vendName = vendName;
		this.address = address;
		this.zip = zip;
		this.products = products;
	}

	// Property accessors

	public Integer getVendId() {
		return this.vendId;
	}

	public void setVendId(Integer vendId) {
		this.vendId = vendId;
	}

	public String getVendName() {
		return this.vendName;
	}

	public void setVendName(String vendName) {
		this.vendName = vendName;
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

	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}