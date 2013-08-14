package com.dicks.action;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.ProdCateId;
import com.dicks.pojo.StoreCate;
import com.dicks.pojo.StoreCateId;
import com.opensymphony.xwork2.ActionSupport;

public class ViewAndEditCategoryAction extends ActionSupport{
	private String categoryId;
	private String categoryName;
	private String categoryDescr;
	private String storeIdString;
	private String skuString;
	
	public String viewStoreCategory(){
		if(categoryId!= null){
			try {
				StoreCate[] storeCates = StoreCateDAO.getInstance().getStoreCategoryListById(getCategoryId());
				this.setCategoryName(storeCates[0].getCateName());
				this.setCategoryDescr(storeCates[0].getCateDescr());
				StringBuffer sb = new StringBuffer();
				for(int i=0; i<storeCates.length;i++){
					sb.append(storeCates[i].getId().getStoreId()).append(",");
				}
				sb.deleteCharAt(sb.length()-1);
				this.setStoreIdString(sb.toString());
			} catch (Exception e) {
				return ERROR;
			}
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String viewProdCategory(){
		if(categoryId!= null){
			try{
				ProdCate[] prodCates = ProdCateDAO.getInstance().getProdCategoryListById(getCategoryId());
				this.setCategoryName(prodCates[0].getCateName());
				this.setCategoryDescr(prodCates[0].getCateDescr());
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i<prodCates.length;i++){
					sb.append(prodCates[i].getProduct().getSku()).append(",");
				}
				sb.deleteCharAt(sb.length()-1);
				this.setSkuString(sb.toString());
			} catch (Exception e) {
				return ERROR;
			}
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String editStoreCategory(){
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
	
	public String editProdCategory(){
		String[] skus = skuString.split(",");
		ProdCate[] news = new ProdCate[skus.length];
		try {
		for(int i=0; i<skus.length; i++){
			int prodId = ProductDAO.getInstance().getProductById(skus[i]).getProdId();
			ProdCateId prodCateId = new ProdCateId(Integer.valueOf(categoryId), prodId) ;
			ProdCate prodCate = new ProdCate(prodCateId, null, categoryName, categoryDescr);	
			news[i] = prodCate;
		}
		
		ProdCateDAO.getInstance().update(news);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String viewCate2EditCate(){
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
	
	
}
