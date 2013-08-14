package com.dicks.pojo;

/**
 * InventoryId entity. @author MyEclipse Persistence Tools
 */

public class InventoryId implements java.io.Serializable {

	// Fields

	private Integer prodId;
	private Integer storeId;

	// Constructors

	/** default constructor */
	public InventoryId() {
	}

	/** full constructor */
	public InventoryId(Integer prodId, Integer storeId) {
		this.prodId = prodId;
		this.storeId = storeId;
	}

	// Property accessors

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InventoryId))
			return false;
		InventoryId castOther = (InventoryId) other;

		return ((this.getProdId() == castOther.getProdId()) || (this
				.getProdId() != null
				&& castOther.getProdId() != null && this.getProdId().equals(
				castOther.getProdId())))
				&& ((this.getStoreId() == castOther.getStoreId()) || (this
						.getStoreId() != null
						&& castOther.getStoreId() != null && this.getStoreId()
						.equals(castOther.getStoreId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		return result;
	}

}