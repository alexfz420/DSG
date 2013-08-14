package com.dicks.pojo;

/**
 * StoreCateId entity. @author MyEclipse Persistence Tools
 */

public class StoreCateId implements java.io.Serializable {

	// Fields

	private Integer cateStoreId;
	private Integer storeId;

	// Constructors

	/** default constructor */
	public StoreCateId() {
	}

	/** full constructor */
	public StoreCateId(Integer cateStoreId, Integer storeId) {
		this.cateStoreId = cateStoreId;
		this.storeId = storeId;
	}

	// Property accessors

	public Integer getCateStoreId() {
		return this.cateStoreId;
	}

	public void setCateStoreId(Integer cateStoreId) {
		this.cateStoreId = cateStoreId;
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
		if (!(other instanceof StoreCateId))
			return false;
		StoreCateId castOther = (StoreCateId) other;

		return ((this.getCateStoreId() == castOther.getCateStoreId()) || (this
				.getCateStoreId() != null
				&& castOther.getCateStoreId() != null && this.getCateStoreId()
				.equals(castOther.getCateStoreId())))
				&& ((this.getStoreId() == castOther.getStoreId()) || (this
						.getStoreId() != null
						&& castOther.getStoreId() != null && this.getStoreId()
						.equals(castOther.getStoreId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCateStoreId() == null ? 0 : this.getCateStoreId()
						.hashCode());
		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		return result;
	}

}