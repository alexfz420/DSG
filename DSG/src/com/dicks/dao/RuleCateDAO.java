package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Category;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.Product;
import com.dicks.pojo.RuleCate;
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
	
}
