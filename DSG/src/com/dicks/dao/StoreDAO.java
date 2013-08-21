package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Product;
import com.dicks.pojo.Store;

public class StoreDAO extends BaseDao<Store> {
	private static StoreDAO instance = new StoreDAO();

	public static StoreDAO getInstance() {
		return instance;
	}

	public static void setInstance(StoreDAO instance) {
		StoreDAO.instance = instance;
	}

	public StoreDAO() {
		super(Store.class);
	}
	
	public void createStore(Store store) throws Exception {
		super.create(store);
	}

	public int getTotalStoreNum() throws Exception {
		return super.getList().size();
	}
	
	public Store getById(int id) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("storeId", id);
		criterions.add(criterion);
		return super.get(criterions);
	}
	
	public ArrayList<Store> getAllStores() throws Exception{
		return (ArrayList<Store>) super.getList();
	}
	
	public String[] getAllNames() throws Exception{
		List<Store> list = getAllStores();
		String[] names = new String[list.size()];
		for(int i = 0; i<list.size() ;i++){
			names[i] = list.get(i).getStoreName()+"";
		}
		return names;
	}
	
	public void setFlag() throws Exception{
		List<Store> stores =  getAllStores();	
		
		for(Store store :stores){
			store.setFlag("TH-A,ST-A,SP-A");
			super.update(store);
		}
	}
	
	public void deletePie() throws Exception{
		List<Store> stores =  getAllStores();	
		for(Store store :stores){
			String name =store.getStoreName();
			name.replaceAll("'", "");
			store.setStoreName(name);
			super.update(store);
		}

	}

}
