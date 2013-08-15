package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.OrdersDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Rule;
import com.dicks.engine.ReMakeTemplate;
import com.dicks.engine.WriteDrl;

public class RulePriority {
	public String[] ruleList;
	public Rule[] allRule;
	public String ruleString;
	private String rulename;
	public Rule[] preRule;
	public Rule[] midRule;
	public Rule[] lastRule;
	
	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}
	public String getRuleString(){
		return ruleString;
	}
	
	public void setRuleString(String ruleString){
		this.ruleString = ruleString;
	}
	public String gotoruleprioritylist(){
		
		int pre = 0;
		int mid = 0;
		int last = 0;
		try {
			allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println("rule Length!!!"+allRule.length);
		
		for (int i = 0; i<allRule.length;i++){
			if (allRule[i].getType().equals("9")){
				if (allRule[i].getPriority()>0){
					pre++;
				}
				else{
					last++;
				}
			}
		}
		mid = allRule.length-pre-last;
		//System.out.println("all rule number is "+allRule.length);
		//System.out.println("pre rule number is "+pre);
		//System.out.println("mid rule number is "+mid);
		//System.out.println("last rule number is "+last);
		preRule = new Rule[pre];
		midRule = new Rule[mid];
		lastRule = new Rule[last];
		for (int i = 0; i<pre;i++){
			preRule[i] = allRule[i];
			//System.out.println("Pre Rule"+allRule[i].getRuleName());
		}
		for (int i = pre,j=0;i<(pre+mid);i++,j++){
			midRule[j] = allRule[i];
			//System.out.println("Mid Rule"+allRule[i].getRuleName());
		}
		for (int i = (pre+mid),j=0; i < allRule.length; i++,j++){
			lastRule[j] = allRule[i];
			//System.out.println("Last Rule"+allRule[i].getRuleName());
		}
		
		
		return "success";
	}
	public String reRank(){
		//System.out.println("!!!!!!!!!!!"+ruleString);
		int pre = 0;
		int mid = 0;
		int last = 0;
		try {
			allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println("rule Length!!!"+allRule.length);
		Rule[] preRule = null;
		for (int i = 0; i<allRule.length;i++){
			if (allRule[i].getType().equals("9")){
				if (allRule[i].getPriority()>0){
					pre++;
				}
				else{
					last++;
				}
			}
		}
		mid = allRule.length-pre-last;
		//System.out.println("all rule number is "+allRule.length);
		//System.out.println("pre rule number is "+pre);
		//System.out.println("mid rule number is "+mid);
		//System.out.println("last rule number is "+last);
		String[] ruleStringList = ruleString.split(",");
		//System.out.println("hahahahah"+ruleString);
		
		Rule[] pRule = new Rule[allRule.length];
		for (int i = 0;i<pre;i++){
			pRule[i] = allRule[i];
		}
		
		for (int i = pre, j = 0;i<(pre+mid);i++,j++){
			pRule[i] = findRule(allRule,ruleStringList[j]);
			//System.out.println("pre priority"+pRule[i].getPriority());
			//System.out.println("computing priority"+pRule[i-1].getPriority());
			pRule[i].setPriority(pRule[i-1].getPriority()-2);
			//System.out.println("after priority"+pRule[i].getPriority());
			//System.out.println("first new Rule"+ruleStringList[j]);
		}
		
		for (int i = (mid+pre);i<allRule.length;i++){
			pRule[i] = allRule[i];
		}
		
		for (int i = 0;i<pRule.length;i++){
			//System.out.println("rule "+i+" "+pRule[i].getRuleName());
		}
		try {
			RuleDAO.getInstance().updatePriorities(pRule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		WriteDrl rmk = new WriteDrl();
		return "success";
	}
	
	public Rule findRule(Rule[] rule, String find){
		Rule thisRule = new Rule();
		for (int i = 0; i<rule.length; i++){
			if (rule[i].getRuleName().equalsIgnoreCase(find)){
				thisRule = rule[i];
			}
		}
		return thisRule;
	}
}
