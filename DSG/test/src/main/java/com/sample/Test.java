package com.sample;

public class Test {

	public static void main(String[] args) {
		for (int i = 1; i < 50; i++) {
			System.out.println(fibonacci(i));
		}
	}
	
	
	public static int fibonacci(int n) {
		if (n == 1 || n == 2) {
			//System.out.println(n + " == 1");
			return 1;
		}
		
		int r = fibonacci(n - 1) + fibonacci(n - 2);
		//System.out.println(n + " == " + r);
		return r;
	}
	
}
