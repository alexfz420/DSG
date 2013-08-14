package com.dicks.pojo;

/**
 * RuleCate entity. @author MyEclipse Persistence Tools
 */

public class RuleCate implements java.io.Serializable {

	// Fields

	private RuleCateId id;
	private Rule rule;
	private Boolean isProductRelated;

	// Constructors

	/** default constructor */
	public RuleCate() {
	}

	/** full constructor */
	public RuleCate(RuleCateId id, Rule rule, Boolean isProductRelated) {
		this.id = id;
		this.rule = rule;
		this.isProductRelated = isProductRelated;
	}

	// Property accessors

	public RuleCateId getId() {
		return this.id;
	}

	public void setId(RuleCateId id) {
		this.id = id;
	}

	public Rule getRule() {
		return this.rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public Boolean getIsProductRelated() {
		return this.isProductRelated;
	}

	public void setIsProductRelated(Boolean isProductRelated) {
		this.isProductRelated = isProductRelated;
	}

}