package com.dicks.dao;

import java.util.ArrayList;
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
}
