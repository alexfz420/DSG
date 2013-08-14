package com.dicks.pojo;

/**
 * OrderDetailId entity. @author MyEclipse Persistence Tools
 */

public class OrderDetailId implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Integer prodId;

	// Constructors

	/** default constructor */
	public OrderDetailId() {
	}

	/** full constructor */
	public OrderDetailId(Integer orderId, Integer prodId) {
		this.orderId = orderId;
		this.prodId = prodId;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
		if (!(other instanceof OrderDetailId))
			return false;
		OrderDetailId castOther = (OrderDetailId) other;

		return ((this.getOrderId() == castOther.getOrderId()) || (this
				.getOrderId() != null
				&& castOther.getOrderId() != null && this.getOrderId().equals(
				castOther.getOrderId())))
				&& ((this.getProdId() == castOther.getProdId()) || (this
						.getProdId() != null
						&& castOther.getProdId() != null && this.getProdId()
						.equals(castOther.getProdId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		return result;
	}

}