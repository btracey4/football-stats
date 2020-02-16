package com.btracey4.footballstats.enums;

/**
 * Enum class for NFL {@link Team}.
 * Each Division consists of a conference (one
 * of "AFC" or "NFC") and a region (one of Cardinal
 * directions)
 * @author btracey4
 *
 */
public enum Division {
	AFC_EAST("AFC", "East"),
	AFC_NORTH("AFC", "North"),
	AFC_SOUTH("AFC", "South"),
	AFC_WEST("AFC", "West"),
	NFC_EAST("NFC", "East"),
	NFC_NORTH("NFC", "North"),
	NFC_SOUTH("NFC", "South"),
	NFC_WEST("NFC", "West");
	
	Division(String conference, String region){
		this.conference = conference;
		this.region = region;
		this.displayName = String.format("%s %s", conference, region);
	}
	
	private String conference;
	private String region;
	private String displayName;
	
	public String getConference() {
		return conference;
	}
	public String getRegion() {
		return region;
	}
	public String getDisplayName() {
		return displayName;
	}
}
