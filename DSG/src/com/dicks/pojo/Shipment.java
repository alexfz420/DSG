package com.dicks.pojo;

/**
 * Shipment entity. @author MyEclipse Persistence Tools
 */

public class Shipment implements java.io.Serializable {

	// Fields

	private ShipmentId id;
	private Integer normalRate;
	private Integer overSizeRate;
	private Integer overWeightRate;
	private Integer distance;

	// Constructors

	/** default constructor */
	public Shipment() {
	}

	/** full constructor */
	public Shipment(ShipmentId id, Integer normalRate, Integer overSizeRate,
			Integer overWeightRate) {
		this.id = id;
		this.normalRate = normalRate;
		this.overSizeRate = overSizeRate;
		this.overWeightRate = overWeightRate;
	}

	// Property accessors

	public ShipmentId getId() {
		return this.id;
	}

	public void setId(ShipmentId id) {
		this.id = id;
	}

	public Integer getNormalRate() {
		return this.normalRate;
	}

	public void setNormalRate(Integer normalRate) {
		this.normalRate = normalRate;
	}

	public Integer getOverSizeRate() {
		return this.overSizeRate;
	}

	public void setOverSizeRate(Integer overSizeRate) {
		this.overSizeRate = overSizeRate;
	}

	public Integer getOverWeightRate() {
		return this.overWeightRate;
	}

	public void setOverWeightRate(Integer overWeightRate) {
		this.overWeightRate = overWeightRate;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

}