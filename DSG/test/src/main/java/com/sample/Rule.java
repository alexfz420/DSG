package com.sample;

public class Rule {

	private int priority;
	private String group;
	private String path;
	private int ruleID;
	private String description;
	private String type;
	private String object;
	private String attribute;
	private String operator;
	private String value;
	private String route;
	private String action;
	private String flag;
	private String condition;

	public Rule(){

	}

	public Rule(int ruleID, String path, String description, int priority, String group ){
		this.ruleID = ruleID;
		this.path = path;
		this.description = description;
		this.priority = priority;
		this.group = group;
	}

	public Rule(int ruleID, String path, String description, int priority){
		this.ruleID = ruleID;
		this.path = path;
		this.description = description;
		this.priority = priority;
		this.group = group;
	}


	public Rule(int ruleID, String path, String description, int priority, String type, String[] objects, 
			String[] attributes, String[] operators, String[] values, String condition, String routes, String[] actions,String flag){
		StringBuffer objectBuffer = new StringBuffer();
		StringBuffer attributeBuffer = new StringBuffer();
		StringBuffer operatorBuffer = new StringBuffer();
		StringBuffer valueBuffer = new StringBuffer();
		StringBuffer actionBuffer = new StringBuffer();
		StringBuffer routeBuffer = new StringBuffer();



		this.condition = condition;
		this.type = type;
		this.ruleID = ruleID;
		this.path = path;
		this.description = description;
		this.priority = priority;
		this.group = "";
		this.flag = flag;

		if (type.equalsIgnoreCase("Threshold")){
			this.object = translate(objectBuffer, objects);
			this.attribute = translate(attributeBuffer, attributes);
			this.operator = translate(operatorBuffer, operators);
			this.value = translate(valueBuffer, values);
			this.action = translate(actionBuffer, actions);
		}
		else if (type.equalsIgnoreCase("Special Route")){
			this.object = translate(objectBuffer, objects);
			this.attribute = translate(attributeBuffer, attributes);
			this.operator = translate(operatorBuffer, operators);
			this.value = translate(valueBuffer, values);
			this.action = translate(actionBuffer, actions);
			this.route = routes;
			System.out.println("passed in"+routes);
			
		}
		else if (type.equalsIgnoreCase("Store Filter")){
			this.object = translate(objectBuffer, objects);
			this.attribute = translate(attributeBuffer, attributes);
			this.operator = translate(operatorBuffer, operators);
			this.value = translate(valueBuffer, values);
			this.action = translate(actionBuffer, actions);
		}
		else{
			System.out.println("Error, Invalid Rule Type Added");
			System.exit(0);
		}
	}

	public Rule(int ruleID, String path, String description){
		this.ruleID = ruleID;
		this.path = path;
		this.description = description;
		this.priority = 0;
		this.group = "";
	}

	public String getCondition(){
		return condition;
	}

	public void setConditon(String condition){
		this.condition = condition;
	}
	public String getFlag(){
		return flag;
	}

	public void setFlag(String flag){
		this.flag = flag;
	}

	public int getRuleID(){
		return ruleID;
	}

	public int getPriority(){
		return priority;
	}

	public String getType(){
		return type;

	}

	public String[] getObject(){
		return translateBack(object);
	}

	public String[] getAttribute(){
		return translateBack(attribute);
	}

	public String[] getOperator(){
		return translateBack(operator);
	}

	public String[] getValue(){
		return translateBack(value);
	}

	public String getRoute(){
		return route;
	}

	public String[] getAction(){
		return translateBack(action);
	}

	public String getPath(){
		return path;
	}

	public String getDescription(){
		return description;
	}

	public String getGroup(){
		return group;
	}

	public void setType(String type){
		this.type = type;
	}


	public void setRuleID(int ruleID){
		this.ruleID = ruleID;
	}

	public void setPriority(int priority){

			this.priority = priority;

	}

	public void setPath(String path){
		this.path = path;
	}

	public void setDescription(String description){
		this.description = description;
	}
	public void setGroup(String group){
		this.group = group;
	}

	public void setObject(String[] object){
		StringBuffer objectBuffer = new StringBuffer();
		this.object = translate(objectBuffer, object);
	}

	public void setAttribute(String[] attribute){
		StringBuffer attributeBuffer = new StringBuffer();
		this.attribute = translate(attributeBuffer, attribute);
	}

	public void setOperator(String[] operator){
		StringBuffer operatorBuffer = new StringBuffer();
		this.operator = translate(operatorBuffer, operator);
	}

	public void setValue(String[] value){
		StringBuffer valueBuffer = new StringBuffer();
		this.value = translate(valueBuffer, value);
	}

	public void setRoute(String[] route){
		StringBuffer routeBuffer = new StringBuffer();
		this.route = translate(routeBuffer, route);
	}

	public void setAction(String[] action){
		StringBuffer actionBuffer = new StringBuffer();
		this.action = translate(actionBuffer, action);
	}

	public String translate (StringBuffer s1, String[] s2){

		s1.append(s2[0]);
		for (int i = 1; i < s2.length; i++){
				s1.append(",");
				s1.append(s2[i]);
		}
		return s1.toString();

	}

	public String[] translateBack(String s1){
		String[] s2 = s1.split(",");

		return s2;
	}



}