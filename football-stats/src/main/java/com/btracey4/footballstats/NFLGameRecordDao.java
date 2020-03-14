package com.btracey4.footballstats;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.btracey4.footballstats.hibernate.NFLGameRecord;
/**
 * Hibernate Dao class for accessing 'nfl_game_records' schema
 * @author btracey4
 *
 */
@Transactional(readOnly=false)
public class NFLGameRecordDao extends HibernateTemplate{
	
	public void saveGames(List<NFLGameRecord> games) {
	    Session session = super.getSessionFactory().openSession();
		Iterator it = games.iterator();
	    int i = 0;
	    while(it.hasNext()){ 
	        i++;
	        Object game = it.next();
	        session.saveOrUpdate(game);
//	        if (i % 50 == 0) { session.flush(); session.clear(); }
	    }
	}
	
	@SuppressWarnings("unchecked")
	public List<NFLGameRecord> getGameRecordsBySeason(int season){
		DetachedCriteria criteria = DetachedCriteria.forClass(NFLGameRecord.class);
		return (List<NFLGameRecord>) this.findByCriteria(criteria);
	}

}
