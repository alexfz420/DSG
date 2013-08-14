package com.dicks.pojo;

/**
 * RuleCateId entity. @author MyEclipse Persistence Tools
 */

public class RuleCateId implements java.io.Serializable {

	// Fields

	private Integer categoryId;
	private Integer ruleId;

	// Constructors

	/** default constructor */
	public RuleCateId() {
	}

	/** full constructor */
	public RuleCateId(Integer categoryId, Integer ruleId) {
		this.categoryId = categoryId;
		this.ruleId = ruleId;
	}

	// Property accessors

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
		if (!(other instanceof RuleCateId))
			return false;
		RuleCateId castOther = (RuleCateId) other;

		return ((this.getCategoryId() == castOther.getCategoryId()) || (this
				.getCategoryId() != null
				&& castOther.getCategoryId() != null && this.getCategoryId()
				.equals(castOther.getCategoryId())))
				&& ((this.getRuleId() == castOther.getRuleId()) || (this
						.getRuleId() != null
						&& castOther.getRuleId() != null && this.getRuleId()
						.equals(castOther.getRuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCategoryId() == null ? 0 : this.getCategoryId()
						.hashCode());
		result = 37 * result
				+ (getRuleId() == null ? 0 : this.getRuleId().hashCode());
		return result;
	}

}