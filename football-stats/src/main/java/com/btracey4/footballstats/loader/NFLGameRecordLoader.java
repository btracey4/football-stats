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

import com.btracey4.footballstats.enums.Team;
import com.btracey4.footballstats.hibernate.NFLGameRecord;

public class NFLGameRecordLoader {
	private List<NFLGameRecord> games;
	private String dateFormatString;
	private SimpleDateFormat dateFormat;
	private Logger log;

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
		
	}

	public String getDateFormatString() {
		return dateFormatString;
	}

	public void setDateFormatString(String dateFormatString) {
		this.dateFormatString = dateFormatString;
	}
	
	
	
}
