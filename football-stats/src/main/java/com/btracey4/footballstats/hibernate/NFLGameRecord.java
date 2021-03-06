package com.btracey4.footballstats.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.btracey4.footballstats.enums.Team;

/**
 * Hibernate class for NFL game
 * @author btracey4
 *
 */
@Entity
@Table(name="NFL_Season_Records")
public class NFLGameRecord {
	private int id;
	private int season;
	private int week;
	private Date gameDate;
	private Team homeTeam;
	private Team guestTeam;
	private int homeScore;
	private int guestScore;
	
	@Transient
	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	@Transient
	public Team getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="home_team")
	public String getHomeTeamName() {
		return homeTeam.getName();
	}
	
	public void setHomeTeamName(String name) {
		this.homeTeam = Team.getTeamByName(name);
	}
	
	@Column(name="guest_team")
	public String getGuestTeamName() {
		return guestTeam.getName();
	}
	
	public void setGuestTeamName(String name) {
		this.guestTeam = Team.getTeamByName(name);
	}

	@Column(name="home_score")
	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	@Column(name="guest_score")
	public int getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}

	@Column(name="season")
	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	@Column(name="week")
	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	@Column(name="game_date")
	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	
	@Override
	public String toString() {
		return String.format("%d\tWeek %d\t%15s vs. %15s\tScore %2d - %2d", season, week,homeTeam,guestTeam,homeScore,guestScore);
	}
}
