package com.dicks.action;

import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.StoreCate;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCategory extends ActionSupport{
	
	private String act;
	private String ids;
	
	public String execute() throws Exception {
		String[] idArray =ids.split(",");
		if("store".equals(act)){
			
			//StoreCateDAO.getInstance().deleteCategory
		}else if("product".equals(ids)){
			
		}
		return SUCCESS;
	}
	
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
