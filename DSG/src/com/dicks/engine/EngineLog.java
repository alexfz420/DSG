package com.dicks.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.pojo.Log;
import com.dicks.pojo.Rule;

public class EngineLog {
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();	
	private int stage;
	
	public EngineLog(ArrayList<Log> logs) throws Exception {
		RuleDAO ruleDAO = RuleDAO.getInstance();
		
		for (Log log : logs) {
			Rule rule = ruleDAO.getRuleById(log.getRule().getRuleId()+ "");
			String[] strings = log.getRecord().split(",");
			for (String string : strings) {
				this.addLog(rule.getRuleName(), string);
			}
		}
	}
	
	public EngineLog(int stage) {
		this.stage = stage;
	}
	
	public HashMap<String, ArrayList<String>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, ArrayList<String>> map) {
		this.map = map;
	}
	
	public void addLog(String rulename, String log) {
		ArrayList<String> logs = map.get(rulename);
		if (logs == null) {
			ArrayList<String> newLogs = new ArrayList<String>();
			newLogs.add(log);
			map.put(rulename, newLogs); 
		} else {
			logs.add(log);
		}
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
	
	public Set<String> getRuleNames() {	
		return map.keySet();
	}
	
	public ArrayList<String> getLogsByName(String name) {
		System.out.println("name: " + name);
		return map.get(name);
	}
	
	public ArrayList<LogE> getLogs() throws Exception {
		ArrayList<LogE> logs = new ArrayList<LogE>();
		int count = 0;
		for (String name : map.keySet()) {
			LogE log = new LogE(name, map.get(name));

			//System.out.println("rule name:" + name);
			Rule rule = RuleDAO.getInstance().getRuleByName(name);
			//System.out.println("rule id:" + rule.getRuleId());
			String[] categories = RuleCateDAO.getInstance().getCateNamesByRuleId(rule.getRuleId()+"");
			if (rule.getType().equals("9")) log.setCategories("All");
			else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < categories.length; i++) {
					sb.append(categories[i]);
					if (i != categories.length - 1) {
						sb.append(", ");
					}
				}
				//System.out.println("categories: " + Arrays.toString(categories));
				log.setCategories(sb.toString());	
			}				
			String[] strings = RuleDAO.getInstance().getDescriptionByRule(rule);
			//System.out.println("conditions: " + strings[0]);
			//System.out.println("actions: " + strings[1]);
			log.setConditions(strings[0]);
			log.setActions(strings[1]);
			
			log.setRule(rule);
			log.setIndex(count++);
			logs.add(log);
		}
		return logs;
	}
	
	public ArrayList<String> logNames() {
		return new ArrayList<String>(map.keySet());
	}
	
	public static class LogE {
		private int index;
		private String name;
		private ArrayList<String> logs;
		private Rule rule;
		private String categories;
		private String conditions;
		private String actions;
		
		public LogE(String name, ArrayList<String> logs) {
			this.name = name;
			this.logs = logs;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<String> getLogs() {
			return logs;
		}

		public void setLogs(ArrayList<String> logs) {
			this.logs = logs;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public Rule getRule() {
			return rule;
		}

		public void setRule(Rule rule) {
			this.rule = rule;
		}

		public String getCategories() {
			return categories;
		}

		public void setCategories(String categories) {
			this.categories = categories;
		}

		public String getConditions() {
			return conditions;
		}

		public void setConditions(String conditions) {
			this.conditions = conditions;
		}

		public String getActions() {
			return actions;
		}

		public void setActions(String actions) {
			this.actions = actions;
		}
	}
}
