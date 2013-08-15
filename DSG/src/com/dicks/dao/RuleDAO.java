package com.dicks.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Rule;

public class RuleDAO extends BaseDao<Rule> {
	private static RuleDAO instance = new RuleDAO();

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
		for(Rule rule:rules){
			String newObject = getNewProdObjForUpdate(rule.getObject(),skus);
			rule.setObject(newObject);
			super.update(rule);
		}		
	}
	
	private String getNewProdObjForUpdate(String obj, String[] skus){
		if(obj==null||"".equals(obj)){
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i<skus.length ; i++){
				sb.append(skus[i]).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			return sb.toString();
		}
		
		String[] objs = obj.split(",");
		List<String> strArray = (ArrayList<String>)Arrays.asList(objs);
		
		for(int i = 0 ; i<skus.length ; i++){
			String sku = skus[i];
			boolean flag = false;
			for(int j=0; j<objs.length;j++){
				if(objs[j].equals(sku)){}
				flag = true;
				break;
			}	
			if(!flag) strArray.add(sku);
		}

		StringBuffer sb = new StringBuffer();
		for(String str: strArray){
			sb.append(str).append(",");		
		}
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	private String getNewProdObjForDelete(String obj, String[] skus) {
		if(obj==null||"".equals(obj)) return null;
		
		String[] objs = obj.split(",");

		for(int i=0;i<skus.length;i++){
			String sku = skus[i];
			for(int j=0; j<objs.length;j++){
				if(objs[j].equals(sku)){
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
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();		
	}
}
