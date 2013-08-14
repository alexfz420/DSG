package com.dicks.pojo;

/**
 * OrderDetail entity. @author MyEclipse Persistence Tools
 */

public class OrderDetail implements java.io.Serializable {

	// Fields

	private OrderDetailId id;
	private Product product;
	private Orders orders;
	private Integer unitPrice;
	private Integer qty;

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** full constructor */
	public OrderDetail(OrderDetailId id, Product product, Orders orders,
			Integer unitPrice, Integer qty) {
		this.id = id;
		this.product = product;
		this.orders = orders;
		this.unitPrice = unitPrice;
		this.qty = qty;
	}

	// Property accessors

	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Integer getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
