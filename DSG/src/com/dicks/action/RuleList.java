package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.FeeDAO;
import com.dicks.dao.OrdersDAO;
import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.engine.CreateTemplate;
import com.dicks.engine.UpdateTemplate;
import com.dicks.engine.WriteDrl;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Rule;

public class RuleList {
	public String[] ruleList;
	public Rule[] allRule;
	public String ruleString;
	public Rule[] preRule;
	public Rule[] midRule;
	public Rule[] lastRule;
	public String ruleId;
	public String ruleType;
	public String[] attribute;
	public String[] operator;
	public String[] value;
	public String cates;
	public String rulename;
	public String rulenames;
	public String ruleDess;
	public String condition;
	public String conditions;
	public String prodCate;
	public String des;
	public String[] action;
	public String viewEdit;
	public String[] sources;
	private String ruleName;
	private String ruleDescription;
	
	
	public void setCates(String a){
		this.cates = a;
	}

	public String getCates(){
		return cates;
	}
	public void setSources(String[] a){
		this.sources = a;
	}

	public String[] getSources(){
		return sources;
	}
	
	public String getViewEdit(){
		return viewEdit;
	}
	
	public void setViewEdit(String viewEdit){
		this.viewEdit = viewEdit;
	}

	public String categoryname;
	
	private ArrayList<Fee> storeFeeList;
	private ArrayList<Fee> warehouseFeeList;
	private ArrayList<Fee> vendorFeeList;

	public String getRuleNames(){
		return rulenames;
	}
	public void setRuleNames(String rulenames){
		this.rulenames = rulenames;
	}
	public String getCategory(){
		return categoryname;
	}
	public void setCategory(String a){
		this.categoryname = a;
	}

	public String getDes(){
		return des;
	}

	public void setDes(String des){
		this.des = des;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public void setProdCate(String prodCate){
		this.prodCate = prodCate;
	}

	public String getProdCate(){
		return prodCate;
	}
	public String getRuleId(){
		return  ruleId;
	}

	public void setRuleId(String ruleId){
		this.ruleId = ruleId;
	}

	public String getRuleType(){
		return  ruleType;
	}

	public void setRuleType(String ruleType){
		this.ruleType = ruleType;
	}

	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}
	public String getRuleString(){
		return ruleString;
	}

	public void setRuleString(String ruleString){
		this.ruleString = ruleString;
	}



	public String gotorulelist(){

		int pre = 0;
		int mid = 0;
		int last = 0;
		try {
			// Claire change it to get all lists
			//allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
			allRule = RuleDAO.getInstance().getAllRuleList() ;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i<allRule.length;i++){
			if (allRule[i].getType().equals("n")){
				if (allRule[i].getPriority()>0){
					pre++;
				}
				else{
					last++;
				}
			}
		}
		mid = allRule.length-pre-last;
		System.out.println("all rule number is "+allRule.length);
		System.out.println("pre rule number is "+pre);
		System.out.println("mid rule number is "+mid);
		System.out.println("last rule number is "+last);
		preRule = new Rule[pre];
		midRule = new Rule[mid];
		lastRule = new Rule[last];
		for (int i = 0; i<pre;i++){
			preRule[i] = allRule[i];
			System.out.println("Pre Rule"+allRule[i].getRuleName());
		}
		for (int i = pre,j=0;i<(pre+mid);i++,j++){
			midRule[j] = allRule[i];
			System.out.println("Mid Rule"+allRule[i].getRuleName());
		}
		for (int i = (pre+mid),j=0; i < allRule.length; i++,j++){
			lastRule[j] = allRule[i];
			System.out.println("Last Rule"+allRule[i].getRuleName());
		}


		return "success";
	}


	public String goToEdit() throws Exception{
		
		System.out.println("THe view edit is "+viewEdit);
		
		Rule thisRule = new Rule();
		try {
			thisRule = RuleDAO.getInstance().getRuleById(ruleId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (thisRule.getType().equals("1")){
			String[] tmp2 = null;
			try {
				tmp2 = ProdCateDAO.getInstance().getProdCateNames();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer cate = new StringBuffer();
			String[] cateList = RuleCateDAO.getInstance().getCateNamesByRuleId(thisRule.getRuleId().toString());
			cate.append(cateList);
			for (int i = 1;i<cateList.length;i++){
				System.out.println(cateList[i]);
				cate.append(","+cateList[i]);
			}
			cates = cate.toString();
			System.out.println("cate list"+cates);

			condition = thisRule.getCondition();
			if (condition.equals("||"))
				condition = "ALL";
			else
				condition = "Any";
			rulename = thisRule.getRuleName();
			ruleDess = thisRule.getRuleDescr();
			rulename = rulename.replace(" ","%20");
			System.out.println("jj"+ruleDess);
			attribute = thisRule.getAttributes();
			operator = thisRule.getOperators();
			value = thisRule.getValues();
			//get current cate here;
			
			
			for (int i = 0;i<attribute.length;i++){
				System.out.println("product "+attribute[i]);
				System.out.println("operator "+operator[i]);
				System.out.println("value "+value[i]);

			}
			if (viewEdit.equals("view")){
				return "goToViewProductThreshold";
			}
			else if (viewEdit.equals("edit")){
				return "goToEditProductThreshold";
			}
		} else if (thisRule.getType().equals("5")) {
			FeeDAO feeDAO = FeeDAO.getInstance();
			this.storeFeeList = feeDAO.getByType("store");
			this.warehouseFeeList = feeDAO.getByType("warehouse");
			this.vendorFeeList = feeDAO.getByType("vendor");
			this.ruleName = thisRule.getRuleName();
			this.setRuleDescription(thisRule.getRuleDescr());
			return "goToCostCalculation";
		} 
		else if (thisRule.getType().equals("3")) {
			String[] tmp2 = null;
			try {
				tmp2 = ProdCateDAO.getInstance().getProdCateNames();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer cate = new StringBuffer();
			cate.append(tmp2[0]);
			for (int i = 1;i<tmp2.length;i++){
				System.out.println(tmp2[i]);
				cate.append(","+tmp2[i]);
			}

			prodCate = cate.toString();

			System.out.println("WTF"+prodCate);
			rulename = thisRule.getRuleName();
			ruleDess = thisRule.getRuleDescr();
			operator = thisRule.getOperators();
			value = thisRule.getValues();
			sources = thisRule.getRoutes();
			System.out.println("rule name is"+rulename);
			if (viewEdit.equals("view")){
				return "goToViewSpecialRoute";
			}
			else if (viewEdit.equals("edit")){
				return "goToEditSpecialRoute";
			}
			else if (viewEdit.equals("ables")){
				System.out.println("getting albe!!!");
				if (thisRule.getAble()  == true){
					thisRule.setAble(false);
				}
				else{
					thisRule.setAble(true);
				}
				RuleDAO.getInstance().update(thisRule);
				WriteDrl wdrl = new WriteDrl();
				return "enableSuccess";
			}
		}
		else {
			return "goToEditProductThreshold";
		}
		System.out.println("Wrong input for viewEdit rule list page");
		return "gotoEditProductThreshold";
	}
	public String reRank(){
		System.out.println("!!!!!!!!!!!"+ruleString);
		int pre = 0;
		int mid = 0;
		int last = 0;
		try {
			allRule = RuleDAO.getInstance().getAllSortedListFromStageOne() ;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Rule[] preRule = null;
		for (int i = 0; i<allRule.length;i++){
			if (allRule[i].getType().equals("n")){
				if (allRule[i].getPriority()>0){
					pre++;
				}
				else{
					last++;
				}
			}
		}
		mid = allRule.length-pre-last;
		System.out.println("all rule number is "+allRule.length);
		System.out.println("pre rule number is "+pre);
		System.out.println("mid rule number is "+mid);
		System.out.println("last rule number is "+last);
		String[] ruleStringList = ruleString.split(",");
		Rule[] newRule = new Rule[allRule.length];
		for (int i = 0 ; i < newRule.length; i++){
			newRule[i] = findRule(allRule, ruleStringList[i]);
			System.out.println("New rule "+i+" "+newRule[i].getRuleName());
		}


		return "success";
	}

	public Rule findRule(Rule[] rule, String find){
		Rule thisRule = new Rule();
		for (int i = 0; i<rule.length; i++){
			if (rule[i].getRuleName().equalsIgnoreCase(find)){
				thisRule = rule[i];
			}
		}
		return thisRule;
	}

	public String updateRule(){

		categoryname =categoryname.replace("%20", " ");
		rulename = rulename.replace("%20", " ");
		des = des.replace("%20", " ");

		System.out.println("input rule"+rulename);
		String[] categoryList= categoryname.split(",");

		Rule hahaRule = new Rule();
		Rule[] ruleList = null;

		try{
				//hahaRule = RuleDAO.getInstance().getRuleByName(rulename);
				//System.out.println("this rule Id is !!!!!!!!!"+hahaRule.getRuleId());
				ruleList =RuleDAO.getInstance().getAllSortedListFromStageOne();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i<ruleList.length;i++){
			System.out.println("!!fsdfjdsakfasdkfjasdlkfjadslkjflkasjflkasdjfl"+ruleList[i].getRuleName()+"  "+rulename);
			if ((ruleList[i].getRuleName().replace("%20"," ")).equals(rulename)){
				System.out.println("found it");
				hahaRule = ruleList[i];
				break;
			}
		}

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

		String[] product = null;

		//System.out.println("first instance of catelist is "+cateList[0]);
		try {
			product = ProdCateDAO.getInstance().getSKUByCategory(cateList);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("product length is "+ product.length);
		System.out.println("hahahah first product in the list is "+product[0]);

		String[] action = new String[1];
		action[0] = "";

		String[] route = new String[1];
		route[0] = "";
		System.out.println("rule id "+hahaRule.getRuleId());
		System.out.println("rulename pre "+rulename);
		System.out.println("rulename after "+rulenames);
		UpdateTemplate updateTemplate= new UpdateTemplate(hahaRule.getRuleId(),rulename,rulenames,des,product,attribute,operator,value,conditions,route,action,"TH-A,ST-A,SP-A");

		return "success";
	}
	public ArrayList<Fee> getStoreFeeList() {
		return storeFeeList;
	}
	public void setStoreFeeList(ArrayList<Fee> storeFeeList) {
		this.storeFeeList = storeFeeList;
	}
	public ArrayList<Fee> getWarehouseFeeList() {
		return warehouseFeeList;
	}
	public void setWarehouseFeeList(ArrayList<Fee> warehouseFeeList) {
		this.warehouseFeeList = warehouseFeeList;
	}
	public ArrayList<Fee> getVendorFeeList() {
		return vendorFeeList;
	}
	public void setVendorFeeList(ArrayList<Fee> vendorFeeList) {
		this.vendorFeeList = vendorFeeList;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
}
