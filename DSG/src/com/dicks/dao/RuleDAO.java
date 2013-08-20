package com.dicks.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Rule;

public class RuleDAO extends BaseDao<Rule> {
	private static RuleDAO instance = new RuleDAO();
	private static Map<String, String> dictionary = null;

	public RuleDAO() {
		super(Rule.class);
	}

	public static RuleDAO getInstance() {
		return instance;
	}

	public Rule[] getAllSortedListFromStageOne() throws Exception {
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("stage", "1");
		criterions.add(criterion);	
		ArrayList<Rule> ruleList = (ArrayList<Rule>) super.getList(criterions);
		Collections.sort(ruleList, new Comparator<Rule>() {
			public int compare(Rule o1, Rule o2) {
				return o2.getPriority() - o1.getPriority();
			}
		});
		Rule[] array = (Rule[]) ruleList.toArray(new Rule[ruleList.size()]);
		return array;
	}
	
	

	public Rule[] getAllRuleList() throws Exception{
		ArrayList<Rule> ruleList = (ArrayList<Rule>) super.getList();
		Collections.sort(ruleList, new Comparator<Rule>() {
			public int compare(Rule o1, Rule o2) {
				return o2.getPriority() - o1.getPriority();
			}
		});
		return (Rule[]) ruleList.toArray(new Rule[ruleList.size()]);
	}
	
	public Rule getRuleById(String ruleId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("ruleId", Integer.valueOf(ruleId));
		criterions.add(criterion);
		return super.get(criterions);
	}
	
	public ArrayList<Rule> getRuleByType(String type) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("type", type);
		criterions.add(criterion);
		return (ArrayList<Rule>) super.getList(criterions);
	}
	
	public Rule getRuleByName(String ruleName) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("ruleName", ruleName);
		criterions.add(criterion);
		return super.get(criterions);
	}
	
	public void createRule(Rule rule) throws Exception {
		super.create(rule);
	}

	public void updatePriorities(Rule[] ruleArray) throws Exception{
		for(Rule rule : ruleArray){
			super.update(rule);
		}
	}
	
	public void update(Rule rule) throws Exception{
		super.update(rule);
	}
	
	public void updateProdObjForUpdate(String[] skus, List<Rule> rules) throws Exception {
		if(rules==null||rules.size() == 0) return;
		for(Rule rule:rules){
			String newObject = getNewProdObjForUpdate(rule.getObject(),skus);
			rule.setObject(newObject);
			super.update(rule);
		}		
	}
	
	public void updateProdObjForDelete(String[] skus, List<Rule> rules) throws Exception {

		if(rules==null||rules.size() == 0) return;
		System.out.println("3");
		for(Rule rule:rules){
			String newObject = getNewProdObjForDelete(rule.getObject(),skus);
			rule.setObject(newObject);
			super.update(rule);	
		}		
	}
	
	public String getNewProdObjForUpdate(String obj, String[] skus){
		if(obj==null||"".equals(obj)){
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i<skus.length ; i++){
				sb.append(skus[i]).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			return sb.toString();
		}
		
		String[] objs = obj.split(",");
		List<String> strArray = new ArrayList<String>(Arrays.asList(objs));
		
		for(int i = 0 ; i<skus.length ; i++){
			String sku = skus[i];
			boolean flag = false;
			for(int j=0; j<objs.length; j++){
				if(objs[j].equals(sku)){
					flag = true;
					break;
				}
			}	
			if(!flag) {
				strArray.add(sku);
			}
		}

		StringBuffer sb = new StringBuffer();
		for(String str: strArray){
			sb.append(str).append(",");		
		}
		
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	public String getNewProdObjForDelete(String obj, String[] skus) {
		if(obj==null||obj.trim().length() ==0) return null;
		
		String[] objs = obj.split(",");

		for(int i=0;i<skus.length;i++){
			String sku = skus[i];
			for(int j=0; j<objs.length;j++){
				if(sku.equals(objs[j])){
					objs[j]=null;
					break;
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<objs.length;i++){
			if(objs[i]!=null){
				sb.append(objs[i]).append(",");
			}
		}
		
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
			return sb.toString();
		}
		return "";			
	}
	
	public String[] getDescriptionByRule(Rule rule) throws Exception{
		//map
		buildDictionary();
		
//		List<Criterion> criterions = new ArrayList<Criterion>();
//		Criterion criterion = Restrictions.eq("ruleId", ruleId);
//		criterions.add(criterion);
//		Rule rule  =  super.get(criterions);
		String type = rule.getType();
		
		StringBuffer sbWhen = new StringBuffer();
		StringBuffer sbThen = new StringBuffer();
		String[] result = new String[2];

		
		String[] attributes = rule.getAttributes();
		String[] operators =  rule.getOperators();
		String condition = dictionary.get(rule.getCondition());
		String[] values = rule.getValues();
		String[] actions = rule.getActions();
		String route = rule.getRoute();
		
		
		if("1".equals(type)){
			sbWhen.append("when ");
			for(int i = 0 ; i<attributes.length ; i ++){
				sbWhen.append(attributes[i]).append(" ").append(dictionary.get(operators[i])).append(" ").append(values[i]).append(", ").append(condition).append(" ");
			}
			sbWhen.delete(sbWhen.length()-5,sbWhen.length()-1) ;
			
			sbThen.append("then ");
			for(int i = 0; i<actions.length ; i++){
				sbThen.append(dictionary.get(actions[i])).append(", ");
			}
			sbThen.delete(sbThen.length()-2,sbThen.length()-1) ;
		}if("3".equals(type)){
			sbWhen.append("when this item's quantity ");
			sbWhen.append(dictionary.get(operators[0])).append(" ").append(values[0]);
			sbThen.append("then ").append(dictionary.get(actions[0])).append(" Store ").append(route);
			
		}
		if("3".equals(type)){
			
		}
		
		result[0] = sbWhen.toString();
		result[1] = sbThen.toString();
		return result;
	}
	
	private static void buildDictionary(){
		if(dictionary!=null) return;
		dictionary = new HashMap<String, String>();
		dictionary.put(">","is bigger than");
		dictionary.put("<","is less than");
		dictionary.put("<","equals to");
		dictionary.put("miniumPackage","this package will be splitted");
		dictionary.put("||","or");
		dictionary.put("&&","and");
		dictionary.put("retract","this item will be filted out");
		dictionary.put("special", "this item will be directly shipped from");
		dictionary.put("miniumPackage", "this item will be filted out");
	}
	
	public List<String> getRuleNamesForProduct() throws Exception{
		List<Criterion> criterion = new ArrayList<Criterion>();
		Disjunction disjunctions = Restrictions.disjunction();
		disjunctions.add(Restrictions.eq("type", "1" ));
		disjunctions.add(Restrictions.eq("type", "3" ));
		criterion.add(disjunctions);
		List<Rule> list= (ArrayList<Rule>) super.getList(criterion);
		if(list ==null) return null;
		
		List<String> result = new ArrayList<String>();		
		for(Rule rule: list){
			result.add(rule.getRuleName());
		}
		return result;
	}
	
	public List<String> getRuleNamesForStore() throws Exception{
		List<Criterion> criterion = new ArrayList<Criterion>();
		Disjunction disjunctions = Restrictions.disjunction();
		disjunctions.add(Restrictions.eq("type", "2" ));
		disjunctions.add(Restrictions.eq("type", "4" ));
		criterion.add(disjunctions);
		List<Rule> list= (ArrayList<Rule>) super.getList(criterion);
		if(list ==null) return null;
		
		List<String> result = new ArrayList<String>();		
		for(Rule rule: list){
			result.add(rule.getRuleName());
		}
		return result;
	}

	
}
