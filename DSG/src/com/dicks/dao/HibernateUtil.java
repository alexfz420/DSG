/**
 * 08-600 
 * Kewei Wang
 * 08/23/2012
 */
package com.dicks.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * HibernateUtil Class
 * 
 * @version 1.0
 */
public final class HibernateUtil {
	private static SessionFactory factory;

    public static void setSessionFactory(SessionFactory factory) {
        HibernateUtil.factory = factory;
    }
	
	public static Session getSession() {
		if (factory == null) {
//			Configuration configuration = new Configuration();
//			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();         
//			factory = configuration.buildSessionFactory(serviceRegistry); 
			factory = new Configuration().configure().buildSessionFactory();
		}
		Session session = factory.openSession();
		if (session != null) {
		} else {
		}
		session.clear();
		return session;
	}
}
