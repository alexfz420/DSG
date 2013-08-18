package com.dicks.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.ProdCate;
import com.dicks.pojo.Product;
import com.dicks.pojo.Store;
import com.dicks.pojo.StoreCate;
import com.dicks.pojo.StoreCateId;

public class StoreCateDAO extends BaseDao<StoreCate> {
	private static StoreCateDAO instance = new StoreCateDAO();
	
	public StoreCateDAO() {
		super(StoreCate.class);
	}
	
	public static StoreCateDAO getInstance (){
		return instance;
	}
	
	public StoreCate[] getStoreCategoryListById(String id) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("id.cateStoreId", Integer.valueOf(id));
		criterions.add(criterion);
		ArrayList<StoreCate> finalResult = (ArrayList<StoreCate>)super.getList(criterions);
		return (StoreCate[])finalResult.toArray(new StoreCate[finalResult.size()]);
	}

	public String[] getStoreCateNames() throws Exception{
		StoreCate[] storeCates = getStoreCategoryList();
		String[] names = new String[storeCates.length];
		
		for(int i = 0 ; i<names.length ;i++){
			names[i]=storeCates[i].getCateName();
		}
		return names;
	}
	
	public StoreCate[] getStoreCategoryList() throws Exception{
		ArrayList<StoreCate> cateList =  (ArrayList<StoreCate>) super.getList();
		StoreCate[] array = (StoreCate[])cateList.toArray(new StoreCate[cateList.size()]); 
		return filterCate(array);
	}

	public void createCategory(StoreCate storeCate) throws Exception{
		super.create(storeCate);
	}
	
	public int getNewId() throws Exception{
		int id1 = getMaxId();
		int id2 = ProdCateDAO.getInstance().getMaxId();
		return Math.max(id1, id2)+1;
	}
	
	public int getMaxId() throws Exception{
		String sql = "select max(cate_store_id) maxid from store_cate";
		Object result = HibernateUtil.getSession().createSQLQuery(sql).uniqueResult();
		if(null == result) return 0;
		return (Integer) result;
	}
	
	public void update(StoreCate[] storeCates) throws Exception{
		String cateId = storeCates[0].getId().getCateStoreId()+"";
		StoreCate[] preview = getStoreCategoryListById(cateId);
		for(StoreCate sc: preview){
			super.delete(sc);
		}
		for(StoreCate sc: storeCates){
			createCategory(sc);
		}
		
	}
	
	private StoreCate[] filterCate(StoreCate[] storeCates){
		
		if(storeCates==null) return null;
		List<StoreCate> storeCates1 = new ArrayList<StoreCate>();
		int id = 0;
		for(int i=0; i<storeCates.length ; i++){
			if(i==0){
				id=	storeCates[i].getId().getCateStoreId();
				storeCates1.add(storeCates[i]);
			}
			if(storeCates[i].getId().getCateStoreId()!=id){
				storeCates1.add(storeCates[i]);
				id =storeCates[i].getId().getCateStoreId();
			}
		}
		 StoreCate[] result = (StoreCate[])storeCates1.toArray(new StoreCate[storeCates1.size()]); 
		 return result;
	}

	public Store[] getStoreByCategory(String[] categoryNameList) throws Exception{
		List<Store> finalResult = new LinkedList<Store>();
		for(String categoryName : categoryNameList){
			List<Criterion> criterions = new ArrayList<Criterion>();
			Criterion criterion = Restrictions.eq("cateName", categoryName);
			criterions.add(criterion);
			List<StoreCate> result =  super.getList(criterions);
			for(StoreCate sc : result){
				finalResult.add(sc.getStore());
			}
		}
		Store[] stores = (Store[])finalResult.toArray(new Store[finalResult.size()]);
		return stores;	
	}
	
	public String[] getStoreNamesByCategory(String[] categoryNameList) throws Exception{
		Store[] stores = getStoreByCategory(categoryNameList);
		String[] skuArray = new String[stores.length];
		for(int i=0;i<stores.length;i++){
			skuArray[i] = stores[i].getStoreName();
		}
		return skuArray;
	}
	
	public void deleteCategorys(String[] idArray) throws Exception {
		for(String id: idArray){
			StoreCate[] scs = getStoreCategoryListById(id);
			int[] stordIds = new int[scs.length];
			for(int i =0; i <scs.length; i++ ){
				stordIds[i]=scs[i].getId().getStoreId();
			}
			RuleCateDAO.getInstance().deleteCategory(id, stordIds);
			for(StoreCate sc :scs){
				super.delete(sc);
			}
		}	
	} 
	
	public void delete(int cateid, int storeId ) throws Exception{
		StoreCate sc = new StoreCate(new StoreCateId(cateid, storeId), null, null, null);
		super.delete(sc);
	}


	
	public List<Integer> getAllIds() throws Exception{
		List<Integer> result = new ArrayList<Integer>();
		for(StoreCate pc : getStoreCategoryList()){
			result.add(pc.getId().getCateStoreId());
		}
		return result;
	}

	public int getCateIdByCateName(String cateName) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("cateName",cateName);
		criterions.add(criterion);
		
		StoreCate pc = ((List<StoreCate>)super.getList(criterions)).get(0);
		return pc.getId().getCateStoreId();
	}
	
//	public StoreCate getCategoryById(int categoryId) throws Exception {
//	List<Criterion> criterions = new ArrayList<Criterion>();
//	Criterion criterion = Restrictions.eq("id.cateStoreId", categoryId);
//	criterions.add(criterion);
//	return super.get(criterions);
//}
	
}
