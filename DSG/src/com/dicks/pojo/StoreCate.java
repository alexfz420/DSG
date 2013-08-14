package com.dicks.pojo;

/**
 * StoreCate entity. @author MyEclipse Persistence Tools
 */

public class StoreCate extends Category implements java.io.Serializable {

	// Fields

	private StoreCateId id;
	private Store store;

	// Constructors

	/** default constructor */
	public StoreCate() {
	}

	/** full constructor */
	public StoreCate(StoreCateId id, Store store, String cateName,
			String cateDescr) {
		this.id = id;
		this.store = store;
		super.setCateName(cateName);
		super.setCateDescr(cateDescr); 
	}

	// Property accessors

	public StoreCateId getId() {
		return this.id;
	}

	public void setId(StoreCateId id) {
		this.id = id;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}