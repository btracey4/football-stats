package com.btracey4.footballstats.enums;
/**
 * Enum class for 32 NFL teams.
 * Each Team consists of a location, a team name,
 * and a {@link Team}
 * @author btracey4
 *
 */
public enum Team {

	PATRIOTS("New England", "Patriots", Division.AFC_EAST),
	BILLS("Buffalo", "Bills", Division.AFC_EAST),
	JETS("New York", "Jets", Division.AFC_EAST),
	DOLPHINS("Miami", "Dolphins", Division.AFC_EAST),
	RAVENS("Baltimore", "Ravens", Division.AFC_NORTH),
	STEELERS("Pittsburgh", "Steelers", Division.AFC_NORTH),
	BROWNS("Cleveland", "Browns", Division.AFC_NORTH),
	BENGALS("Cincinnati", "Bengals", Division.AFC_NORTH),
	TEXANS("Houston", "Texans", Division.AFC_SOUTH),
	TITANS("Tennessee", "Titans", Division.AFC_SOUTH),
	COLTS("Indianapolis", "Colts", Division.AFC_SOUTH),
	JAGUARS("Jacksonville", "Jaguars", Division.AFC_SOUTH),
	CHIEFS("Kansas City", "Chiefs", Division.AFC_WEST),
	BRONCOS("Denver", "Broncos", Division.AFC_WEST),
	RAIDERS("Oakland", "Raiders", Division.AFC_WEST),
	CHARGERS("Los Angeles", "Chargers", Division.AFC_WEST),
	EAGLES("Philadelphia", "Eagles", Division.NFC_EAST),
	COWBOYS("Dallas", "Cowboys", Division.NFC_EAST),
	GIANTS("New York", "Giants", Division.NFC_EAST),
	REDSKINS("Washington", "Redskins", Division.NFC_EAST),
	PACKERS("Green Bay", "Packers", Division.NFC_NORTH),
	VIKINGS("Minnesota", "Vikings", Division.NFC_NORTH),
	BEARS("Chicago", "Bears", Division.NFC_NORTH),
	LIONS("Detroit", "Lions", Division.NFC_NORTH),
	SAINTS("New Orleans", "Saints", Division.NFC_SOUTH),
	FALCONS("Atlanta", "Falcons", Division.NFC_SOUTH),
	BUCCANEERS("Tampa Bay", "Buccaneers", Division.NFC_SOUTH),
	PANTHERS("Carolina", "Panthers", Division.NFC_SOUTH),
	FORTY_NINERS("San Francisco", "49ers", Division.NFC_WEST),
	SEAHAWKS("Seattle", "Seahawks", Division.NFC_WEST),
	RAMS("Los Angeles", "Rams", Division.NFC_WEST),
	CARDINALS("Arizona", "Cardinals", Division.NFC_WEST);
	
	Team(String location, String name, Division division){
		this.location = location;
		this.name = name;
		this.division = division;
	}
	
	private String location;
	private String name;
	private Division division;
	
	public String getLocation() {
		return location;
	}
	public String getName() {
		return name;
	}
	public Division getDivision() {
		return division;
	}
	/**
	 * 
	 * @return Team Location + Team Name
	 */
	public String getFullName() {
		return String.format("%s %s", location, name);
	}
	public static Team getTeamByName(String name) {
		if(name.equals("St. Louis Rams")) return RAMS;
		else if(name.equals("San Diego Chargers")) return CHARGERS;
		for(Team team : values()) {
			if(team.getName().equals(name) || team.getFullName().equals(name))
				return team;
		}
		throw new IllegalArgumentException("No NFL team with the name: " + name);
	}
}
