package com.dicks.pojo;

/**
 * ProdCate entity. @author MyEclipse Persistence Tools
 */

public class ProdCate extends Category implements java.io.Serializable  {

	// Fields
	private ProdCateId id;
	private Product product;
	private String appliedRuleList;

	// Constructors

	/** default constructor */
	public ProdCate() {
	}

	/** minimal constructor */
	public ProdCate(ProdCateId id, Product product, String cateName) {
		this.id = id;
		this.product = product;
		super.setCateName(cateName);
	}

	/** full constructor */
	public ProdCate(ProdCateId id, Product product, String cateName,
			String cateDescr) {
		this.id = id;
		this.product = product;
		super.setCateName(cateName);
		super.setCateDescr(cateDescr);
	}

	// Property accessors

	public ProdCateId getId() {
		return this.id;
	}

	public void setId(ProdCateId id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAppliedRuleList() {
		return appliedRuleList;
	}

	public void setAppliedRuleList(String appliedRuleList) {
		this.appliedRuleList = appliedRuleList;
	}



}