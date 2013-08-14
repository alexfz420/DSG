package com.dicks.pojo;

/**
 * LogId entity. @author MyEclipse Persistence Tools
 */

public class LogId implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Integer ruleId;

	// Constructors

	/** default constructor */
	public LogId() {
	}

	/** full constructor */
	public LogId(Integer orderId, Integer ruleId) {
		this.orderId = orderId;
		this.ruleId = ruleId;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LogId))
			return false;
		LogId castOther = (LogId) other;

		return ((this.getOrderId() == castOther.getOrderId()) || (this
				.getOrderId() != null
				&& castOther.getOrderId() != null && this.getOrderId().equals(
				castOther.getOrderId())))
				&& ((this.getRuleId() == castOther.getRuleId()) || (this
						.getRuleId() != null
						&& castOther.getRuleId() != null && this.getRuleId()
						.equals(castOther.getRuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result
				+ (getRuleId() == null ? 0 : this.getRuleId().hashCode());
		return result;
	}

}