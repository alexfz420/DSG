package com.dicks.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.pojo.Rule;

public class EngineLog {
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();	
	private int stage;
	
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
		return map.get(name);
	}
	
	public ArrayList<LogE> getLogs() throws Exception {
		ArrayList<LogE> logs = new ArrayList<LogE>();
		int count = 0;
		for (String name : map.keySet()) {
			LogE log = new LogE(name, map.get(name));
			Rule rule = RuleDAO.getInstance().getRuleByName(name);
			String[] categories = RuleCateDAO.getInstance().getCateNamesByRuleId(rule.getRuleId());
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
	}
}
