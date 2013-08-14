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
		//System.out.println("WTF");
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

	public int getMaxId() throws Exception{
		String sql = "select max(cate_prod_id) maxid from prod_cate";
		return (Integer)HibernateUtil.getSession().createSQLQuery(sql).uniqueResult();
		
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
		for(ProdCate sc: preview){
			super.delete(sc);
		}
		for(ProdCate sc: prodCates){
			createCategory(sc);
		}
		
	}


	

}
