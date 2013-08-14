package com.dicks.pojo;

/**
 * PackageDetailId entity. @author MyEclipse Persistence Tools
 */

public class PackageDetailId implements java.io.Serializable {

	// Fields

	private Integer packageId;
	private Integer prodId;

	// Constructors

	/** default constructor */
	public PackageDetailId() {
	}

	/** full constructor */
	public PackageDetailId(Integer packageId, Integer prodId) {
		this.packageId = packageId;
		this.prodId = prodId;
	}

	// Property accessors

	public Integer getPackageId() {
		return this.packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
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
		if (!(other instanceof PackageDetailId))
			return false;
		PackageDetailId castOther = (PackageDetailId) other;

		return ((this.getPackageId() == castOther.getPackageId()) || (this
				.getPackageId() != null
				&& castOther.getPackageId() != null && this.getPackageId()
				.equals(castOther.getPackageId())))
				&& ((this.getProdId() == castOther.getProdId()) || (this
						.getProdId() != null
						&& castOther.getProdId() != null && this.getProdId()
						.equals(castOther.getProdId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPackageId() == null ? 0 : this.getPackageId().hashCode());
		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		return result;
	}

}