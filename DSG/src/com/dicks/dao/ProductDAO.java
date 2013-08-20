package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Product;

public class ProductDAO extends BaseDao<Product> {
	private static ProductDAO instance = new ProductDAO();

	public ProductDAO() {
		super(Product.class);
	}

	public static ProductDAO getInstance() {
		return instance;
	}

	public void createProduct(Product product) throws Exception {
		super.create(product);
	}
	
	

	public Product getById(Integer id) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("prodId", id);
		criterions.add(criterion);
		return super.get(criterions);
	}

	public List<Product> getProductsByIds(ArrayList<Integer> ids) throws Exception {
		List<Criterion> criterion = new ArrayList<Criterion>();
		Disjunction disjunctions = Restrictions.disjunction();
		for (int id : ids) {
			disjunctions.add(Restrictions.eq("prodId", id ));
		}
		criterion.add(disjunctions);
		return super.getList(criterion);	
	}
	
	public int[] getProductIdsBySKUList(String[] skuList) throws Exception{
		Product[] products = getProductsBySKUList(skuList);
		int[] array = new int[products.length];
		for(int i = 0 ; i<products.length ; i++){
			array[i] = products[i].getProdId();
		}
		
		return array;
	}
	
	public Product[] getProductsBySKUList(String[] skuList) throws Exception{
		List<Criterion> criterion = new ArrayList<Criterion>();

		Disjunction disjunctions = Restrictions.disjunction();
		for(String sku:skuList){
			disjunctions.add(Restrictions.eq("sku", sku));
		}
		criterion.add(disjunctions);
		ArrayList<Product> finalResult = (ArrayList<Product>)super.getList(criterion);
		Product[] products = (Product[])finalResult.toArray(new Product[finalResult.size()]);
		
		return products;		
	}
	
	
	public void updateProducts(Product[] products) throws Exception{
		for(Product p: products){
			super.update(p);
		}
	}
	
	public Product getProductById(String sku) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion = Restrictions.eq("sku", sku);
		criterions.add(criterion);
		return super.get(criterions);
	}
	
	public String[] getAllNames() throws Exception{
		List<Product> list = (ArrayList<Product>)super.getList();
		String[] names = new String[list.size()];
		for(int i = 0; i<list.size() ;i++){
			names[i] = list.get(i).getProdName()+"";
		}
		return names;
	}
	
	
}
