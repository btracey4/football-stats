package com.btracey4.footballstats.display;

import java.util.Scanner;

public class NFLStatsDisplay {
	Scanner scan;
	
	public void display() {
		initialize();
		mainMenu();
	}
	
	private void initialize() {
		scan = new Scanner(System.in);
		
	}
	
	private void mainMenu() {
		System.out.println("Select an action: ");
		System.out.println("\t1) Display team records by season");
		System.out.println("\t2) Display individual season records");
		System.out.println("\t3) Exit");
		int response = 0;
		String input = "";
		try {
			input = scan.next();
			response = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid response give: \"" + input + "\" is not a valid number.\n Returning to main menu.");
			mainMenu();
		}
		switch(response) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
			default:
				System.out.println("Invalid response give: \"" + response + "\" is not an option.\n Returning to main menu.");
				mainMenu();
		}
	}
	
	private void displayTeamRecords() {
		
	}
}
