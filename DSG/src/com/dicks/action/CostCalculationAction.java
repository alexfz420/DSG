package com.dicks.action;

import java.util.ArrayList;

import com.dicks.dao.FeeDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Rule;
import com.opensymphony.xwork2.ActionSupport;

public class CostCalculationAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	FeeDAO feeDAO = FeeDAO.getInstance();	
	
	private String ruleId;
	private String ruleName;

	private String ruleDescription;
	private String[] storeFeeName;
	private String[] storeFeeId;
	private String[] storeFeeFlag;
	private String[] storeFeePercentage;
	private String[] storeFeeAttribute;
	private String[] storeFeeValue;

	private String[] warehouseFeeName;
	private String[] warehouseFeeId;
	private String[] warehouseFeeFlag;
	private String[] warehouseFeePercentage;
	private String[] warehouseFeeAttribute;
	private String[] warehouseFeeValue;

	private String[] vendorFeeName;
	private String[] vendorFeeId;
	private String[] vendorFeeFlag;
	private String[] vendorFeePercentage;
	private String[] vendorFeeAttribute;
	private String[] vendorFeeValue;

	public String costCalculation() throws Exception {
		Rule rule = RuleDAO.getInstance().getRuleById(ruleId);
		rule.setRuleName(this.ruleName);
		rule.setRuleDescr(this.ruleDescription);
		RuleDAO.getInstance().update(rule);
		
		updateStoreFees();
		updateWarehouseFees();
		updateVendorFees();
		return SUCCESS;
	}

	public void updateVendorFees() throws Exception {
		if (vendorFeeId == null || vendorFeeId.length == 0) {
			deleteAll("vendor");
			return;
		}

		ArrayList<Fee> fees = feeDAO.getByType("vendor");
		for (int i = 0; i < vendorFeeId.length; i++) {
			if (vendorFeeId[i].equals("new")) {
				Fee f = new Fee();
				f.setCostName(vendorFeeName[i]);
				if (vendorFeeFlag[i].equals("p")) {
					f.setFlag("p");
					f.setAttribute(vendorFeeAttribute[i]);
					f.setPercentage((int) Math.round(Double
							.parseDouble(vendorFeePercentage[i]) * 100));
				} else if (vendorFeeFlag[i].equals("v")) {
					f.setFlag("v");
					f.setValue((int) Math.round(Double
							.parseDouble(vendorFeeValue[i]) * 100));
				}
				f.setShippingType("vendor");
				feeDAO.createFee(f);
			} else {
				Fee f = findById(fees, Integer.parseInt(vendorFeeId[i]));
				f.setCostName(vendorFeeName[i]);

				if (vendorFeeFlag[i].equals("p")) {
					f.setFlag("p");
					f.setAttribute(vendorFeeAttribute[i]);
					f.setPercentage((int) Math.round(Double
							.parseDouble(vendorFeePercentage[i]) * 100));
				} else if (vendorFeeFlag[i].equals("v")) {
					f.setFlag("v");
					f.setValue((int) Math.round(Double
							.parseDouble(vendorFeeValue[i]) * 100));
				}
				feeDAO.updateFee(f);
			}
		}

		for (int i = 0; i < fees.size(); i++) {
			Fee f = fees.get(i);
			boolean maintain = false;
			for (int j = 0; j < vendorFeeId.length && !maintain; j++) {
				if (Integer.parseInt(vendorFeeId[j]) == f.getCostId())
					maintain = true;
			}
			if (maintain == false)
				feeDAO.deleteFee(f);
		}
	}

	public void updateWarehouseFees() throws Exception {
		System.out.println("1");
		if (warehouseFeeId == null || warehouseFeeId.length == 0) {
			deleteAll("warehouse");
			return;
		}
		System.out.println("2");
		ArrayList<Fee> fees = feeDAO.getByType("warehouse");
		for (int i = 0; i < warehouseFeeId.length; i++) {
			if (warehouseFeeId[i].equals("new")) {
				Fee f = new Fee();
				f.setCostName(warehouseFeeName[i]);
				if (warehouseFeeFlag[i].equals("p")) {
					f.setFlag("p");
					f.setAttribute(warehouseFeeAttribute[i]);
					f.setPercentage((int) Math.round(Double
							.parseDouble(warehouseFeePercentage[i]) * 100));
				} else if (warehouseFeeFlag[i].equals("v")) {
					f.setFlag("v");
					f.setValue((int) Math.round(Double
							.parseDouble(warehouseFeeValue[i]) * 100));
				}
				f.setShippingType("warehouse");
				feeDAO.createFee(f);
				System.out.println("3");
			} else {
				Fee f = findById(fees, Integer.parseInt(warehouseFeeId[i]));
				f.setCostName(warehouseFeeName[i]);

				if (warehouseFeeFlag[i].equals("p")) {
					f.setFlag("p");
					f.setAttribute(warehouseFeeAttribute[i]);
					f.setPercentage((int) Math.round(Double
							.parseDouble(warehouseFeePercentage[i]) * 100));
				} else if (warehouseFeeFlag[i].equals("v")) {
					f.setFlag("v");
					f.setValue((int) Math.round(Double
							.parseDouble(warehouseFeeValue[i]) * 100));
				}
				feeDAO.updateFee(f);
				System.out.println("4");
			}
		}

		System.out.println("5");
		for (int i = 0; i < fees.size(); i++) {
			Fee f = fees.get(i);
			boolean maintain = false;
			for (int j = 0; j < warehouseFeeId.length && !maintain; j++) {
				if (Integer.parseInt(warehouseFeeId[j]) == f.getCostId())
					maintain = true;
			}
			if (maintain == false)
				feeDAO.deleteFee(f);
		}
	}

	private void updateStoreFees() throws Exception {
		if (storeFeeId == null || storeFeeId.length == 0) {
			deleteAll("store");
			return;
		}

		ArrayList<Fee> storeFees = feeDAO.getByType("store");
		for (int i = 0; i < storeFeeId.length; i++) {
			System.out.println("i: " + i);
			if (storeFeeId[i].equals("new")) {
				Fee f = new Fee();
				f.setCostName(storeFeeName[i]);
				if (storeFeeFlag[i].equals("p")) {
					f.setFlag("p");
					f.setAttribute(storeFeeAttribute[i]);
					f.setPercentage((int) Math.round(Double
							.parseDouble(storeFeePercentage[i]) * 100));
				} else if (storeFeeFlag[i].equals("v")) {
					f.setFlag("v");
					f.setValue((int) Math.round(Double
							.parseDouble(storeFeeValue[i]) * 100));
				}
				f.setShippingType("store");
				feeDAO.createFee(f);
			} else {
				Fee f = findById(storeFees, Integer.parseInt(storeFeeId[i]));
				f.setCostName(storeFeeName[i]);

				if (storeFeeFlag[i].equals("p")) {
					f.setFlag("p");
					f.setAttribute(storeFeeAttribute[i]);
					f.setPercentage((int) Math.round(Double
							.parseDouble(storeFeePercentage[i]) * 100));
				} else if (storeFeeFlag[i].equals("v")) {
					f.setFlag("v");
					f.setValue((int) Math.round(Double
							.parseDouble(storeFeeValue[i]) * 100));
				}
				feeDAO.updateFee(f);
			}
		}

		for (int i = 0; i < storeFees.size(); i++) {
			Fee f = storeFees.get(i);
			boolean maintain = false;
			for (int j = 0; j < storeFeeId.length && !maintain; j++) {
				if (Integer.parseInt(storeFeeId[j]) == f.getCostId())
					maintain = true;
			}
			if (maintain == false)
				feeDAO.deleteFee(f);
		}
	}

	private void deleteAll(String type) throws Exception {
		ArrayList<Fee> fees = feeDAO.getByType(type);
		for (Fee f : fees) {
			feeDAO.deleteFee(f);
		}
	}

	private Fee findById(ArrayList<Fee> storeFees, int id) {
		for (Fee f : storeFees) {
			if (f.getCostId() == id)
				return f;
		}
		return null;
	}

	public String[] getStoreFeeName() {
		return storeFeeName;
	}

	public void setStoreFeeName(String[] storeFeeName) {
		this.storeFeeName = storeFeeName;
	}

	public String[] getStoreFeeId() {
		return storeFeeId;
	}

	public void setStoreFeeId(String[] storeFeeId) {
		this.storeFeeId = storeFeeId;
	}

	public String[] getStoreFeeFlag() {
		return storeFeeFlag;
	}

	public void setStoreFeeFlag(String[] storeFeeFlag) {
		this.storeFeeFlag = storeFeeFlag;
	}

	public String[] getStoreFeePercentage() {
		return storeFeePercentage;
	}

	public void setStoreFeePercentage(String[] storeFeePercentage) {
		this.storeFeePercentage = storeFeePercentage;
	}

	public String[] getStoreFeeAttribute() {
		return storeFeeAttribute;
	}

	public void setStoreFeeAttribute(String[] storeFeeAttribute) {
		this.storeFeeAttribute = storeFeeAttribute;
	}

	public String[] getStoreFeeValue() {
		return storeFeeValue;
	}

	public void setStoreFeeValue(String[] storeFeeValue) {
		this.storeFeeValue = storeFeeValue;
	}

	public String[] getWarehouseFeeName() {
		return warehouseFeeName;
	}

	public void setWarehouseFeeName(String[] warehouseFeeName) {
		this.warehouseFeeName = warehouseFeeName;
	}

	public String[] getwarehouseFeeId() {
		return warehouseFeeId;
	}

	public void setWarehouseFeeId(String[] warehouseFeeId) {
		this.warehouseFeeId = warehouseFeeId;
	}

	public String[] getWarehouseFeeFlag() {
		return warehouseFeeFlag;
	}

	public void setWarehouseFeeFlag(String[] warehouseFeeFlag) {
		this.warehouseFeeFlag = warehouseFeeFlag;
	}

	public String[] getWarehouseFeePercentage() {
		return warehouseFeePercentage;
	}

	public void setWarehouseFeePercentage(String[] warehouseFeePercentage) {
		this.warehouseFeePercentage = warehouseFeePercentage;
	}

	public String[] getWarehouseFeeAttribute() {
		return warehouseFeeAttribute;
	}

	public void setWarehouseFeeAttribute(String[] warehouseFeeAttribute) {
		this.warehouseFeeAttribute = warehouseFeeAttribute;
	}

	public String[] getWarehouseFeeValue() {
		return warehouseFeeValue;
	}

	public void setWarehouseFeeValue(String[] warehouseFeeValue) {
		this.warehouseFeeValue = warehouseFeeValue;
	}

	public String[] getVendorFeeName() {
		return vendorFeeName;
	}

	public void setVendorFeeName(String[] vendorFeeName) {
		this.vendorFeeName = vendorFeeName;
	}

	public String[] getVendorFeeId() {
		return vendorFeeId;
	}

	public void setVendorFeeId(String[] vendorFeeId) {
		this.vendorFeeId = vendorFeeId;
	}

	public String[] getVendorFeeFlag() {
		return vendorFeeFlag;
	}

	public void setVendorFeeFlag(String[] vendorFeeFlag) {
		this.vendorFeeFlag = vendorFeeFlag;
	}

	public String[] getVendorFeePercentage() {
		return vendorFeePercentage;
	}

	public void setVendorFeePercentage(String[] vendorFeePercentage) {
		this.vendorFeePercentage = vendorFeePercentage;
	}

	public String[] getVendorFeeAttribute() {
		return vendorFeeAttribute;
	}

	public void setVendorFeeAttribute(String[] vendorFeeAttribute) {
		this.vendorFeeAttribute = vendorFeeAttribute;
	}

	public String[] getVendorFeeValue() {
		return vendorFeeValue;
	}

	public void setVendorFeeValue(String[] vendorFeeValue) {
		this.vendorFeeValue = vendorFeeValue;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
}
