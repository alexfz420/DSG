package com.dicks.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DAO<E> {

	private SessionFactory sessionFactory;
	public DAO(SessionFactory factory)
	{
		sessionFactory = factory;
	}
	
	protected SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	protected Session getSession()
	{
		return sessionFactory.openSession();
	}
	
	protected List getByAttribute(String attribute,String value,Class clazz)
	{
		Session session = null;
		try {
			session = this.getSession();
			StringBuilder sb = new StringBuilder();
			sb.append("from ");
			sb.append(clazz.getName());
			sb.append(" where ");
			sb.append(attribute);
			sb.append('=');
			sb.append("'");
			sb.append(value);
			sb.append("'");
			String query = sb.toString();
			Query qr = session.createQuery(query);
			List list = qr.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null)	session.close();
		}
		return null;
	}
	
	protected List getAll(Class clazz)
	{
		Session session = null;
		try {
			session = this.getSession();
			Query qr = session.createQuery("from "+clazz.getName());
			List list = qr.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null)	session.close();
		}
		return null;
	}
	
	protected boolean createNew(E entity,String attribute,String key,Class clazz)
	{
		Session session = null;
		try {
			session = this.getSession();
			Transaction tx = session.beginTransaction();
			List existed = getByAttribute(attribute,key,clazz);
			if(existed!=null && existed.size()>0)
			{
				tx.rollback();
				return false;
			}
			else
			{
				session.save(entity);
				tx.commit();
				return true;
			} 
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(session != null)	session.close();
		}
	}
}
