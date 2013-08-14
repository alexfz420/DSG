package com.dicks.pojo;

/**
 * ShipmentId entity. @author MyEclipse Persistence Tools
 */

public class ShipmentId implements java.io.Serializable {

	// Fields

	private Integer destinationZip;
	private Integer supplyZip;

	// Constructors

	/** default constructor */
	public ShipmentId() {
	}

	/** full constructor */
	public ShipmentId(Integer destinationZip, Integer supplyZip) {
		this.destinationZip = destinationZip;
		this.supplyZip = supplyZip;
	}

	// Property accessors

	public Integer getDestinationZip() {
		return this.destinationZip;
	}

	public void setDestinationZip(Integer destinationZip) {
		this.destinationZip = destinationZip;
	}

	public Integer getSupplyZip() {
		return this.supplyZip;
	}

	public void setSupplyZip(Integer supplyZip) {
		this.supplyZip = supplyZip;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ShipmentId))
			return false;
		ShipmentId castOther = (ShipmentId) other;

		return ((this.getDestinationZip() == castOther.getDestinationZip()) || (this
				.getDestinationZip() != null
				&& castOther.getDestinationZip() != null && this
				.getDestinationZip().equals(castOther.getDestinationZip())))
				&& ((this.getSupplyZip() == castOther.getSupplyZip()) || (this
						.getSupplyZip() != null
						&& castOther.getSupplyZip() != null && this
						.getSupplyZip().equals(castOther.getSupplyZip())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getDestinationZip() == null ? 0 : this.getDestinationZip()
						.hashCode());
		result = 37 * result
				+ (getSupplyZip() == null ? 0 : this.getSupplyZip().hashCode());
		return result;
	}

}