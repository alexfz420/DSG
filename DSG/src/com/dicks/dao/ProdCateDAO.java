package com.dicks.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.ProdCate;
import com.dicks.pojo.Product;
import com.dicks.pojo.StoreCate;


public class ProdCateDAO extends BaseDao<ProdCate>{
	private static ProdCateDAO instance = new ProdCateDAO();
	
	public ProdCateDAO() {
		super(ProdCate.class);
	}
	
	public static ProdCateDAO getInstance (){
		return instance;
	}
	
	public String[] getProdCateNames () throws Exception{
		ProdCate[] prodCates = getProdCategoryList();
		String[] names = new String[prodCates.length];
		
		for(int i = 0 ; i<names.length ;i++){
			names[i]=prodCates[i].getCateName();
		}
		return names;
	}
	
	public Product[] getProductByCategory(String[] categoryNameList) throws Exception{
		List<Product> finalResult = new LinkedList<Product>();
		for(String categoryName : categoryNameList){
			List<Criterion> criterions = new ArrayList<Criterion>();
			Criterion criterion = Restrictions.eq("cateName", categoryName);
			criterions.add(criterion);
			List<ProdCate> result =  super.getList(criterions);
			for(ProdCate pc : result){
				finalResult.add(pc.getProduct());
			}
		}
		Product[] products = (Product[])finalResult.toArray(new Product[finalResult.size()]);
		return products;	
	}
	
	
	
	public String[] getSKUByCategory(String[] categoryNameList) throws Exception{
		Product[] products = getProductByCategory(categoryNameList);
		String[] skuArray = new String[products.length];
		for(int i=0;i<products.length;i++){
			skuArray[i] = products[i].getSku();
		}
		return skuArray;
	}
	


	public ProdCate[] getProdCategoryList() throws Exception{
		ArrayList<ProdCate> cateList =  (ArrayList<ProdCate>) super.getList();
		ProdCate[] array = (ProdCate[])cateList.toArray(new ProdCate[cateList.size()]); 
		return filterCate(array);
	}
	
	public List<Integer> getAllIds() throws Exception{
		List<Integer> result = new ArrayList<Integer>();
		for(ProdCate pc : getProdCategoryList()){
			result.add(pc.getId().getCateProdId());
		}
		return result;
	}

	public int getMaxId() throws Exception{
		String sql = "select max(cate_prod_id) maxid from prod_cate";
		Object result = HibernateUtil.getSession().createSQLQuery(sql).uniqueResult();
		if(null == result) return 0;
		return (Integer) result;
		
	}
	
	public void createCategory(ProdCate prodCate) throws Exception{	
		super.create(prodCate);
	}
	
	public int getNewId() throws Exception{
		int id1 = getMaxId();
		int id2 = StoreCateDAO.getInstance().getMaxId();
		return (Math.max(id1, id2)+1);
	}

	private ProdCate[] filterCate(ProdCate[] storeCates){
		
		if(storeCates==null) return null;
		List<ProdCate> storeCates1 = new ArrayList<ProdCate>();
		int id = 0;
		for(int i=0; i<storeCates.length ; i++){
			if(i==0){
				id=	storeCates[i].getId().getCateProdId();
				storeCates1.add(storeCates[i]);
			}
			if(storeCates[i].getId().getCateProdId()!=id){
				storeCates1.add(storeCates[i]);
				id =storeCates[i].getId().getCateProdId();
			}
		}
		ProdCate[] result = (ProdCate[])storeCates1.toArray(new ProdCate[storeCates1.size()]); 
		 return result;
	}

	public ProdCate[] getProdCategoryListById(String id) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.cateProdId", Integer.valueOf(id));
		criterions.add(criterion);
		ArrayList<ProdCate> finalResult = (ArrayList<ProdCate>)super.getList(criterions);
		return (ProdCate[])finalResult.toArray(new ProdCate[finalResult.size()]);
	} 
	
	
	public void update(ProdCate[] prodCates) throws Exception{
		String cateId = prodCates[0].getId().getCateProdId()+"";
		ProdCate[] preview = getProdCategoryListById(cateId);
		if(preview!=null){
			for(ProdCate sc: preview){
				super.delete(sc);
			}
		}
		
		if(prodCates!=null){
			for(ProdCate sc: prodCates){
				createCategory(sc);
			}
		}	
	}
	
	public int getCateIdByCateName(String cateName) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("cateName",cateName);
		criterions.add(criterion);
		ProdCate pc = ((List<ProdCate>)super.getList(criterions)).get(0);
		return pc.getId().getCateProdId();
	}

	public void deleteCategorys(String[] idArray) throws Exception {
		for(String id: idArray){
			ProdCate[] scs = getProdCategoryListById(id);
			String[] skus = new String[scs.length];
			for(int i =0; i <scs.length; i++ ){
				skus[i]=scs[i].getProduct().getSku();
			}
			RuleCateDAO.getInstance().deleteCategory(id, skus);
			for(ProdCate sc :scs){
				super.delete(sc);
			}
		}			
	}

	

}
