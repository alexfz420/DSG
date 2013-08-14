
package com.sample;

import java.io.*;

public class createRuleJsp {

	

	
	   public static void main (String[] args) throws IOException {
		   //String current = new java.io.File( "." ).getCanonicalPath();
           //System.out.println("Current dir:"+current);
		   //String currentDir = System.getProperty("user.dir");
           //System.out.println("Current dir using System:" +currentDir);
		 String type ="Threshold";
		 String[] product = new String[4];
		 product[0] ="1";
		 product[1] ="2";
	     product[2] = "4";
		 product[3] = "def234";
		 //product[2] ="ghi456";
		 String[] attribute = new String[2];
		 attribute[0] = "weight";
		 attribute[1] ="size";
		 String[] operator = new String[2];
		 operator[0] = ">";
		 operator[1]= "<";
		 String value[] = new String[2];
		 value[0] = "5";
		 value[1] ="3";
		 String action[] = new String[1];
		 action[0] = "minPackage";
		 String route = new String();
		 String flag = "TH-B,ST-A,SP-A";
		 int position = 2;
		//CreateTemplate test= new CreateTemplate(type,product,attribute,operator,value,null,action,flag,position);
		 
		 
		 type ="Store Filter";
		 product = new String[1];
		 product[0] ="store001";
		 //product[1] ="store13";
		 //product[1] = "def234";
		 //product[2] ="ghi456";
		 attribute = new String[2];
		 attribute[0] = "zone";
		 attribute[1] = "Safety Stock";
		 //attribute[1] ="size";
		 operator = new String[2];
		 operator[0] = ">";
		 operator[1] = "<";
		 //operator[1]= "<";
		 value = new String[2];
		 value[0] = "5";
		 value[1] = "15";
		 //value[1] ="3";
		 action = new String[1];
		 action[0] = "retract";
		 route = new String();
		 flag = "TH-A,ST-C,SP-A";
		 position = 1;
		//test= new CreateTemplate(type,product,attribute,operator,value,null,action,flag,position);
		
		type ="Special Route";
		position = 3;
		 product = new String[1];
		 product[0] ="1";
		 //product[1] ="002";
		 //product[1] = "def234";
		 //product[2] ="ghi456";
		 attribute = new String[2];
		 attribute[0] = "number";
		 operator = new String[2];
		 operator[0] = ">";
		 value = new String[2];
		 value[0] = "5";
		 action = new String[1];
		 action[0] = "special";
		 route = new String();
		 route = "1";
		 flag = "TH-A,ST-A,SP-B";
		CreateTemplate test= new CreateTemplate(type,product,attribute,operator,value,route,action,flag,position);
	   }
}