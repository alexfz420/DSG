package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.FeeDAO;
import com.dicks.dao.OrdersDAO;
import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.engine.CreateTemplate;
import com.dicks.engine.UpdateTemplate;
import com.dicks.engine.WriteDrl;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Orders;
import com.dicks.pojo.Product;
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
	
	public String des;
	public String[] action;
	public String viewEdit;
	public String[] sources;
	private String ruleName;
	private String ruleDescription;
	private String thisValue;
	private String thisSources;
	private String thisOperator;
	private String cateList ;
	private Rule rule;
	public String[] productcount;
	
	public String prodCate;
	public String storeCate;
	public String prodd;
	public String storeProduct;
	public String prodLists;
	
	public void setProductLists(String s){
		this.prodLists = s;
	}
	
	public String getProductLists(){
		return prodLists;
	}
	
	public void setStoreProduct(String s){
		this.storeProduct = s;
	}
	
	public String getStoreProduct(){
		return storeProduct;
	}
	private void setProd(String s){
		this.prodd = s;
	}
	
	private String getProdd(){
		return prodd;
	}
	public void setProductcount(String[] a){
		this.productcount = a;
	}

	public String[] getProductcount(){
		return productcount;
	}
	
	private void setCateList(String cateList){
		this.cateList = cateList;
	}
	
	private String getCateList(){
		return cateList;
	}
	
	
	public void setThisValue(String a){
		this.thisValue = a;
	}
	
	public String getThisValue(){
		return thisValue;
	}
	
	
	public void setThisSources(String a){
		this.thisSources = a;
	}
	
	public String getThisSources(){
		return thisSources;
	}
	
	public void setThisOperator(String a){
		this.thisOperator = a;
	}
	
	public String getThisOperator(){
		return thisOperator;
	}
	
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

		


		return "success";
	}


	public String goToEdit() throws Exception{
		
		System.out.println("The view edit is "+viewEdit);
		
		//get prodcate and store cate
		
		String[] temp2 = null;
		String[] temp3 = null;
		String[] temp4 = null;
		try {
			temp4 = ProductDAO.getInstance().getAllNames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer prodList = new StringBuffer();
		prodList.append(temp4[0]);
		for (int i = 1;i<temp4.length;i++){
			//System.out.println(tmp2[i]);
			prodList.append(","+temp4[i]);
		}
		prodLists = prodList.toString();
		
		try {
			temp2 = ProdCateDAO.getInstance().getProdCateNames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer catess = new StringBuffer();
		catess.append(temp2[0]);
		for (int i = 1;i<temp2.length;i++){
			//System.out.println(tmp2[i]);
			catess.append(","+temp2[i]);
		}
		try {
			temp3 = StoreCateDAO.getInstance().getStoreCateNames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer catesss = new StringBuffer();
		catesss.append(temp3[0]);
		for (int i = 1;i<temp3.length;i++){
			//System.out.println(tmp2[i]);
			catesss.append(","+temp3[i]);
		}
		storeCate = catesss.toString();
		prodCate = catess.toString();
		
		
		
		
		
		Rule thisRule = new Rule();
		try {
			thisRule = RuleDAO.getInstance().getRuleById(ruleId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] cateLists = null;
		try {
			cateLists = RuleCateDAO.getInstance().getCateNamesByRuleId(thisRule.getRuleId().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer cateBuffer = new StringBuffer();
		/*cateBuffer.append(cateLists[0]);
		for (int i = 1; i<cateLists.length;i++){
			cateBuffer.append(","+cateLists[i]);
		}
		
		cateList = cateBuffer.toString();*/
	
		System.out.println("!!!!!!!Cate"+cateLists.length);
		if (thisRule.getType().equals("1")){
			System.out.println("checking type 1");
			String[] tmp2 = null;
			try {
				tmp2 = ProdCateDAO.getInstance().getProdCateNames();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer cate = new StringBuffer();
			System.out.println("this rule "+thisRule.getRuleName());
			String[] cateList = RuleCateDAO.getInstance().getCateNamesByRuleId(thisRule.getRuleId().toString());
			cate.append(cateList[0]);
			for (int i = 1;i<cateList.length;i++){
				System.out.println(i+" cate is "+cateList[i]);
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
			else if (viewEdit.equals("ables")){
				System.out.println("getting albe!!!");
				if (thisRule.getAble()  == true)
				{
					thisRule.setAble(false);
				}
				else
				{
					thisRule.setAble(true);
				}
				RuleDAO.getInstance().update(thisRule);
				WriteDrl wdrl = new WriteDrl();
				return "enableSuccess";
			}
		}
		else if (thisRule.getType().equals("2")){
			
			//get product list
			
			/*Product[] pp = ProductDAO.getInstance().getProductsBySKUList(thisRule.getRoutes());
			StringBuffer ppd = new StringBuffer();
			ppd.append(pp[0].getSku());
			for (int i = 0;i<pp.length;i++){
				ppd.append(","+pp[i].getSku());
			}
			prodd = ppd.toString();*/
			prodd = thisRule.getRoute();
			
			System.out.println("checking type 2");
			String[] tmp2 = null;
			try {
				tmp2 = ProdCateDAO.getInstance().getProdCateNames();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			StringBuffer cate = new StringBuffer();
			String[] cateList = RuleCateDAO.getInstance().getCateNamesByRuleId(thisRule.getRuleId().toString());
			cate.append(cateList[0]);
			for (int i = 1;i<cateList.length;i++){
				System.out.println(i+" cate is "+cateList[i]);
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
			System.out.println("jj2"+ruleDess);
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
				return "goToViewStoreThreshold";
			}
			else if (viewEdit.equals("edit")){
				return "goToEditStoreThreshold";
			}
			else if (viewEdit.equals("ables")){
				System.out.println("getting albe!!!");
				if (thisRule.getAble()  == true)
				{
					thisRule.setAble(false);
				}
				else
				{
					thisRule.setAble(true);
				}
				RuleDAO.getInstance().update(thisRule);
				WriteDrl wdrl = new WriteDrl();
				return "enableSuccess";
			}
		}
		else if (thisRule.getType().equals("5")) {
			System.out.println("checking type 5");
			FeeDAO feeDAO = FeeDAO.getInstance();
			this.storeFeeList = feeDAO.getByType("store");
			this.warehouseFeeList = feeDAO.getByType("warehouse");
			this.vendorFeeList = feeDAO.getByType("vendor");
			this.ruleName = thisRule.getRuleName();
			this.setRuleDescription(thisRule.getRuleDescr());
			this.rule = thisRule;
			return "goToCostCalculation";
		} else if (thisRule.getType().equals("6")) {
			System.out.println("checking type 6");
			this.rule = thisRule;
			this.ruleName = thisRule.getRuleName();
			this.ruleDescription = thisRule.getRuleDescr();
			return "goToEvaluationMethod";
		} 
		else if (thisRule.getType().equals("3")) {
			System.out.println("checking type 3");
			String[] tmp2 = null;
			try {
				tmp2 = ProdCateDAO.getInstance().getProdCateNames();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer cate = new StringBuffer();
			String[] cateList = RuleCateDAO.getInstance().getCateNamesByRuleId(thisRule.getRuleId().toString());
			cate.append(cateList[0]);
			for (int i = 1;i<cateList.length;i++){
				System.out.println(i+" cate is "+cateList[i]);
				cate.append(","+cateList[i]);
			}
			cates = cate.toString();
			System.out.println("cate list"+cates);
			
			

			System.out.println("WTF"+prodCate);
			rulename = thisRule.getRuleName();
			ruleDess = thisRule.getRuleDescr();
			operator = thisRule.getOperators();
			value = thisRule.getValues();
			sources = thisRule.getRoutes();
			System.out.println("this value "+value[0]);
			thisOperator  = operator[0];
			thisValue = value[0];
			thisSources = sources[0];
			
			
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
		System.out.println("updating category"+categoryname);
		System.out.println("input rule"+rulename);
		String[] categoryList= categoryname.split(",");

		Rule hahaRule = new Rule();
		Rule[] ruleLists = null;

		try{
				//hahaRule = RuleDAO.getInstance().getRuleByName(rulename);
				//System.out.println("this rule Id is !!!!!!!!!"+hahaRule.getRuleId());
				ruleLists =RuleDAO.getInstance().getAllSortedListFromStageOne();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i<ruleLists.length;i++){
			System.out.println("!l"+ruleLists[i].getRuleName()+"  "+rulename);
			if ((ruleLists[i].getRuleName().replace("%20"," ")).equals(rulename)){
				System.out.println("found it");
				hahaRule = ruleLists[i];
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
			cateList[i] = categoryList[i].trim();
			System.out.println("cate 1 !"+cateList[i]);
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
		for (int i = 0; i<product.length;i++){
			System.out.println(i+" "+"product is "+product[i]);
		}
		
		String[] action = new String[1];
		action[0] = "";

		String[] route = new String[1];
		route[0] = "";
		System.out.println("rule id "+hahaRule.getRuleId());
		System.out.println("rulename pre "+rulename);
		System.out.println("rulename after "+rulenames);
		UpdateTemplate updateTemplate= new UpdateTemplate(hahaRule.getRuleId(),rulename,rulenames,des,product,attribute,operator,value,conditions,route,action,"TH-A,ST-A,SP-A",cateList,true);
		WriteDrl wdl = new WriteDrl();
		return "success";
	}
	
	public String updateRuleSpecial(){

		categoryname =categoryname.replace("%20", " ");
		rulename = rulename.replace("%20", " ");
		des = des.replace("%20", " ");
		System.out.println("input category"+categoryname);
		categoryname = categoryname.trim();
		Rule hahaRule = new Rule();
		Rule[] ruleLists = null;

		try{
				//hahaRule = RuleDAO.getInstance().getRuleByName(rulename);
				//System.out.println("this rule Id is !!!!!!!!!"+hahaRule.getRuleId());
				ruleLists =RuleDAO.getInstance().getAllSortedListFromStageOne();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i<ruleLists.length;i++){
			System.out.println("!!fsdfjdsakfasdkfjasdlkfjadslkjflkasjflkasdjfl"+ruleLists[i].getRuleName()+"  "+rulename);
			if ((ruleLists[i].getRuleName().replace("%20"," ")).equals(rulename)){
				System.out.println("found it");
				hahaRule = ruleLists[i];
				break;
			}
		}
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
		
		 String[] action = new String[1];
		 action[0] = "special";
		 
		String flag = "TH-A,ST-A,SP-A";
		System.out.println("rulename "+rulename);
		System.out.println("rulename!!!"+rulenames);
		System.out.println("des "+des);
		System.out.println("operator "+operator[0]);
		System.out.println("prodcut "+productcount);
		UpdateTemplate updateTemplate= new UpdateTemplate(hahaRule.getRuleId(),rulename,rulenames,des,product,aa,operator,value," ",productcount,action,flag,cateList,true);
		WriteDrl wdl = new WriteDrl();
		return "success";
	}
	
	
	public String updateStoreThreshold(){
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+categoryname);
		categoryname =categoryname.replace("%20", " ");
		rulename = rulename.replace("%20", " ");
		System.out.println("input des"+des);
		des = des.replace("%20", " ");
		System.out.println("replace des"+des);
		Rule hahaRule = new Rule();
		Rule[] ruleLists = null;
		
		System.out.println("store Prodct before "+storeProduct);
		String[] storeProducts = storeProduct.split(",");
		int prodLength = 0;
		for (int j = 0 ; j<storeProducts.length;j++){
			if ((storeProducts[j] != null) && (!storeProducts[j].equals(" "))){
				prodLength++;
			}
		}
		String [] prodList = new String[prodLength];
		for (int i = 0; i<prodList.length;i++){
			prodList[i] = storeProducts[i];
		}
		System.out.println("prodList "+prodList.length);
		try{
			ruleLists =RuleDAO.getInstance().getAllSortedListFromStageOne();
		}
		catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		
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
		String type = "Store Threshold";
		
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
		
		for (int i = 0; i<ruleLists.length;i++){
			System.out.println("!l"+ruleLists[i].getRuleName()+"  "+rulename);
			if ((ruleLists[i].getRuleName().replace("%20"," ")).equals(rulename)){
				System.out.println("found it");
				hahaRule = ruleLists[i];
				break;
			}
		}
		
		System.out.println("product size is "+product.length);
		//no available action right now
		String[] action = new String[1];
		action[0] = "retract";
		//no route needed 
		String[] route = new String[1];
		route[0] = " ";
		
		
		System.out.println("rulename "+rulename);
		
		
		for (int i = 0;i<product.length;i++){
			System.out.println("prod "+product[i]);
		}
		for (int i = 0; i < attribute.length; i++){
			System.out.println("att "+attribute[i]);
			System.out.println("oper "+operator[i]);
			System.out.println("value "+value[i]);
		}
		
		conditions ="&&";
		System.out.println("des "+des);
		UpdateTemplate updateTemplate= new UpdateTemplate(hahaRule.getRuleId(),rulename,rulenames,des,product,attribute,operator,value,conditions,route,action,"TH-A,ST-A,SP-A",cateList,false);
		WriteDrl wdl = new WriteDrl();
		
		return "updateStoreThreshold";
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

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
}
