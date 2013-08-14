package com.dicks.pojo;

/**
 * PackageDetail entity. @author MyEclipse Persistence Tools
 */

public class PackageDetail implements java.io.Serializable {

	// Fields

	private PackageDetailId id;
	private Packages packages;
	private Product product;
	private Integer qty;
	private Integer unitPrice;

	// Constructors

	/** default constructor */
	public PackageDetail() {
	}

	/** minimal constructor */
	public PackageDetail(PackageDetailId id, Packages packages,
			Product product, Integer qty) {
		this.id = id;
		this.packages = packages;
		this.product = product;
		this.qty = qty;
	}

	/** full constructor */
	public PackageDetail(PackageDetailId id, Packages packages,
			Product product, Integer qty, Integer unitPrice) {
		this.id = id;
		this.packages = packages;
		this.product = product;
		this.qty = qty;
		this.unitPrice = unitPrice;
	}

	// Property accessors

	public PackageDetailId getId() {
		return this.id;
	}

	public void setId(PackageDetailId id) {
		this.id = id;
	}

	public Packages getPackages() {
		return this.packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

}