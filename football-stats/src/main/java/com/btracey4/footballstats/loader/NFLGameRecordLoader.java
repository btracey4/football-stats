package com.btracey4.footballstats.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.btracey4.footballstats.NFLGameRecordDao;
import com.btracey4.footballstats.enums.Team;
import com.btracey4.footballstats.hibernate.NFLGameRecord;
/**
 * Loader class for parsing csv files with NFL matchup
 * data and saving to 'nfl_game_records' schema. Expects order of
 * csv file fields to be:
 * 	<li>1) Season (start year)
 *  <li>2) Week
 *  <li>3) Game Date
 *  <li>4) Home Team
 *  <li>5) Guest Team
 *  <li>6) Home Team score
 *  <li>7) Guest Team score
 * @author btracey4
 *
 */
public class NFLGameRecordLoader {
	private List<NFLGameRecord> games;
	private String dateFormatString;
	private SimpleDateFormat dateFormat;
	private Logger log;
	private NFLGameRecordDao dao;

	private void initialize() {
		dateFormat = new SimpleDateFormat(dateFormatString);
		log = Logger.getLogger(NFLGameRecordLoader.class.getName());
		games = new ArrayList<>();
	}
	
	/**
	 * Given filepath to csv, parses file into {@link NFLGameRecord} objects,
	 * then saves them in database
	 * @param filepath
	 * @throws Exception
	 */
	public void parseCSV(String filepath) throws Exception {
		initialize();
		log.info("Opening file: " + filepath);
		try(BufferedReader file = new BufferedReader(new FileReader(filepath))){
			String currLine = "";
			while((currLine = file.readLine()) != null) {
				try {
					NFLGameRecord game = getGameFromRawLine(currLine);
					games.add(game);
				} catch (ParseException e) {
					throw new IllegalArgumentException("Problem parsing the following line:\n"
							+ currLine + "\n\nMessage: " + e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		saveGamesToDatabase(games);
	}
	
	
	private NFLGameRecord getGameFromRawLine(String rawLine) throws ParseException{
		String[] fields = rawLine.split(",");
		NFLGameRecord game = new NFLGameRecord();
		game.setSeason(Integer.parseInt(fields[0]));
		game.setWeek(Integer.parseInt(fields[1]));
		game.setGameDate(dateFormat.parse(fields[2]));
		game.setHomeTeam(Team.getTeamByName(fields[3]));
		game.setGuestTeam(Team.getTeamByName(fields[4]));
		game.setHomeScore(Integer.parseInt(fields[5]));
		game.setGuestScore(Integer.parseInt(fields[6]));
		return game;
	}
	
	private void saveGamesToDatabase(List<NFLGameRecord> games) {
		dao.saveGames(games);
	}

	public String getDateFormatString() {
		return dateFormatString;
	}

	public void setDateFormatString(String dateFormatString) {
		this.dateFormatString = dateFormatString;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public NFLGameRecordDao getDao() {
		return dao;
	}

	public void setDao(NFLGameRecordDao dao) {
		this.dao = dao;
	}
	
	
	
}
