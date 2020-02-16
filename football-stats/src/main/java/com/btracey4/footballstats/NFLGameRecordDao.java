package com.btracey4.footballstats;

import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.btracey4.footballstats.hibernate.NFLGameRecord;

public class NFLGameRecordDao extends HibernateTemplate{
	
//	@Override
//	public SessionFactory getSessionFactory(){
//		if(super.getSessionFactory() != null) {
//			return super.getSessionFactory();
//		}
//		Configuration cfg = new Configuration();
//		Properties p = new Properties();
//		//load properties file
//		try{
//			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//
//		cfg.setProperties(p);
//		// build and set session factory
//		this.setSessionFactory(cfg.buildSessionFactory());
//		return super.getSessionFactory();
//	}
	
	public void saveGames(List<NFLGameRecord> games) {
		this.saveOrUpdate(games);
	}
	
	@SuppressWarnings("unchecked")
	public List<NFLGameRecord> getGameRecordsBySeason(int season){
		DetachedCriteria criteria = DetachedCriteria.forClass(NFLGameRecord.class);
		return (List<NFLGameRecord>) this.findByCriteria(criteria);
	}

}
