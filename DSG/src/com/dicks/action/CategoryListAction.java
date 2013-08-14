package com.dicks.action;

import java.util.ArrayList;
import java.util.List;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.Rule;
import com.dicks.pojo.RuleCate;
import com.dicks.pojo.StoreCate;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryListAction extends ActionSupport {
	private ProdCate[] prodCategoryList;
	private StoreCate[] storeCategoryList;
	private String act;
	

	private static final long serialVersionUID = 1L;
	
	public String displayCategory(){
		if("product".equals(act)){
			try {
				ProdCate[] all = ProdCateDAO.getInstance().getProdCategoryList();
				
				for(int i = 0;i<all.length;i++){
					Rule[] rules = RuleCateDAO.getInstance().getRuleListByCateId(all[i].getId().getCateProdId()+"");
					if(rules==null){
						all[i].setAppliedRuleList("No rule applied.");
						continue;
					}
					StringBuffer sb = new StringBuffer();
					for(Rule rule: rules){
						sb.append(rule.getRuleName()).append(", ");
					}
					sb.delete(sb.length()-2, sb.length()-1);
					all[i].setAppliedRuleList(sb.toString()); 
				}
				this.setProdCategoryList(all);	
			} catch (Exception e) {
				return ERROR;
			}
			return SUCCESS;
		}else if("store".equals(act)){ 
			try {
				StoreCate[] all = StoreCateDAO.getInstance().getStoreCategoryList();
				for(int i = 0;i<all.length;i++){
					Rule[] rules = RuleCateDAO.getInstance().getRuleListByCateId(all[i].getId().getCateStoreId()+"");
					if(rules==null){
						all[i].setAppliedRuleList("No rule applied.");
						continue;
					}
					StringBuffer sb = new StringBuffer();
					for(Rule rule: rules){
						sb.append(rule.getRuleName()).append(", ");
					}
					sb.delete(sb.length()-2, sb.length()-1);
					all[i].setAppliedRuleList(sb.toString()); 
				}
				this.setStoreCategoryList(all);
			} catch (Exception e) {
				System.out.println("!!!"+e.toString());
				return ERROR;
			}
			return SUCCESS;
		}
		return SUCCESS;
	}

	public ProdCate[] getProdCategoryList() {
		if(prodCategoryList!=null){
		}
		return prodCategoryList;
	}

	public void setProdCategoryList(ProdCate[] prodCategoryList) {
		this.prodCategoryList = prodCategoryList;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public StoreCate[] getStoreCategoryList() {
		return storeCategoryList;
	}

	public void setStoreCategoryList(StoreCate[] storeCategoryList) {
		this.storeCategoryList = storeCategoryList;
	}
	
	
//	private ProdCate[] filterProdCate(ProdCate[] prodCates){
//		if(prodCates==null) return null;
//		List<ProdCate> productCates = new ArrayList<ProdCate>();
//		int id = 0;
//		for(int i=0; i<prodCates.length ; i++){
//			if(i==0){
//				id=	prodCates[i].getId().getCateProdId();
//				productCates.add(prodCates[i]);
//			}
//			if(prodCates[i].getId().getCateProdId()!=id){
//				productCates.add(prodCates[i]);
//				id =prodCates[i].getId().getCateProdId();
//			}
//		}
//		return (ProdCate[])productCates.toArray(new ProdCate[productCates.size()]); 
//	} 
//	
//	private StoreCate[] filterStoreCate(StoreCate[] storeCates){
//		
//		if(storeCates==null) return null;
//		List<StoreCate> storeCates1 = new ArrayList<StoreCate>();
//		int id = 0;
//		for(int i=0; i<storeCates.length ; i++){
//			if(i==0){
//				id=	storeCates[i].getId().getCateStoreId();
//				storeCates1.add(storeCates[i]);
//			}
//			if(storeCates[i].getId().getCateStoreId()!=id){
//				storeCates1.add(storeCates[i]);
//				id =storeCates[i].getId().getCateStoreId();
//			}
//		}
//		 StoreCate[] result = (StoreCate[])storeCates1.toArray(new StoreCate[storeCates1.size()]); 
//		 return result;
//	} 
} 

