package com.dicks.pojo;

/**
 * Fee entity. @author MyEclipse Persistence Tools
 */

public class Fee implements java.io.Serializable {

	// Fields
	private Integer costId;
	private String shippingType;
	private String costName;
	private String flag;
	private Integer percentage;
	private String attribute;
	private Integer value;

	// Constructors

	/** default constructor */
	public Fee() {
	}

	/** minimal constructor */
	public Fee(String shippingType, String costName, String flag) {
		this.shippingType = shippingType;
		this.costName = costName;
		this.flag = flag;
	}

	/** full constructor */
	public Fee(String shippingType, String costName, String flag,
			Integer percentage, String attribute, Integer value) {
		this.shippingType = shippingType;
		this.costName = costName;
		this.flag = flag;
		this.percentage = percentage;
		this.attribute = attribute;
		this.value = value;
	}
	
	/** for percentage constructor */
	public Fee(String costName, String shippingType, int percentage, String attribute) {
		this.costName = costName;
		this.shippingType = shippingType;
		this.percentage = percentage;
		this.attribute = attribute;
		this.flag = "p";
	}


	public Fee(String costName, String shippingType, int value) {
		this.costName = costName;
		this.shippingType = shippingType;
		this.value = value;
		this.flag = "v";
	}
	
	// Property accessors

	public Integer getCostId() {
		return this.costId;
	}

	public void setCostId(Integer costId) {
		this.costId = costId;
	}

	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getCostName() {
		return this.costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
