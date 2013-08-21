package com.dicks.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.print.DocFlavor.URL;

import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.pojo.Product;
import com.dicks.pojo.Vendor;
import com.dicks.pojo.Rule;

public class UpdateTemplate {
    public String myTab ="    ";
    public String myReturn = "\n";
    public String mySpace = " ";
    public int ruleCount = 0;
    public static String[] rules = new String[10];
    public static Rule[] ruleFile = new Rule[100];
    public static Rule[] ruleFiles = null;
    public static int ruleInt;
    public String typeString;
    
    
    public static Product[] product = new Product[3];
    
   
    
	public UpdateTemplate  (int ruleId, String ruleName,String ruleNames, String description,String[] objects, String[] attributes, 
			String[] operators, String[] values, String conditions, String[] routes, String[] actions, String flag,String[] cateList, boolean isProduct){
		
		System.out.println("pre name "+ruleName);
		System.out.println("changed name "+ruleNames);
		System.out.println("des "+description);
		for (int i = 0; i<values.length;i++){
			System.out.println("!valueasdfads "+values[i]);
		}
		for (int i = 0; i<attributes.length;i++){
			System.out.println("!valueasdfads "+attributes[i]);
		}
		for (int i = 0; i<operators.length;i++){
			System.out.println("operator!!! "+operators[i]);
		}
		for (int i = 0; i<objects.length;i++){
			System.out.println("objs!!! "+objects[i]);
		}
		
		int ruleInt = 0;
		System.out.println("route!!"+routes);
		System.out.println("condition is!!!"+conditions);
		String condition = null;
		if (conditions != null){
			if (conditions.equals("All")){
				condition = "||";
			}
			else{
				condition = "&&";
			}
			System.out.println("condition is "+condition);
		}
		String current = null;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        System.out.println("Current dir:"+current);
	 String currentDir = System.getProperty("user.dir");
	        System.out.println("Current dir using System:" +currentDir);
		 
		  
		  System.out.println("--------------Updating Rules--------------------------");
		 
			

			try {
				ruleFiles = RuleDAO.getInstance().getAllSortedListFromStageOne();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("three is "+ruleFiles.length);
			System.out.println("checking!! "+ruleName);
			for (int i = 0 ; i < ruleFiles.length; i++){
				System.out.println("checking "+ruleFiles[i].getRuleName());
				if ((ruleFiles[i].getRuleName().replace("%20"," ")).equals(ruleName)){
					ruleInt = i;
					System.out.println("num "+i);
					System.out.println("ruleID "+ruleFiles[i].getRuleId());
				}
				//System.out.println(i+" "+ruleFiles[i].getRuleDescr());

			}
			
		  System.out.println("Rules before editing");
		  int i = 0;
		  for (i = 0; i<ruleFiles.length;i++){
	    	  System.out.println ("Rule :"+i+"  "+ ruleFiles[i].getRuleName()+" Priority: "+ruleFiles[i].getPriority());


	      }
	      System.out.println("----------------------------------------------------------");
	      
	      System.out.println("rule name"+ruleName);
	      System.out.println("rule des"+description);
	      System.out.println("rule condi"+conditions);
	      for (i = 0 ; i<attributes.length;i++){
	    	  System.out.println(attributes[i]);
	    	  System.out.println(operators[i]);
	    	  System.out.println(values[i]);
	      }
	      ruleFiles[ruleInt].setRuleName(ruleNames);
	      ruleFiles[ruleInt].setRuleDescr(description);
	      ruleFiles[ruleInt].setObjects(objects);
	      ruleFiles[ruleInt].setAttributes(attributes);
	      ruleFiles[ruleInt].setOperators(operators);
	      ruleFiles[ruleInt].setValues(values);
	      ruleFiles[ruleInt].setOperators(operators);
	      ruleFiles[ruleInt].setCondition(condition);
	     
		

	      try {
	    	  System.out.println("updating");
				RuleDAO.getInstance().update(ruleFiles[ruleInt]);
				RuleCateDAO.getInstance().createRuleCate(RuleDAO.getInstance().getRuleByName(ruleFiles[ruleInt].getRuleName()).getRuleId(), cateList, isProduct);
				
			} catch (Exception e) {
				System.out.println("eeeeeeror"+e);// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     WriteDrl test = new WriteDrl();
		   
	      
	}




	   

}
