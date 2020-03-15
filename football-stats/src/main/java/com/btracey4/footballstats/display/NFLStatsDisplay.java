package com.btracey4.footballstats.display;

import java.util.List;
import java.util.Scanner;

import com.btracey4.footballstats.NFLGameRecordDao;
import com.btracey4.footballstats.enums.Team;
import com.btracey4.footballstats.hibernate.NFLGameRecord;

/**
 * Class for querying and displaying loaded game record data
 * @author btracey4
 *
 */
public class NFLStatsDisplay {
	Scanner scan;
	NFLGameRecordDao dao;
	
	/**
	 * Entry way for display logic
	 */
	public void display() {
		initialize();
		mainMenu();
	}
	
	private void initialize() {
		scan = new Scanner(System.in);
	}
	
	/**
	 * Presents querying options and funnels to appropriate helper method
	 */
	private void mainMenu() {
		System.out.println("\nSelect an action: ");
		System.out.println("\t1) Display team records by season");
		System.out.println("\t2) Display individual season records");
		System.out.println("\t3) Exit");
		int response = 0;
		String input = "";
		try {
			input = scan.next();
			response = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid response give: \"" + input + "\" is not a valid number.\n Returning to main menu.\n");
			mainMenu();
		}
		switch(response) {
		case 1:
			displayTeamRecords();
			break;
		case 2:
			break;
		case 3:
			break;
			default:
				System.out.println("Invalid response give: \"" + response + "\" is not an option.\n Returning to main menu.\n");
				mainMenu();
		}
	}
	
	/**
	 * Helper method to display a specific teams matchup records for a 
	 * specific season
	 */
	private void displayTeamRecords() {
		System.out.println("Input team name:");
		String teamName;
		teamName = scan.next();
		Team team = null;
		try{
			team = Team.getTeamByName(teamName);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + "\nInput a valid team name.");
			displayTeamRecords();
		}
		int season = 0;	
		boolean isValidSeason = false;
		while(!isValidSeason) {
			System.out.println("Select a season:");
			List<Integer> validSeasons = dao.getAllSeasons();
			for(Integer validSeason : validSeasons) {
				System.out.println("\t" + validSeason);
			}
			try {
				season = Integer.parseInt(scan.next());
			} catch(NumberFormatException e) {
				System.out.println("Invalid response. Try again");
				continue;
			}
			if(validSeasons.contains(season)) {
				isValidSeason = true;
			}
		}
		int wins = 0;
		int losses = 0;
		int ties = 0;
		for(NFLGameRecord game : dao.getGameRecordsByTeamAndSeason(team, season)) {
			System.out.println(game);
			if(game.getWeek() > 17) break;
			if(game.getHomeScore() == game.getGuestScore()) {
				ties++;
			}
			else if((game.getHomeTeam() == team) && (game.getHomeScore() > game.getGuestScore())) {
				wins++;
			} else if((game.getGuestTeam() == team) && (game.getHomeScore() < game.getGuestScore())) {
				wins++;
			}
			else {
				losses++;
			}
		}
		String fullSeasonRecord = String.format("%s %d season record: %d-%d",
				team.getFullName(), season, wins, losses);
		fullSeasonRecord =  ties > 0 ? fullSeasonRecord + "-" + ties : fullSeasonRecord;
		System.out.println(fullSeasonRecord);
		System.out.println("Returning to main menu");
		mainMenu();
	}

	
	
	public NFLGameRecordDao getDao() {
		return dao;
	}

	public void setDao(NFLGameRecordDao dao) {
		this.dao = dao;
	}
	
}
