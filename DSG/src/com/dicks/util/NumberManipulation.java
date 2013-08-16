package com.dicks.util;

import java.text.DecimalFormat;

public class NumberManipulation
{
	private NumberManipulation(){};
	
	public static String long2TwoDecimal(int input){
		DecimalFormat df = new DecimalFormat("###,##0.00"); 
		float f = input;
		return df.format(f/100);
	}
	
	public static String long2ThreeDecimal(long input){
		DecimalFormat df = new DecimalFormat("###,##0.000"); 
		float f = input;
		return df.format(f/1000);
	}
	
	public static String float2TwoDecimal(float input){
		DecimalFormat df = new DecimalFormat("###,##0.00"); 
		return df.format(input);
	}
	
	public static String getValue(long shares, long price){
		DecimalFormat df = new DecimalFormat("###,##0.00");
		float f = shares* price/100000;
		return df.format(f);
	}
}
