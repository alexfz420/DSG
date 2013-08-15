package com.dicks.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule entity. @author MyEclipse Persistence Tools
 */

public class Rule implements java.io.Serializable {

	// Fields

	private Integer ruleId;
	private String ruleName;
	private String ruleUrl;
	private String ruleDescr;
	private Integer priority;
	private String flag;
	private String type;
	private String stage;
	private String object;
	private String attribute;
	private String operator;
	private String value;
	private String route;
	private String action;
	private String condition;
	private boolean isSpecial;
	private boolean able;
	private Set ruleCates = new HashSet(0);
	private Set logs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Rule() {
	}
	
	

	public Rule(String ruleName, String ruleUrl, String ruleDescr,
			Integer priority, String flag, String type, String stage,
			String object, String attribute, String operator, String value,
			String route, String action, String condition, boolean isSpecial) {
		super();
		this.ruleName = ruleName;
		this.ruleUrl = ruleUrl;
		this.ruleDescr = ruleDescr;
		this.priority = priority;
		this.flag = flag;
		this.type = type;
		this.stage = stage;
		this.object = object;
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
		this.route = route;
		this.action = action;
		this.condition = condition;
		this.isSpecial = isSpecial;
	}



	/** minimal constructor */
	public Rule(String ruleName, String ruleUrl, String ruleDescr,
			Integer priority, String flag, String type, String stage,
			String object, String attribute, String operator, String value,
			String route, String action) {
		this.ruleName = ruleName;
		this.ruleUrl = ruleUrl;
		this.ruleDescr = ruleDescr;
		this.priority = priority;
		this.flag = flag;
		this.type = type;
		this.stage = stage;
		this.object = object;
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
		this.route = route;
		this.action = action;
	}

	/** full constructor */
	public Rule(String ruleName, String ruleUrl, String ruleDescr,
			Integer priority, String flag, String type, String stage,
			String object, String attribute, String operator, String value,
			String route, String action, String condition) {
		this.ruleName = ruleName;
		this.ruleUrl = ruleUrl;
		this.ruleDescr = ruleDescr;
		this.priority = priority;
		this.flag = flag;
		this.type = type;
		this.stage = stage;
		this.object = object;
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
		this.route = route;
		this.action = action;
		this.condition = condition;
	}
	
	//Joe Constructor
	public Rule(String ruleName, String ruleUrl, String ruleDescr, int priority){
		this.ruleName = ruleName;
		this.ruleUrl = ruleUrl;
		this.ruleDescr = ruleDescr;
		this.priority = priority;
	}
	
	public Rule(String ruleName, String ruleUrl, String ruleDescr){
		this.ruleName = ruleName;
		this.ruleUrl = ruleUrl;
		this.ruleDescr = ruleDescr;
	}

	public Rule(String ruleName, String ruleUrl, String ruleDescr, int priority, String type, String[] objects, 
			String[] attributes, String[] operators, String[] values, String condition, String[] routes, 

			String[] actions,String flag, String stage, boolean isSpecial){


		StringBuffer objectBuffer = new StringBuffer();
		StringBuffer attributeBuffer = new StringBuffer();
		StringBuffer operatorBuffer = new StringBuffer();
		StringBuffer valueBuffer = new StringBuffer();
		StringBuffer actionBuffer = new StringBuffer();
		StringBuffer routeBuffer = new StringBuffer();

		this.ruleName = ruleName;
		this.ruleUrl = ruleUrl;
		this.ruleDescr = ruleDescr;
		this.priority = priority;
		this.flag = flag;
		this.type = type;
		this.stage = stage;
		this.condition = condition;

		this.isSpecial = isSpecial;
		if (type.equalsIgnoreCase("1")){
			this.object = translate(objectBuffer, objects);
			this.attribute = translate(attributeBuffer, attributes);
			this.operator = translate(operatorBuffer, operators);
			this.value = translate(valueBuffer, values);
			this.action = translate(actionBuffer, actions);
			this.route = translate(routeBuffer,routes);
		}
		else if (type.equalsIgnoreCase("2")){
			this.object = translate(objectBuffer, objects);
			this.attribute = translate(attributeBuffer, attributes);
			this.operator = translate(operatorBuffer, operators);
			this.value = translate(valueBuffer, values);
			this.action = translate(actionBuffer, actions);
			this.route =translate(routeBuffer, routes);
		}
		else if (type.equalsIgnoreCase("3")){
			this.object = translate(objectBuffer, objects);
			this.attribute = translate(attributeBuffer, attributes);
			this.operator = translate(operatorBuffer, operators);
			this.value = translate(valueBuffer, values);
			this.action = translate(actionBuffer, actions);
			this.route =translate(routeBuffer, routes);
		}
		else{
			System.out.println("Error, Invalid Rule Type Added");
			System.exit(0);
		}		
	}

	// Property accessors

	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleUrl() {
		return this.ruleUrl;
	}

	public void setRuleUrl(String ruleUrl) {
		this.ruleUrl = ruleUrl;
	}

	public String getRuleDescr() {
		return this.ruleDescr;
	}

	public void setRuleDescr(String ruleDescr) {
		this.ruleDescr = ruleDescr;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Set getRuleCates() {
		return this.ruleCates;
	}

	public void setRuleCates(Set ruleCates) {
		this.ruleCates = ruleCates;
	}

	public boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String translate (StringBuffer s1, String[] s2){

		s1.append(s2[0]);
		for (int i = 1; i < s2.length; i++){
				s1.append(",");
				s1.append(s2[i]);
		}
		//System.out.println("translated"+s1.toString());
		return s1.toString();

	}

	public String[] translateBack(String s1){
		String[] s2 = s1.split(",");
		//System.out.println("translated back");
		for (int i = 0;i<s2.length;i++){
			//System.out.print(s2[i]);
		}
		//System.out.println();
		return s2;
	}	

	public String[] getObjects(){
		return translateBack(object);
	}
	
	public void setObjects(String[] objects){
		StringBuffer objectBuffer = new StringBuffer();
		this.object = translate(objectBuffer, objects);
	}
	
	public void setAttributes(String[] attributes){
		StringBuffer attributeBuffer = new StringBuffer();
		this.attribute = translate(attributeBuffer, attributes);
	}
	
	public void setValues(String[] values){
		StringBuffer valueBuffer = new StringBuffer();
		this.value = translate(valueBuffer, values);
	}
	
	public void setOperators(String[] operators){
		StringBuffer operatorBuffer = new StringBuffer();
		this.operator = translate(operatorBuffer, operators);
	}
	
	public void setRoutes(String[] routes){
		StringBuffer routeBuffer = new StringBuffer();
		this.route = translate(routeBuffer, routes);
	}
	
	public void setActions(String[] actions){
		StringBuffer actionBuffer = new StringBuffer();
		this.value = translate(actionBuffer, actions);
	}

	public String[] getAttributes(){
		return translateBack(attribute);
	}

	public String[] getOperators(){
		return translateBack(operator);
	}

	public String[] getValues(){
		return translateBack(value);
	}

	public String[] getRoutes(){
		return translateBack(route);
	}

	public String[] getActions(){
		return translateBack(action);
	}



	public Set getLogs() {
		return logs;
	}



	public void setLogs(Set logs) {
		this.logs = logs;
	}
	
	public String printReport(){
		StringBuffer ruleReport = new StringBuffer();
		
		return ruleReport.toString();
	}



	public boolean isAble() {
		return able;
	}



	public void setAble(boolean able) {
		this.able = able;
	}
}
