package com.dicks.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields
	private Integer prodId;
	private Vendor vendor;
	private String prodName;
	private String sku;
	private String prodDescr;
	private Integer factoryPrice;
	private Integer weight;
	private Double length;
	private Double width;
	private Double height;
	private String flag = "TH-A,ST-A,SP-A";
	private boolean isSpecial;
	private Set orderDetails = new HashSet(0);
	private Set inventories = new HashSet(0);
	private Set packageDetails = new HashSet(0);
	private Set prodCates = new HashSet(0);

	// Constructors

	/** default constructor 
	 * @param k 
	 * @param j 
	 * @param i 
	 * @param string2 
	 * @param string */
	
	/** minimal constructor */
	public Product(){
	}

	public boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public Product (String sku, String productName, double price, Integer weight, int size) {
		this.sku = sku;
		this.prodName = prodName;
		this.factoryPrice = factoryPrice;
		this.weight = weight;
		
	}
	
	public Product(Vendor vendor, String prodName, String sku,
			String prodDescr, Integer factoryPrice, Integer weight, String flag) {
		this.vendor = vendor;
		this.prodName = prodName;
		this.sku = sku;
		this.prodDescr = prodDescr;
		this.factoryPrice = factoryPrice;
		this.weight = weight;
		this.flag = flag;
	}

	/** full constructor */
	public Product(Vendor vendor, String prodName, String sku,
			String prodDescr, Integer factoryPrice, Integer weight,
			String flag, Double length, Double width, Double height,
			Set orderDetails, Set inventories, Set packageDetails, Set prodCates) {
		this.vendor = vendor;
		this.prodName = prodName;
		this.sku = sku;
		this.prodDescr = prodDescr;
		this.factoryPrice = factoryPrice;
		this.weight = weight;
		this.flag = flag;
		this.length = length;
		this.width = width;
		this.height = height;
		this.orderDetails = orderDetails;
		this.inventories = inventories;
		this.packageDetails = packageDetails;
		this.prodCates = prodCates;
	}

	// Property accessors

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
//		System.out.println("fuck d sku "+sku);
		this.sku = sku;
	}

	public String getProdDescr() {
		return this.prodDescr;
	}

	public void setProdDescr(String prodDescr) {
		this.prodDescr = prodDescr;
	}

	public Integer getFactoryPrice() {
		return this.factoryPrice;
	}

	public void setFactoryPrice(Integer factoryPrice) {
		this.factoryPrice = factoryPrice;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Double getLength() {
		return this.length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return this.width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Set getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Set getInventories() {
		return this.inventories;
	}

	public void setInventories(Set inventories) {
		this.inventories = inventories;
	}

	public Set getPackageDetails() {
		return this.packageDetails;
	}

	public void setPackageDetails(Set packageDetails) {
		this.packageDetails = packageDetails;
	}

	public Set getProdCates() {
		return this.prodCates;
	}

	public void setProdCates(Set prodCates) {
		this.prodCates = prodCates;
	}

	public void minPackage(){
		System.out.println(prodName+" has been splited to another package");
	}
	
	@Override
	public String toString() {
		return prodName;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product)) return false;
		Product p = (Product) obj;
		return p.prodId == this.prodId;
	}
	
	@Override
	public int hashCode() {
		long sum = this.prodId;
		
		for (int i = 0; i < this.sku.length(); i++) {
			sum = sum * 31 + sku.charAt(i);
		}
		
		for (int i = 0; i < this.prodName.length(); i++) {
			sum = sum * 31 + prodName.charAt(i);
		}
		
		return (int) sum;
	}
}
