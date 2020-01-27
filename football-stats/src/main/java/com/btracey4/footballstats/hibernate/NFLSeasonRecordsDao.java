package com.btracey4.footballstats.hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class NFLSeasonRecordsDao extends HibernateTemplate{
	
	@Override
	public SessionFactory getSessionFactory(){
		Configuration cfg = new Configuration();
		Properties p = new Properties();
		//load properties file
		try{
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch(Exception e){
			e.printStackTrace();
		}

		cfg.setProperties(p);
		// build session factory
		return cfg.buildSessionFactory();
	}

}
