package com.dicks.action;

import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.StoreCate;
import com.dicks.pojo.StoreCateId;
import com.opensymphony.xwork2.ActionSupport;

public class EditCategoryAction extends ActionSupport{
	private String categoryId;
	private String categoryName;
	private String categoryDescr;
	private String storeIdString;
	
	public String editCategory(){
		String[] ids = storeIdString.split(",");
		StoreCate[] news = new StoreCate[ids.length];
		for(int i=0; i<ids.length; i++){
			StoreCateId storeCateId = new StoreCateId(Integer.valueOf(categoryId), Integer.valueOf(ids[i])) ;
			StoreCate storeCate = new StoreCate(storeCateId, null, categoryName, categoryDescr);	
			news[i] = storeCate;
		}
		try {
			StoreCateDAO.getInstance().update(news);
		} catch (Exception e) {
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
	

}
