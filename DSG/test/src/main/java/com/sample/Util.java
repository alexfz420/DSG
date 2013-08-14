package com.sample;

public class Util {

	public static double getShippingDays(int from, int to) {
		return 2.5 * (Math.abs(from - to));
	}
	
	public static double getShippingCosts(int from, int to) {
		return 2.5 * (Math.abs(from - to));
	}
}
