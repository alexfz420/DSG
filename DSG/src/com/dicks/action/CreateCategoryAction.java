package com.dicks.action;

import java.util.ArrayList;
import java.util.List;

import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.pojo.ProdCate;
import com.dicks.pojo.ProdCateId;
import com.dicks.pojo.Store;
import com.dicks.pojo.StoreCate;
import com.dicks.pojo.StoreCateId;
import com.opensymphony.xwork2.ActionSupport;

public class CreateCategoryAction extends ActionSupport {
	private String categoryName;
	private String categoryDes;
	private String storeType;
	private String regionTag;
	private String storeState;
	private String storeId;
	private String productType;
	private String brand;
	private String productSKU;
	private String act;

	private static final long serialVersionUID = 1L;
	private List<Store> storeList;
	
	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(ArrayList<Store> storeList) {
		this.storeList = storeList;
	}
	
	public String goToNewCategory() throws Exception{
		storeList = new ArrayList<Store>();
		storeList = StoreDAO.getInstance().getAllStores();
		return SUCCESS;
	}
	
	public String createCategory() throws Exception{
		if("store".equals(act)){
			String[] ids = null;
			if(storeState!= null){
				if("California".equals(storeState)){
					storeId = "363,367,372,373,374,421,469,470,471,473,474,475,476,477,478,479,480,481,482,489,503,504,511,518,519,527,528,529,542,552,559";
				}
				if("Pennsylvania".equals(storeState)){
					storeId = "266,14,15,277,24,25,28,29,36,37,38,39,40,298,305,58,59,61,73,74,75,83,85,100,384,136,151,187,188,198,203,204,222,225,491,250";
				}
			}
			
			
				ids = storeId.split(",");
//			System.out.println("!!!"+ids);
			int cateId = StoreCateDAO.getInstance().getNewId();
			
			for(String id:ids){
				
				StoreCateId storeCateId = new StoreCateId(Integer.valueOf(cateId), Integer.valueOf(id)) ;
				StoreCate storeCate = new StoreCate(storeCateId, null, categoryName, categoryDes);
				StoreCateDAO.getInstance().createCategory(storeCate);
			}
		}else if("product".equals(act)){
			String[] skus = null;
			if(brand!=null){
				if("Nike".equals(brand)){
					productSKU="NK-SH-KB";
				}
				if("Ocean".equals(brand)){
					productSKU="OC-OD-KY";
				}
				if("Oneill".equals(brand)){
					productSKU="ON-OD-WS";
				}
				if("Adidas".equals(brand)){
					productSKU="AD-SH-SP,AD-SH-TM";
				}
				if("Aqua".equals(brand)){
					productSKU="AQ-OD-MA";
				}
				if("TYR".equals(brand)){
					productSKU="TY-OD-WS";
				}
				if("Newport".equals(brand)){
					productSKU="NE-SH-SA";
				}
				if("All".equals(brand)){
					productSKU = "OC-OD-KY,ON-OD-WS,FI-OD-FI,AQ-OD-MA,TY-OD-WS,NK-SH-KB,AD-SH-SP,AD-SH-TM,NE-SH-SA";
				}	
			}	
			
			if(productType !=null){
				if("Outdoor".equals(productType)){
					productSKU = "OC-OD-KY,ON-OD-WS,FI-OD-FI,AQ-OD-MA,TY-OD-WS";
				}
				if("Shoe".equals(productType)){
					productSKU = "NK-SH-KB,AD-SH-SP,AD-SH-TM,NE-SH-SA";
				}
				if("All".equals(productType)){
					productSKU = "OC-OD-KY,ON-OD-WS,FI-OD-FI,AQ-OD-MA,TY-OD-WS,NK-SH-KB,AD-SH-SP,AD-SH-TM,NE-SH-SA";
				}	
			}
			skus = productSKU.split(",");
			int cateId = ProdCateDAO.getInstance().getNewId();
			int[] ids = ProductDAO.getInstance().getProductIdsBySKUList(skus);
			for(int id:ids){
				ProdCateId pcId = new ProdCateId(cateId, id);
				ProdCate prodCate = new ProdCate(pcId, null, categoryName, categoryDes);
				ProdCateDAO.getInstance().createCategory(prodCate);
			}	
		}
		return SUCCESS;
	}
	
	public String createStoreCategory(){
	
		return SUCCESS;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDes() {
		return categoryDes;
	}

	public void setCategoryDes(String categoryDes) {
		this.categoryDes = categoryDes;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getRegionTag() {
		return regionTag;
	}

	public void setRegionTag(String regionTag) {
		this.regionTag = regionTag;
	}

	public String getStoreState() {
		return storeState;
	}

	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductSKU() {
		return productSKU;
	}

	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String flag) {
		this.act = flag;
	}
}
