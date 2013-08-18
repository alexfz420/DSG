package com.dicks.action;

import java.util.ArrayList;
import java.util.List;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.ProdCateId;
import com.dicks.pojo.Rule;
import com.dicks.pojo.RuleCate;
import com.dicks.pojo.RuleCateId;
import com.dicks.pojo.StoreCate;
import com.dicks.pojo.StoreCateId;
import com.opensymphony.xwork2.ActionSupport;

public class ViewAndEditCategoryAction extends ActionSupport {
	private String categoryId;
	private String categoryName;
	private String categoryDescr;
	private String storeIdString;
	private String skuString;
	private String appliedRuleString;
	private String previousAppliedRuleString;
	private String ruleList;

	public String viewStoreCategory() {
		if (categoryId != null) {
			try {
				StoreCate[] storeCates = StoreCateDAO.getInstance()
						.getStoreCategoryListById(getCategoryId());

				this.setCategoryName(storeCates[0].getCateName());
				this.setCategoryDescr(storeCates[0].getCateDescr());

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < storeCates.length; i++) {
					sb.append(storeCates[i].getId().getStoreId()).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				getRuleInfo();
				this.setStoreIdString(sb.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
				return ERROR;
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String viewProdCategory() {
		if (categoryId != null) {
			try {
				ProdCate[] prodCates = ProdCateDAO.getInstance()
						.getProdCategoryListById(getCategoryId());
				this.setCategoryName(prodCates[0].getCateName());
				this.setCategoryDescr(prodCates[0].getCateDescr());
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < prodCates.length; i++) {
					sb.append(prodCates[i].getProduct().getSku()).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);

				getRuleInfo();

				this.setSkuString(sb.toString());
			} catch (Exception e) {
				return ERROR;
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String editStoreCategory() {
		String[] ids = storeIdString.split(",");
		try {
			// update applied rules, false means its Store group
			updateAppliedRules(false, ids);

			// update Store Category table
	 		StoreCate[] news = new StoreCate[ids.length];
			for (int i = 0; i < ids.length; i++) {
				StoreCateId storeCateId = new StoreCateId(
						Integer.valueOf(categoryId), Integer.valueOf(ids[i]));
				StoreCate storeCate = new StoreCate(storeCateId, null,
						categoryName, categoryDescr);
				news[i] = storeCate;
			}

			StoreCateDAO.getInstance().update(news);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}

	public String editProdCategory() {
		String[] skus = skuString.split(",");
		try {
			// update applied rules including RuleCate table and Rule Table

			updateAppliedRules(true, skus);

			// update ProdCate table

			ProdCate[] news = new ProdCate[skus.length];
			for (int i = 0; i < skus.length; i++) {
				int prodId = ProductDAO.getInstance().getProductById(skus[i])
						.getProdId();
				ProdCateId prodCateId = new ProdCateId(
						Integer.valueOf(categoryId), prodId);
				ProdCate prodCate = new ProdCate(prodCateId, null,
						categoryName, categoryDescr);
				news[i] = prodCate;
			}
			
			ProdCateDAO.getInstance().update(news);
		} catch (Exception e) {
			System.out.println("!!!" + e.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	private void getRuleInfo() throws Exception {
		// get rule information
		Rule[] rules = RuleCateDAO.getInstance()
				.getRuleListByCateId(categoryId);
		if (rules == null) {
			setAppliedRuleString(null);
			return;
		}
		StringBuffer sb1 = new StringBuffer();
		for (Rule rule : rules) {
			sb1.append(rule.getRuleName()).append(",");
		}
		if(sb1.length()>0){
			sb1.deleteCharAt(sb1.length()-1);

		}
		setAppliedRuleString(sb1.toString());
	}

	public String viewCate2EditProdCate() {
		List<String> ruleNames;
		try {
			ruleNames = RuleDAO.getInstance().getRuleNamesForProduct();
			if(ruleNames!=null){
				this.setRuleList(getRuleListString(ruleNames));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String viewCate2EditStoreCate() {
		List<String> ruleNames;
		try {
			ruleNames = RuleDAO.getInstance().getRuleNamesForStore();
			if(ruleNames==null||ruleNames.size()==0){}
			else{
				System.out.println("!!!!"+ruleNames.size());
				this.setRuleList(getRuleListString(ruleNames));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescr() {
		return categoryDescr;
	}

	public void setCategoryDescr(String categoryDescr) {
		this.categoryDescr = categoryDescr;
	}

	public String getStoreIdString() {
		return storeIdString;
	}

	public void setStoreIdString(String storeIdString) {
		this.storeIdString = storeIdString;
	}

	public String getSkuString() {
		return skuString;
	}

	public void setSkuString(String skuString) {
		this.skuString = skuString;
	}

	public String getAppliedRuleString() {
		return appliedRuleString;
	}

	public void setAppliedRuleString(String appliedRuleString) {
		this.appliedRuleString = appliedRuleString;
	
	}

	private String getRuleListString(List<String> ruleNames){
		StringBuffer sb = new StringBuffer();
		for(String str : ruleNames){
			sb.append(str).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	private void deleteRule(String[] ruleNamesForDelete, String[] array) throws Exception {
		
		List<Rule> rulesForDelete = new ArrayList<Rule>();
		for (int i = 0; i < ruleNamesForDelete.length; i++) {
			if (ruleNamesForDelete[i] != null) {
				rulesForDelete.add(RuleDAO.getInstance().getRuleByName(ruleNamesForDelete[i]));
			}
		}
		if(rulesForDelete.size()>0){
			RuleDAO.getInstance().updateProdObjForDelete(array, rulesForDelete);
		}
	}

	
	
	// array is skus
	private void updateAppliedRules(boolean isProductRelated, String[] array)
			throws Exception {
		// if ruleString is null, do the delete
		if (appliedRuleString == null || appliedRuleString.trim().length() == 0) {
			if (getPreviousAppliedRuleString() != null
					&& getPreviousAppliedRuleString().trim().length() != 0) {
				String[] rules = previousAppliedRuleString.split(",");
				deleteRule(rules,array);
				
				//delete rule cate table
				for (int i = 0; i < rules.length; i++) {
					Rule rule = RuleDAO.getInstance().getRuleByName(rules[i]);
					int ruleId = rule.getRuleId();
					RuleCateId rcId = new RuleCateId(Integer.valueOf(categoryId),								ruleId);
					RuleCate rc = new RuleCate(rcId, null, isProductRelated);
					RuleCateDAO.getInstance().delete(rc);
				}		
				return;
			}
			else return;			
		}
		
		// update rulecate table
		String[] ruleNames = appliedRuleString.split(",");
		RuleCate[] rcs = new RuleCate[ruleNames.length];
		List<Rule> rules = new ArrayList<Rule>();
		for (int i = 0; i < ruleNames.length; i++) {
			Rule rule = RuleDAO.getInstance().getRuleByName(ruleNames[i]);
			rules.add(rule);
			int ruleId = rule.getRuleId();
			RuleCateId rcId = new RuleCateId(Integer.valueOf(categoryId),
					ruleId);
			RuleCate rc = new RuleCate(rcId, null, isProductRelated);
			rcs[i] = rc;
		}
		RuleCateDAO.getInstance().update(rcs);

		// update rule table
		if (isProductRelated) {
			RuleDAO.getInstance().updateProdObjForUpdate(array, rules);
		} else {
			RuleDAO.getInstance().updateProdObjForUpdate(array, rules);
		}

		if (getPreviousAppliedRuleString() != null
				&& getPreviousAppliedRuleString().trim().length() != 0) {
			String[] previousRules = getPreviousAppliedRuleString().split(
					",");
			for (int i = 0; i < previousRules.length; i++) {
				for (Rule rule : rules) {
					if (rule.getRuleName().equals(previousRules[i])) {
						previousRules[i] = null;
						break;
					}
				}
			}
			deleteRule(previousRules,array);
		}
	

	}

	public String getPreviousAppliedRuleString() {
		return previousAppliedRuleString;
	}

	public void setPreviousAppliedRuleString(String previousAppliedRuleString) {
		this.previousAppliedRuleString = previousAppliedRuleString;
	}

	
	public String getRuleList() {
		return ruleList;
	}

	public void setRuleList(String ruleList) {
		this.ruleList = ruleList;
	}

}
