package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Category;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.RuleCate;
import com.dicks.pojo.RuleCateId;
import com.dicks.pojo.StoreCate;

public class RuleCateDAO extends BaseDao<RuleCate> {
	private static RuleCateDAO instance = new RuleCateDAO();

	public RuleCateDAO() {
		super(RuleCate.class);
	}

	public static RuleCateDAO getInstance() {
		return instance;
	}
	
	public void create(RuleCate rc) throws Exception{
		super.create(rc);
	}

//	private Category[] getCategorysByRuleId(String ruleId) throws Exception{
//	
//	}

	public String[] getCateNamesByRuleId(String ruleId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.ruleId", Integer.valueOf(ruleId));
		criterions.add(criterion);
		List<RuleCate> result =  super.getList(criterions);
		List<Category> categorys = new ArrayList<Category>();
		for(RuleCate rc : result){
			if(rc.getIsProductRelated()){
				ProdCate prodCate = ProdCateDAO.getInstance().getProdCategoryListById(rc.getId().getCategoryId()+"")[0];
				categorys.add(prodCate);
			}else{
				StoreCate storeCate = StoreCateDAO.getInstance().getStoreCategoryListById(rc.getId().getCategoryId()+"")[0];
				categorys.add(storeCate);
			}
		}	
		Category[] categorysArray = (Category[])categorys.toArray(new Category[categorys.size()]);
		String[] names = new String[categorysArray.length];
		for(int i = 0; i< names.length;i++){
			names[i] = categorysArray[i].getCateName();
		}
		
		return names;
	}

	public void deleteCategory(String cateId, int[] stordIds) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.categoryId", Integer.valueOf(cateId));
		criterions.add(criterion);
		List<RuleCate> result =  super.getList(criterions);
		
		if(result==null||result.size()==0) return;
		Rule[] rules = new Rule[result.size()];
		int i = 0;
		for(RuleCate rc: result){
			rules[i] = rc.getRule();
			i++;
			super.delete(rc);
		}
		//RuleDAO.getInstance().updateStoreObject(rules,stordIds);
	}

	public void deleteCategory(String cateId, String[] skus) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.categoryId", Integer.valueOf(cateId));
		criterions.add(criterion);
		List<RuleCate> result =  super.getList(criterions);	
		
		if(result==null||result.size()==0) return;
		Rule[] rules = new Rule[result.size()];
		int i = 0;
		for(RuleCate rc: result){
			rules[i] = rc.getRule();
			i++;
			super.delete(rc);
		}
	//	RuleDAO.getInstance().updateProdObject(rules,skus);		
	}

	public Rule[] getRuleListByCateId(String cateId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.categoryId", Integer.valueOf(cateId));
		criterions.add(criterion);
		List<RuleCate> result =  super.getList(criterions);	
		if(result==null||result.size()==0) return null;

		Rule[] rules = new Rule[result.size()];
		int i = 0;
		for(RuleCate rc: result){
			rules[i] = rc.getRule();
			i++;
		}
		return rules;
	}
	
	public String[] getStoreCateNamesByRuleId(String ruleId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("id.ruleId", Integer.valueOf(ruleId));
		Criterion criterion2 = Restrictions.eq("isProductRelated",false);
		criterions.add(criterion1);
		criterions.add(criterion2);
		List<RuleCate> result =  super.getList(criterions);	
		if(result==null) return null;
		
		List<String> names = new ArrayList<String>();
		for(RuleCate rc: result){
			String cateId = rc.getId().getCategoryId()+"";
			StoreCate storeCate = StoreCateDAO.getInstance().getStoreCategoryListById(cateId)[0];
			names.add(storeCate.getCateName());     
		}
		return (String[])names.toArray(new String[names.size()]);
	}
	
	public String[] getProdCateNamesByRuleId(String ruleId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("id.ruleId", Integer.valueOf(ruleId));
		Criterion criterion2 = Restrictions.eq("isProductRelated",true);
		criterions.add(criterion1);
		criterions.add(criterion2);
		List<RuleCate> result =  super.getList(criterions);	
		if(result==null) return null;
		
		List<String> names = new ArrayList<String>();
		for(RuleCate rc: result){
			String cateId = rc.getId().getCategoryId()+"";
			ProdCate prodCate = ProdCateDAO.getInstance().getProdCategoryListById(cateId)[0];
			names.add(prodCate.getCateName());     
		}
		return (String[])names.toArray(new String[names.size()]);
	}
	

//	public void applyRule(int[] cateIds, int ruleId) throws Exception{
//		List<Integer> prodCateIds = ProdCateDAO.getInstance().getAllIds();
//		List<Integer> storeCateIds = StoreCateDAO.getInstance().getAllIds();
//		for(int id: cateIds){
//			//product category
//			RuleCateId rcId = new RuleCateId(id, ruleId);
//			RuleCate rc= null;
//			if(prodCateIds.contains(id)){
//				rc = new RuleCate(rcId,null,true);
//			}
//			//store category
//			else if(storeCateIds.contains(id)){
//				rc = new RuleCate(rcId,null,false);
//			}
//			if(rc!=null) super.create(rc);
//		}
//	}

	/**
	 * 
	 * @param rcs
	 * @throws Exception
	 */
	public void update(RuleCate[] rcs) throws Exception {
		int cateId = rcs[0].getId().getCategoryId();
		List<RuleCate> ruleCates = getRuleCateListByCateId(cateId);
		for(RuleCate rc: ruleCates){
			super.delete(rc);
		}
		for(RuleCate rc: rcs){
			create(rc);
		}
	}
	
	public List<RuleCate> getRuleCateListByCateId(int cateId) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.categoryId", cateId);
		criterions.add(criterion);
		List<RuleCate> result =  super.getList(criterions);	
		return result;
	}

	
	public void createRuleCate(int ruleId, String[] cateNames, boolean isProduct) throws Exception{
		if(cateNames==null || cateNames.length == 0 ) return;
		
		int[] cateIds = new int[cateNames.length]; 
		if(isProduct){
			for(int i = 0 ; i< cateNames.length ; i++){
				cateIds[i] = ProdCateDAO.getInstance().getCateIdByCateName(cateNames[i]);
			}
		}else{
			for(int i = 0 ; i< cateNames.length ; i++){
				cateIds[i] = StoreCateDAO.getInstance().getCateIdByCateName(cateNames[i]);
			}
		}
		
		for(int id : cateIds){
			RuleCateId rcId = new RuleCateId(id, ruleId);
			RuleCate rc = new RuleCate(rcId, null, true);
			super.create(rc);
		}
	}
	
}
