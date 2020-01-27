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
import com.btracey4.footballstats.hibernate.Game;

public class GameLoader {
	private List<Game> games;
	private SimpleDateFormat dateFormat;
	private Logger log;

	private void initialize() {
		dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		log = Logger.getLogger(GameLoader.class.getName());
		games = new ArrayList<>();
	}
	
	public void parseCSV(String filepath) throws Exception {
		initialize();
		log.info("Opening file: " + filepath);
		try(BufferedReader file = new BufferedReader(new FileReader(filepath))){
			String headerLine = file.readLine();
			checkHeaders(headerLine);
			String currLine = "";
			while((currLine = file.readLine()) != null) {
				try {
					Game game = getGameFromRawLine(currLine);
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
	
	
	private Game getGameFromRawLine(String rawLine) throws ParseException{
		String[] fields = rawLine.split(",");
		Game game = new Game();
		game.setSeason(Integer.parseInt(fields[0]));
		game.setWeek(Integer.parseInt(fields[1]));
		game.setGameDate(dateFormat.parse(fields[2]));
		game.setHomeTeam(Team.getTeamByName(fields[3]));
		game.setGuestTeam(Team.getTeamByName(fields[4]));
		game.setHomeScore(Integer.parseInt(fields[5]));
		game.setGuestScore(Integer.parseInt(fields[6]));
		return game;
	}
	
	public boolean checkHeaders(String headerLine) {
		return false;
	}
	
	private void saveGamesToDatabase(List<Game> games) {
		
	}
	
}
