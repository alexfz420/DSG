package com.dicks.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.Rule;
import com.dicks.pojo.RuleCate;
import com.dicks.pojo.StoreCate;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCategoryAction extends ActionSupport {

	private String act;
	private String ids;

	public String execute() throws Exception {
		String[] idArray = ids.split(",");
		
		
		// delete rule table
		for (String str : idArray) {
			String[] array = null;
			if ("store".equals(act)) {
				StoreCate[] list = StoreCateDAO.getInstance()
						.getStoreCategoryListById(str);
				array = new String[list.length];
				for (int i = 0; i < list.length; i++) {
					array[i] = list[i].getId().getStoreId() + "";
				}
			} else if ("product".equals(act)) {
				ProdCate[] list = ProdCateDAO.getInstance()
						.getProdCategoryListById(str);
				array = new String[list.length];
				for (int i = 0; i < list.length; i++) {
					array[i] = list[i].getId().getProdId() + "";
				}
			}
			Rule[] rulesForDelete = RuleCateDAO.getInstance()
					.getRuleListByCateId(str);
			RuleDAO.getInstance().updateProdObjForDelete(array,
					Arrays.asList(rulesForDelete));

		// delete prodcate table or storecate table
		if ("store".equals(act)) {
			StoreCateDAO.getInstance().deleteCategorys(idArray);
		} else if ("product".equals(act)) {
			ProdCateDAO.getInstance().deleteCategorys(idArray);
		}

	

			// delete rulecate table
			for (String str1 : idArray) {
				List<RuleCate> ruleCates = RuleCateDAO.getInstance()
						.getRuleCateListByCateId(Integer.valueOf(str1));
				if (ruleCates == null || ruleCates.size() == 0) {
				} else {
					for (RuleCate rc : ruleCates) {
						RuleCateDAO.getInstance().delete(rc);
					}
				}
			}

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
