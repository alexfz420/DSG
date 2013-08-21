package com.dicks.action;

import com.dicks.dao.RuleDAO;
import com.dicks.pojo.Rule;

public class ResultRankingAction {
	private String ruleName;
	private String ruleDescription;
	private String rankOption;
	private String minOption;
	private String maxOption;
	private String ruleId;

	
	public String resultRanking() throws Exception{
		Rule rule = RuleDAO.getInstance().getRuleById(ruleId);
		System.out.println("%%%%%%%%%%%");
		rule.setRuleName(this.ruleName);
		rule.setRuleDescr(this.ruleDescription);
		rule.setOperator(this.rankOption);
		if (rankOption.equals("max")) {
			rule.setAttribute(maxOption);
			System.out.println("action: " + rankOption + " " + maxOption);
		} else if (rankOption.equals("min")) {
			rule.setAttribute(minOption);
			System.out.println("action: " + rankOption + " " + minOption);
		}
		
		RuleDAO.getInstance().update(rule);
		
		return "success";
	}
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getRankOption() {
		return rankOption;
	}

	public void setRankOption(String rankOption) {
		this.rankOption = rankOption;
	}

	public String getMinOption() {
		return minOption;
	}

	public void setMinOption(String minOption) {
		this.minOption = minOption;
	}

	public String getMaxOption() {
		return maxOption;
	}

	public void setMaxOption(String maxOption) {
		this.maxOption = maxOption;
	}
}
