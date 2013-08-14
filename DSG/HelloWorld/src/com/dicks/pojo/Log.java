package com.dicks.pojo;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	// Fields

	private LogId id;
	private Rule rule;
	private Orders orders;
	private Integer stage;
	private String record;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** minimal constructor */
	public Log(LogId id, Rule rule, Orders orders, Integer stage) {
		this.id = id;
		this.rule = rule;
		this.orders = orders;
		this.stage = stage;
	}

	/** full constructor */
	public Log(LogId id, Rule rule, Orders orders, Integer stage, String record) {
		this.id = id;
		this.rule = rule;
		this.orders = orders;
		this.stage = stage;
		this.record = record;
	}

	// Property accessors

	public LogId getId() {
		return this.id;
	}

	public void setId(LogId id) {
		this.id = id;
	}

	public Rule getRule() {
		return this.rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Integer getStage() {
		return this.stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

}