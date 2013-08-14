package com.dicks.pojo;

/**
 * ProdCateId entity. @author MyEclipse Persistence Tools
 */

public class ProdCateId implements java.io.Serializable {

	// Fields

	private Integer cateProdId;
	private Integer prodId;

	// Constructors

	/** default constructor */
	public ProdCateId() {
	}

	/** full constructor */
	public ProdCateId(Integer cateProdId, Integer prodId) {
		this.cateProdId = cateProdId;
		this.prodId = prodId;
	}

	// Property accessors

	public Integer getCateProdId() {
		return this.cateProdId;
	}

	public void setCateProdId(Integer cateProdId) {
		this.cateProdId = cateProdId;
	}

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProdCateId))
			return false;
		ProdCateId castOther = (ProdCateId) other;

		return ((this.getCateProdId() == castOther.getCateProdId()) || (this
				.getCateProdId() != null
				&& castOther.getCateProdId() != null && this.getCateProdId()
				.equals(castOther.getCateProdId())))
				&& ((this.getProdId() == castOther.getProdId()) || (this
						.getProdId() != null
						&& castOther.getProdId() != null && this.getProdId()
						.equals(castOther.getProdId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCateProdId() == null ? 0 : this.getCateProdId()
						.hashCode());
		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		return result;
	}

}