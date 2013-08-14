package com.dicks.action;

import com.dicks.pojo.Rule;

public class SpecialRouteAction {
	public String[] productcount;
	public String[] sources;
	
	public void setProductcount(String[] a){
		this.productcount = a;
	}
	
	public String[] getProductcount(){
		return productcount;
	}
	
	public void setSources(String[] a){
		this.sources = a;
	}
	
	public String[] getSources(){
		return sources;
	}
	
	public String specialRoute(){
		for (int i = 0; i < productcount.length; i++){
			System.out.println(productcount[i]);
			System.out.println(sources[i]);
		}
		System.out.println("getting to special route");
		return "success";
	}
}
