package com.btracey4.footballstats.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.btracey4.footballstats.enums.Team;

/**
 * 
 * @author btracey4
 * Hibernate class for NFL game
 *
 */
@Entity
@Table(name="games")
public class Game {
	private Team homeTeam;
	private Team guestTeam;
	private int homeScore;
	private int guestScore;
	private int season;
	private int week;
	private Date gameDate;
	
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

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	
}
