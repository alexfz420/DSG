package com.dicks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.dicks.pojo.Inventory;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.Shipment;

public class ShipmentDAO extends BaseDao<Shipment> {
	private static ShipmentDAO instance = new ShipmentDAO();

	public ShipmentDAO() {
		super(Shipment.class);
	}

	public static ShipmentDAO getInstance() {
		return instance;
	}
	
	public ArrayList<Shipment> getShipmentByRate(int rate) throws Exception {
		System.out.println("hibernate: " + rate);
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion2 = Restrictions.eq("normalRate", 842);
		criterions.add(criterion2);
		return (ArrayList<Shipment>) super.getList(criterions);
	}
	
	public ArrayList<Shipment> getShipmentByDestin(int destinationZip) throws Exception {
		System.out.println("hibernate: " + destinationZip);
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion2 = Restrictions.eq("id.destinationZip", new Integer(destinationZip));
		criterions.add(criterion2);
		return (ArrayList<Shipment>) super.getList(criterions);
	}

	public Shipment getShipmentBySupplyDesitin(String supplyZip, String destinationZip) throws Exception {
		//System.out.println("in get shippment");
		List<Criterion> criterions = new ArrayList<Criterion>();
        Criterion criterion1 = Restrictions.eq("id.supplyZip", supplyZip);
        Criterion criterion2 = Restrictions.eq("id.destinationZip", destinationZip);
        criterions.add(criterion1);
        criterions.add(criterion2);
        if (super.get(criterions) == null){
        	System.out.println("null");
        }
        return super.get(criterions);
	}
	
	/*public Shipment getShipmentBySupplyDesitin(int supplyZip, int destinationZip) throws Exception {
		List<Criterion> criterions = new ArrayList<Criterion>();
        Criterion criterion1 = Restrictions.eq("id.supplyZip", supplyZip);
        Criterion criterion2 = Restrictions.eq("id.destinationZip", destinationZip);
        criterions.add(criterion1);
        criterions.add(criterion2);
        return super.get(criterions);
	}*/
	
	
	public void deleteAll() throws Exception{
		List<Shipment> shipments =(List<Shipment>)super.getList();
		for(Shipment s : shipments){
			super.delete(s);
		}
	}
	
	public void create(Shipment shipment) throws Exception{
		if(!contain(shipment))
		super.create(shipment);
	}
	
	public boolean contain(Shipment shipment) throws Exception{
		List<Criterion> criterions = new ArrayList<Criterion>();
		Criterion criterion1 = Restrictions.eq("id.destinationZip", shipment.getId().getDestinationZip());
		Criterion criterion2 = Restrictions.eq("id.supplyZip", shipment.getId().getSupplyZip());
		criterions.add(criterion1);
		criterions.add(criterion2);
		Shipment shipment1 =(Shipment) super.get(criterions);
		if(shipment1==null) return false;
		return true;
	}
}
