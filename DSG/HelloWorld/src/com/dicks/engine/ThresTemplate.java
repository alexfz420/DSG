package com.dicks.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.dicks.pojo.Rule;

import org.apache.struts2.ServletActionContext;

public class ThresTemplate {
    public String myTab ="    ";
    public String myReturn = "\n";
    public String mySpace = " ";
    public int ruleCount = 0;
    public static String[] rules = new String[10];
    public static Rule[] ruleFile = new Rule[100];
    public static int ruleInt;
    
	public ThresTemplate  (String type, String[] object, String[] attribute, 
			String[] operator, String[] values, String actions ){


		String classesPath  =ServletActionContext.getServletContext().getRealPath("/Helloworld");
		System.out.println("WTF"+classesPath);

		  rules[0] = "/Users/yingli/Documents/github/dicks/HelloWorld/ruleTxt/ruleHeader.txt";

		  rules[0] = "ruleTxt/ruleHeader.txt";

		  rules[1] = "/Users/yingli/Documents/github/dicks/HelloWorld/ruleTxt/rule1.txt";
		  rules[2] = "/Users/yingli/Documents/github/dicks/HelloWorld/ruleTxt/rule2.txt";
		  rules[3] = "/Users/yingli/Documents/github/dicks/HelloWorld/ruleTxt/rule3.txt";





		  //get piority, hardcoded for 2 for demo
		  int i = 0;
	      while (ruleFile[i]!= null){
	    	  System.out.println ("Rule :"+i+"  "+ ruleFile[i].getRuleDescr()+" Piority: "+ruleFile[i].getPriority());
	    	  i++;
	      }
	      //ruleFile[i+1] = new Rule();

	      //ruleInt = 1;
	      int ruleInt = 2;
	      //System.out.println("rule is at  " +ruleInt + "current rule number is "+i);

	      if (ruleInt < (i)){

	      reRank(ruleInt);
	      }
	      else{
	    	  ruleFile[i] = new Rule();
	    	  ruleInt = i;
	      }

		   System.out.println("type  "+type);
		   System.out.println("objects  "+object);
		   System.out.println("attribute" +attribute);
		   System.out.println("operator "+ operator);
		   System.out.println("Values "+values);
		   StringBuffer newRule = new StringBuffer();
		   newRule.append(writeRuleType(type));
		   newRule.append(writeWhen(object,attribute,operator,values));
		   newRule.append(writeThen(actions));
		   System.out.println(newRule.toString());
		   String content = newRule.toString();


		   try {
		    	 for (i  = 0 ; rules[i] != null; i++){
		    		 if (rules[i+1] == null){

		    			 System.out.println("new file is"+"/Users/yingli/Documents/github/dicks/HelloWorld/ruleTxt/rule"+(i+1)+".txt");
		    			 String newPath = classesPath+"/ruleTxt/rule"+(i+1)+".txt";
		    			 File file = new File(newPath);  
		    			// System.out.println("current rule is "+ruleFile[ruleInt]+ "with piority"+ruleFile[ruleInt].getPiority());
		    			 if (ruleInt > 0){

		    				 ruleFile[ruleInt] = new Rule("abc", newPath, "\""+type+ruleInt+"\"", ruleFile[ruleInt-1].getPriority()-2);
		    			 }
		    			 else{
		    				 ruleFile[ruleInt] = new Rule("bcd", newPath, "\""+type+ruleInt+"\"", ruleFile[ruleInt].getPriority()+2);

		    			 }

		    			 FileOutputStream fop = new FileOutputStream(file);

		    				// if file doesnt exists, then create it
		    				if (!file.exists()) {
		    					file.createNewFile();
		    				}

		    				// get the content in bytes
		    				byte[] contentInBytes = content.getBytes();

		    				fop.write(contentInBytes);
		    				fop.flush();
		    				fop.close();

		    				System.out.println("Done");
		    				break;

		    			} 
		    	 }
		    }
		     catch (IOException e) {
		    	 e.printStackTrace();
		     }			 

		    //combining all rules
		     try {

	             File file = new File(classesPath+"/ruleTxt/newRule.drl");         

	             FileOutputStream fos = new FileOutputStream(file);
	             i = 0;
	             FileInputStream fis;

	             String current = new java.io.File( "." ).getCanonicalPath();
	             System.out.println("Current dir:"+current);
	      String currentDir = System.getProperty("user.dir");
	             System.out.println("Current dir using System:" +currentDir);

	             fis = new FileInputStream(new File(classesPath+"/ruleTxt/ruleHeader.txt"));

	        	 byte[] b = new byte[1];
	        	 //System.out.print(b);
	        	 while((fis.read(b)) != -1){
	                 fos.write(b);
	             }

	             while (ruleFile[i] != null){
	            	 //System.out.println("combing rule "+i);
	            	 fis = new FileInputStream(new File(ruleFile[i].getRuleUrl()));
	            	 System.out.println("Gettting new path-----"+ruleFile[i].getRuleUrl()+"i is  "+i);
	            	 b = new byte[1];
	            	 //System.out.print(b);
	            	 while((fis.read(b)) != -1){
	                     fos.write(b);
	                 }
	            	 i++;
	             }

	            fos.flush();
	            System.out.println("success!");
	       }
	      catch(Exception e){System.out.println("error: " + e);}
		     //threshold abc = new threshold("hold");

		     i = 0;
		      while (ruleFile[i]!= null){
		    	  System.out.println ("Rule :"+i+"  "+ ruleFile[i].getRuleDescr()+" Piority: "+ruleFile[i].getPriority());

		    	  i++;
		      }

	}




	   public String writeRuleType(String type){
		   StringBuffer tmp = new StringBuffer();
		   tmp.append("rule  \"Mininum Package"+type+ruleCount+"\""+myReturn);

		   //need to add more statement such as no-loop true dialect "java", will decide later

		   return tmp.toString();

	   }

	   public String writeWhen(String[] splits, String[] splitAttribute, String[] splitOperator, String[] splitValue){

		   //split the object 
		   //System.out.println("1"+splits[0]+"1"+splits[1]+"2"+splits[2]);
		   //first product, special case it if the input is "all"
		   StringBuffer multiObject = new StringBuffer();
		   if (splits[0].equals("ALL")){
			   multiObject.append("");
		   }
		   else{
		   multiObject.append("&& (( productID == "+splits[0]+" )");

		   //combing all the other products
		   System.out.println("splits.size: " + splits.length);
		   for (int i = 1; i < splits.length; i++){
			   multiObject.append("|| (productID == "+splits[i]+" )");
			   System.out.println("add second product");
		   }
		   multiObject.append(")");
		   }

		   //split the attribute
		  // System.out.println("1"+splitsAttribute[0]+"1"+attribute[1]+"2"+attribute[2]);
		  System.out.println("attribute legnth"+splitAttribute.length);

		   //split the operator

		   //split the value

		   //first operator (default)
		   StringBuffer multiAttribute = new StringBuffer();
		   multiAttribute.append("("+ splitAttribute[0]+mySpace+splitOperator[0]+mySpace+splitValue[0] +")");
		   System.out.println("first operationmulti "+multiAttribute.toString());

		   System.out.println("length is!!!"+splitAttribute.length+mySpace+splitOperator.length+mySpace+splitValue.length);
		   //combining all the other operations
		   for (int i = 1; i < splitAttribute.length; i++){
			   System.out.println("round "+i);
			   System.out.println(splitAttribute[i]);
			   System.out.println(splitOperator[i]); 
			   System.out.println(splitValue[i]);
			   multiAttribute.append("|| ( "+ splitAttribute[i]+mySpace+splitOperator[i]+mySpace+splitValue[i] + ")");
			   System.out.println("add second operations!!!"+multiAttribute.toString());
		   }

		   //appending the whole "when" part

		   StringBuffer tmp = new StringBuffer();
		   tmp.append(myTab+"when"+myReturn);
		   tmp.append(myTab+myTab+"$o : Order()"+myReturn);
		   tmp.append(myTab+myTab+"$i : Product( ("+ multiAttribute+")"+multiObject.toString()+
		   		") from $o.getProducts()"+myReturn);
		   //tmp.append(myTab+myTab+"$p : Purchase( customer == $c, $"+attribute.charAt(0)+" : product."+attribute+mySpace+operator+mySpace+values+" )");

		   return tmp.toString();
	   }


	   public String writeThen(String action){
		   StringBuffer tmp = new StringBuffer();
		   tmp.append(myTab+"then"+myReturn);
		   tmp.append(myTab+myTab+"$i.minPackage();"+myReturn);
		   tmp.append("end"+myReturn+myReturn);
		   return tmp.toString();
	   }

	   public void reRank (int rank){

		   Rule tmp = ruleFile[rank];
		   Rule tmp2 = new Rule();
		   System.out.println("Shifting rule"+ruleFile[rank].getRuleDescr());

		   while (ruleFile[rank+1] != null){
			  System.out.println("shift rule  "+rank);

			   tmp2 = ruleFile[rank+1];
			   ruleFile[rank+1] =tmp;
			   ruleFile[rank+1].setPriority(ruleFile[rank+1].getPriority()-2);
			   tmp = tmp2;




			   //System.out.println("round 1 "+"rank  ="+rank+"tmp = "+ tmp.getDescription()
					  // +"rule[rank]"+ruleFile[rank].getDescription()+
					   //"rule[rank+1]  "+ruleFile[rank+1].getDescription());
			  // ruleFile[rank+1].setPiority(ruleFile[rank+1].getPiority()-2);

			   rank ++;
		   }
		   ruleFile[rank+1]=tmp;
		   ruleFile[rank+1].setPriority(ruleFile[rank+1].getPriority()-2);
		   System.out.println("last index is "+(rank+1));
	   }
}