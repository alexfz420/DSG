package com.dicks.action;

import com.dicks.engine.Util;

public class PercentageAction {

	private int percentage = 0;
	
	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String execute() {
		this.percentage = Integer.parseInt(Util.percentage);
		System.out.println("percentage " + percentage);
		return "success";
    }
		
		
}
