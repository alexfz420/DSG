package com.dicks.pojo;

/**
 * Inventory entity. @author MyEclipse Persistence Tools
 */

public class Inventory implements java.io.Serializable {

	// Fields
	private InventoryId id;
	private Product product;
	private Store store;
	private Integer retailPrice;
	private Integer inventory;
	private Integer safetyStock;

	// Constructors

	/** default constructor */
	public Inventory() {
	}

	/** full constructor */
	public Inventory(InventoryId id, Product product, Store store,
			Integer retailPrice, Integer inventory, Integer safetyStock) {
		this.id = id;
		this.product = product;
		this.store = store;
		this.retailPrice = retailPrice;
		this.inventory = inventory;
		this.safetyStock = safetyStock;
	}

	// Property accessors

	public InventoryId getId() {
		return this.id;
	}

	public void setId(InventoryId id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(Integer retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSafetyStock() {
		return this.safetyStock;
	}

	public void setSafetyStock(Integer safetyStock) {
		this.safetyStock = safetyStock;
	}
	
	public int getMargin() {
		return this.getInventory() - this.getSafetyStock();
	}

}