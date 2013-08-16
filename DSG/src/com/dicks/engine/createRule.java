package com.dicks.engine;

import java.io.*;

import com.dicks.pojo.Rule;


public class createRule {

	public static String ruleType = new String();
    public static StringBuffer objectTypeBuffer = new StringBuffer();
    public static String objectType = new String();
    public static String operator = new String();
    public static String actions = new String();
    public static String values = new String();
    public static String attribute = new String();
    public static int ruleCount = 0;
    public static String myTab ="    ";
    public static String myReturn = "\n";
    public static String mySpace = " ";
    public static StringBuffer attributeBuffer = new StringBuffer();
    public static StringBuffer operatorBuffer = new StringBuffer();
    public static StringBuffer valueBuffer = new StringBuffer();
    public static String[] rules = new String[10];
    public static Rule[] ruleFile = new Rule[100];
    public static int ruleInt;


	   public static void main (String[] args) throws IOException {
		  rules[0] = "./ruleTxt/ruleHeader.txt";
		  rules[1] = "./ruleTxt/rule1.txt";
		  rules[2] = "./ruleTxt/rule2.txt";
		  rules[3] = "./ruleTxt/rule3.txt";


		  ruleFile[0] = new Rule("1","./ruleTxt/rule1.txt","\"Explode Cart\"",100);
		  ruleFile[1] = new Rule("2","./ruleTxt/rule2.txt","\"filter stock\"",98);
		  ruleFile[2] = new Rule("3","./ruleTxt/rule3.txt","\"Summarize\"",-5);

	      //  prompt the user to enter their name

	      //  open up standard input
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      String input = null;


	      //read rule type till get the correct one
	      do{
	    	  System.out.print("Enter your type of the rule you want to create (threshold, special route): ");
	    	  input = br.readLine();
	      }
	      while (!input.equalsIgnoreCase("threshold"));
	      ruleType = input;
	      System.out.println("Thanks for the type, " + ruleType);


	      System.out.print("Enter the object you want to put your rules on (store, product) ");
	      input = br.readLine();


	      objectTypeBuffer.append("product");
	      if (input.equalsIgnoreCase("product")){
	    	  System.out.print("please type SKU to apply the rule ");
	    	  input = br.readLine();

	    	  if (input.equalsIgnoreCase("all")){
	    		  objectTypeBuffer.append(",ALL");
	    	  }
	    	  else{ 

		    	  do {
		    		  objectTypeBuffer.append(","+input);
		    		  System.out.print("type SKU to add more or NEXT to continue");

			    	  input = br.readLine();

		    	  }
		    	  while (!input.equalsIgnoreCase("next"));
	    	}


	      }

	      if (input.equalsIgnoreCase("store")){
	    	  System.out.print("please type storeID to apply the rule ");
	    	  input = br.readLine();

	    	  if (input.equalsIgnoreCase("all")){
	    		  objectTypeBuffer.append(",ALL");
	    	  }
	    	  else{ 

		    	  do {
		    		  objectTypeBuffer.append(","+input);
		    		  System.out.print("type storeID to add more or NEXT to continue");

			    	  input = br.readLine();

		    	  }
		    	  while (!input.equalsIgnoreCase("next"));
	    	}


	      }

	      System.out.print("Enter the attribute of the product to apply the rule ");
	      input = br.readLine();
	      attributeBuffer.append(input);

	      do{
	    	  System.out.print("Enter the operator to set the condition of the rule (>,<,=)");
	    	  input = br.readLine();
	      }
	      while (!((input.equalsIgnoreCase(">"))||(input.equalsIgnoreCase("<"))||(input.equalsIgnoreCase("="))));
	      //operator = input;
	      operatorBuffer.append(input);
	      //first attribute and operator
	      System.out.print("Enter the value associate with the operation ");
	      input = br.readLine();
	      valueBuffer.append( input) ;

	      System.out.print("Enter new attribute of the product to apply the rule or next to to set actions");
	      input = br.readLine();
	      while (!(input.equals("next"))) {

		      attributeBuffer.append(","+input);

		      do{
		    	  System.out.print("Enter the operator to set the condition of the rule (>,<,=)");
		    	  input = br.readLine();
		      }
		      while (!((input.equalsIgnoreCase(">"))||(input.equalsIgnoreCase("<"))||(input.equalsIgnoreCase("="))));
		      //operator = input;
		      operatorBuffer.append(","+input);

		      System.out.print("Enter the value associate with the operation ");
		      input = br.readLine();
		      valueBuffer.append(","+input) ;


		      System.out.print("Enter the attribute of the product to apply the rule ");
		      input = br.readLine();

    	  }
    	  

	      int i = 0;
	      while (ruleFile[i]!= null){
	    	  System.out.println ("Rule :"+i+"  "+ ruleFile[i].getRuleDescr()+" Piority: "+ruleFile[i].getPriority());
	    	  i++;
	      }
	      //ruleFile[i+1] = new Rule();
	      System.out.println("Where would you put your new rule at ");
	      input = br.readLine();
	      //ruleInt = 1;
	      int ruleInt = Integer.parseInt(input);
	      //System.out.println("rule is at  " +ruleInt + "current rule number is "+i);

	      if (ruleInt < (i)){

	      reRank(ruleInt);
	      }
	      else{
	    	  ruleFile[i] = new Rule();
	    	  ruleInt = i;
	      }


	      System.out.print("Enter the action you want to do");
	      input = br.readLine();
	      actions = input;



	      System.out.println("thank you for your input, are you trying to make a rule of type of\n "+ ruleType+ "which will do action: "
	      +actions+" when the value of object "+ objectTypeBuffer.toString() + " is "+ operator + values);

	     String content = writeDrl(ruleType, objectTypeBuffer.toString(),attributeBuffer.toString(), operatorBuffer.toString()
	    		 ,valueBuffer.toString(),actions);

	     //create txt file for the new rule



	     try {
	    	 for (i  = 0 ; rules[i] != null; i++){
	    		 if (rules[i+1] == null){
	    			 System.out.println("new file is"+"./ruleTxt/rule"+(i+1)+".txt");
	    			 String newPath = "./ruleTxt/rule"+(i+1)+".txt";
	    			 File file = new File(newPath);  
	    			// System.out.println("current rule is "+ruleFile[ruleInt]+ "with piority"+ruleFile[ruleInt].getPiority());
	    			 if (ruleInt > 0){

	    				 ruleFile[ruleInt] = new Rule("abc", newPath, "\""+ruleType+ruleInt+"\"", ruleFile[ruleInt-1].getPriority()-2);
	    			 }
	    			 else{
	    				 ruleFile[ruleInt] = new Rule("bcd", newPath, "\""+ruleType+ruleInt+"\"", ruleFile[ruleInt].getPriority()+2);

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
             File file = new File("./ruleTxt/newRule.drl");         
             FileOutputStream fos = new FileOutputStream(file);
             i = 0;
             FileInputStream fis;
             fis = new FileInputStream(new File("./ruleTxt/ruleHeader.txt"));
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
	      //System.out.println ("Rule :"+i+"  "+ ruleFile[i+1].getDescription());

	     //re-ordering rules

	} 	
	   public static String writeDrl(String type, String object, String attribute, 
			   String operator, String values, String actions ){
		   StringBuffer newRule = new StringBuffer();
		   newRule.append(writeRuleType(type));
		   newRule.append(writeWhen(object,attribute,operator,values));
		   newRule.append(writeThen(actions));
		   System.out.println(newRule.toString());
		   return newRule.toString();

	   }

	   public static String writeRuleType(String type){
		   StringBuffer tmp = new StringBuffer();
		   tmp.append("rule  \"Mininum Package"+type+ruleCount+"\""+myReturn);

		   //need to add more statement such as no-loop true dialect "java", will decide later

		   return tmp.toString();

	   }

	   public static String writeWhen(String object, String attribute, String operator, String values){
		   System.out.println("attribute!!!!"+attribute);

		   //split the object 
		   System.out.println(object);
		   String[] splits = object.split(",");
		   //System.out.println("1"+splits[0]+"1"+splits[1]+"2"+splits[2]);
		   //first product, special case it if the input is "all"
		   StringBuffer multiObject = new StringBuffer();
		   if (splits[1].equals("ALL")){
			   multiObject.append("");
		   }
		   else{
		   multiObject.append("&& (( productID == "+splits[1]+" )");

		   //combing all the other products
		   System.out.println("splits.size: " + splits.length);
		   for (int i = 2; i < splits.length; i++){
			   multiObject.append("|| (productID == "+splits[i]+" )");
			   System.out.println("add second product");
		   }
		   multiObject.append(")");
		   }

		   //split the attribute
		   System.out.println(attribute);
		   String[] splitAttribute= attribute.split(",");
		  // System.out.println("1"+splitsAttribute[0]+"1"+attribute[1]+"2"+attribute[2]);
		  System.out.println("attribute legnth"+splitAttribute.length);

		   //split the operator
		   System.out.println(operator);
		   String[] splitOperator = operator.split(",");

		   //split the value
		   System.out.println(values);
		   String[] splitValue = values.split(",");

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


	   public static String writeThen(String action){
		   StringBuffer tmp = new StringBuffer();
		   tmp.append(myTab+"then"+myReturn);
		   tmp.append(myTab+myTab+"$i.minPackage();"+myReturn);
		   tmp.append("end"+myReturn+myReturn);
		   return tmp.toString();
	   }

	   public static void reRank (int rank){

		   Rule tmp = ruleFile[rank];
		   Rule tmp2 = new Rule();
		   System.out.println("Shifting rule"+ruleFile[rank].getRuleUrl());

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



	   /*public static void uniteTextFile(String dirPath,String newFilePathAndName,FileOutputStream pFOS)
	   {
	      FileInputStream fIS = null;
	      FileOutputStream fOS = null;   
	      File DirFile = new File(dirPath);
	      File newFile = new File(newFilePathAndName);
	     
	      try
	      {
	       if(!newFile.exists())newFile.createNewFile();
	       if(newFile.isDirectory())
	       {
	        System.out.println("you has to select a file not a dir");
	        return ;
	       }
	       if(pFOS == null)
	        fOS = new FileOutputStream(newFile);
	       else
	        fOS = pFOS;
	       byte [] buffer = null;
	       if(DirFile.exists())
	       {
	        if(DirFile.isDirectory())
	        {
	         File [] files = DirFile.listFiles();
	         for(int i=0;i<files.length;i++)
	         {
	          if(files[i].isFile())
	          {
	           if("txt".equals(files[i].getName().substring(files[i].getName().lastIndexOf(".")+1)))
	           {
	            System.out.println(files[i].getName());
	            fIS = new FileInputStream(files[i]);
	            buffer = new byte[fIS.available()];        
	            fIS.read(buffer);    
	            fOS.write(buffer);
	            fIS.close();
	            fOS.flush();
	           }
	          }
	          else if(files[i].isDirectory())
	          {
	          
	           uniteTextFile(files[i].getAbsolutePath(),newFilePathAndName,fOS);
	          }
	         }
	        }
	        else
	        {
	         System.out.println("its not a path but a file.");
	        }
	       }
	       else
	       {
	        System.out.println("file does not exit!");
	       }
	       if(pFOS == null)
	        fOS.close();
	      
	      
	      }
	      catch(Exception e)
	      {
	       e.printStackTrace();
	       try
	       {
	        if(fOS != null) fOS.close();
	        if(fIS != null) fIS.close();
	       }
	       catch(Exception ie)
	       {
	       
	       }
	      }
	   }*/
	   }
