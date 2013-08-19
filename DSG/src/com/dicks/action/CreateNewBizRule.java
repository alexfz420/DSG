package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.engine.CreateTemplate;
import com.dicks.engine.WriteDrl;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;

public class CreateNewBizRule {
	private String conditions;

	private String actions;
	private String templatename;
	private String rulename;
	public String[] operator;
	public String[] attribute;
	public String[] value;
	public String categoryname;
	public Rule[] allRule;
	public String priority;
	public String[] test;
	public String[] productcount;
	public String[] sources;
	public String des;

	public String prodCate;
	public String storeCate;
	
	public void setStoreCate(String storeCate){
		this.storeCate = storeCate;
	}
	
	public String getstoreCate(){
		return storeCate;
	}
	
	public String getDes(){
		return des;
	}
	
	public void setDes(String des){
		this.des = des;
	}
	public void setProdCate(String prodCate){
		this.prodCate = prodCate;
	}
	
	public String getProdCate(){
		return prodCate;
	}

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
	public String getPriority(){
		return priority;
	}
	public void setPriority(String a){
		this.priority = a;
	}
	public String[] getTest(){
		return test;
	}

	public void setTest(String[] a){
		this.test = a;
		for (int i = 0; i<test.length; i++){
			//System.out.println("haha"+test[i]);
		}
	}


	public Rule[] getAllRule(){
		return allRule;
	}

	public void setAllRule(Rule[] allRule){
		this.allRule = allRule;
	}

	public String getCategory(){
		return categoryname;
	}

	public String[] getAttribute(){
		return attribute;
	}

	public String[] getOperator(){
		return operator;
	}
	public String[] value(){
		return value;
	}

	public void setAttribute(String[] a){
		this.attribute = a;

	}

	public void setOperator(String[] a){
		this.operator = a;

	}

	public void setValue(String[] a){
		this.value = a;

	}
	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String gotonewbizrulelist(){
		String[] tmp2 = null;
		String[] tmp3 = null;
		try {
			tmp2 = ProdCateDAO.getInstance().getProdCateNames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer cate = new StringBuffer();
		cate.append(tmp2[0]);
		for (int i = 1;i<tmp2.length;i++){
			//System.out.println(tmp2[i]);
			cate.append(","+tmp2[i]);
		}
		try {
			tmp3 = StoreCateDAO.getInstance().getStoreCateNames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer cates = new StringBuffer();
		cates.append(tmp3[0]);
		for (int i = 1;i<tmp3.length;i++){
			//System.out.println(tmp2[i]);
			cates.append(","+tmp3[i]);
		}
		storeCate = cates.toString();
		System.out.println("store cate!!!"+storeCate);
		prodCate = cate.toString();
		System.out.println("prod cate!!!"+prodCate);
		return "success";
	}

	public String gototemplate(){
		System.out.println("!!!!!!!prodCate"+prodCate);
		System.out.println(rulename);
		System.out.println(templatename);
		System.out.println(categoryname);
		
		if(templatename.equals("product_threshold")){
			
			try {
				allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//setAllRule(allRule);
			/*for (int i = 0 ;  i < allRule.length; i++){
			System.out.println("rule name  11111"+allRule[i].getRuleName());
			System.out.println("rule desc  1111"+ allRule[i].getRuleDescr());
			}*/
			
			des = des.replace(" ","%20");
			rulename = rulename.replace(" ","%20");
			categoryname = categoryname.replace(" ","%20");
			return "goToProductThreshold";
		}
		else if(templatename.equals("special_route")){
			try {
				allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setAllRule(allRule);
			des = des.replace(" ","%20");
			rulename = rulename.replace(" ","%20");
			categoryname = categoryname.replace(" ","%20");
			return "goToSpecial";
		}
		else if(templatename.equals("store_threshold")){
			try {
				allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setAllRule(allRule);
			des = des.replace(" ","%20");
			rulename = rulename.replace(" ","%20");
			categoryname = categoryname.replace(" ","%20");
			return "goToStoreThreshold";
		}
		return "goToTemplate";
	}

	public String newrule(){
		categoryname =categoryname.replace("%20", " ");
		rulename = rulename.replace("%20", " ");
		des = des.replace("%20", " ");
		
		System.out.println("cate "+categoryname);
		categoryname = categoryname.trim();
		String[] categoryList= categoryname.split(",");
		int cateLength = 0;
		for (int j = 0 ; j<categoryList.length;j++){
			if ((categoryList[j] != null) && (!categoryList[j].equals(" "))){
				cateLength++;
			}
		}
		String [] cateList = new String[cateLength];
		for (int i = 0; i<cateList.length;i++){
			cateList[i] = categoryList[i];
		}
		String type = null;
		if (templatename.equalsIgnoreCase("product_threshold")){
			type = "Product Threshold";
		}
		String[] product = null;

		//System.out.println("first instance of catelist is "+cateList[0]);
		try {
			product = ProdCateDAO.getInstance().getSKUByCategory(cateList);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//no available action right now
		String[] action = new String[1];
		action[0] = "miniumPackage";
		
		//no route needed 
		String[] route = new String[1];
		route[0] = "a";
		/*
		System.out.println("rulename "+rulename);
		System.out.println("des "+des);
		for (int i = 0;i<product.length;i++){
			System.out.println("prod "+product[i]);
		}
		for (int i = 0; i < attribute.length; i++){
			System.out.println("att "+attribute[i]);
			System.out.println("oper "+operator[i]);
			System.out.println("value "+value[i]);
		}*/
		
		
		CreateTemplate test= new CreateTemplate(rulename,des,type,product,attribute,operator,value,conditions,route,action,"TH-A,ST-A,SP-A",Integer.parseInt(priority),cateList);
		WriteDrl wdl = new WriteDrl();
		
		return "newrule";
	}
	
	public String storeThreshold(){
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+categoryname);
		categoryname =categoryname.replace("%20", " ");
		rulename = rulename.replace("%20", " ");
		des = des.replace("%20", " ");
		System.out.println("input category"+categoryname);
		
		
		String[] categoryList= categoryname.split(",");
		int cateLength = 0;
		for (int j = 0 ; j<categoryList.length;j++){
			if ((categoryList[j] != null) && (!categoryList[j].equals(" "))){
				cateLength++;
			}
		}
		String [] cateList = new String[cateLength];
		for (int i = 0; i<cateList.length;i++){
			cateList[i] = categoryList[i];
		}
		String type = null;
		if (templatename.equalsIgnoreCase("store_threshold")){
			type = "Store Threshold";
		}
		String[] product = null;
		for (int i = 0; i<cateList.length;i++){
			System.out.println("cate"+i+" "+cateList[i]);
		}
		//System.out.println("first instance of catelist is "+cateList[0]);
		try {
			product = StoreCateDAO.getInstance().getStoreNamesByCategory(cateList);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("product size is "+product.length);
		//no available action right now
		String[] action = new String[1];
		action[0] = "retract";
		//no route needed 
		String[] route = new String[1];
		route[0] = " ";
		
		
		System.out.println("rulename "+rulename);
		
		System.out.println("des "+des);
		for (int i = 0;i<product.length;i++){
			System.out.println("prod "+product[i]);
		}
		for (int i = 0; i < attribute.length; i++){
			System.out.println("att "+attribute[i]);
			System.out.println("oper "+operator[i]);
			System.out.println("value "+value[i]);
		}
		
		conditions ="&&";
		
		CreateTemplate test= new CreateTemplate(rulename,des,type,product,attribute,operator,value,conditions,route,action,"TH-A,ST-A,SP-A",Integer.parseInt(priority),cateList);
		WriteDrl wdl = new WriteDrl();
		
		return "storeThreshold";
	}
	
	public String specialRoutes(){
		//get sku from category
		categoryname =categoryname.replace("%20", " ");
		rulename = rulename.replace("%20", " ");
		des = des.replace("%20", " ");
		System.out.println("input category"+categoryname);
		categoryname = categoryname.trim();
		
		String[] categoryList= categoryname.split(",");


		int cateLength = 0;
		for (int j = 0 ; j<categoryList.length;j++){
			if ((categoryList[j] != null) && (!categoryList[j].equals(" "))){
				cateLength++;
			}
		}

		String [] cateList = new String[cateLength];
		for (int i = 0; i<cateList.length;i++){
			cateList[i] = categoryList[i];
		}
		for (int i = 0; i<cateList.length;i++){
			System.out.println("cate"+i+" "+categoryList[i]);
			cateList[i] = cateList[i].trim();
		}
		String[] product = null;

		//System.out.println("first instance of catelist is "+cateList[0]);
		try {
			product = ProdCateDAO.getInstance().getSKUByCategory(cateList);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("product size is "+product.length);
		
		///done

		
		String[] aa = new String[1];
		aa[0] = " ";
		String type = null;
		if (templatename.equalsIgnoreCase("special_route")){
			System.out.println("Template is Correct");
			type = "Special Route";
		}
		
		 String[] action = new String[1];
		 action[0] = "special";
		 
		 String flag = "TH-A,ST-A,SP-A";
		CreateTemplate test= new CreateTemplate(rulename,des,type,product,aa,operator,productcount," ",sources,action,flag,Integer.parseInt(priority),cateList);
		WriteDrl wdl = new WriteDrl();
		
		
		
		return "specialRoutes";
	}
	


	public String placeorder(){


		//get specific rule from rulename 
		/*try {
			allRule = RuleDAO.getInstance().getAllSortedList() ;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setAllRule(allRule);*/
		for (int i = 0 ;  i < allRule.length; i++){
		System.out.println("rule name  "+allRule[i].getRuleName());
		System.out.println("rule desc  "+ allRule[i].getRuleDescr());
		}



		//get the rule from the inserted position


		//compu

		return "placeorder";
	}


	



}
