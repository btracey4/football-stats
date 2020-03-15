package com.btracey4.footballstats;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.btracey4.footballstats.enums.Team;
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
		Iterator<NFLGameRecord> it = games.iterator();
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
		criteria.add(Restrictions.eq("season", season));
		return (List<NFLGameRecord>) this.findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<NFLGameRecord> getGameRecordsByTeamAndSeason(Team team, int season){
		DetachedCriteria criteria = DetachedCriteria.forClass(NFLGameRecord.class);
		criteria.add(Restrictions.eq("season", season));
		Criterion homeTeam = Restrictions.eq("homeTeamName", team.getName());
		Criterion guestTeam = Restrictions.eq("guestTeamName", team.getName());
		LogicalExpression homeOrGuest = Restrictions.or(homeTeam, guestTeam);
		criteria.add(homeOrGuest);
		return (List<NFLGameRecord>) this.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getAllSeasons(){
		String hql = "SELECT DISTINCT(season) FROM NFLGameRecord ORDER BY season";
		Session session = super.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		return (List<Integer>)query.list();
	}

}
