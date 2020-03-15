package com.btracey4.footballstats;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.btracey4.footballstats.display.NFLStatsDisplay;
import com.btracey4.footballstats.loader.NFLGameRecordLoader;

@SpringBootApplication
@ImportResource("applicationContext.xml")
public class FootballStatsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FootballStatsApplication.class, args);
		NFLStatsDisplay display;
		switch (args[0]) {
		case "display":
			display = (NFLStatsDisplay) applicationContext.getBean("nflStatsDisplay");
			display.display();
			break;
		case "load":
			NFLGameRecordLoader loader = (NFLGameRecordLoader) applicationContext.getBean("nflGameRecordLoader");
			try {
				loader.parseCSV(args[1]);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			display = (NFLStatsDisplay) applicationContext.getBean("nflStatsDisplay");
			display.display();
			break;
		default:
			throw new IllegalArgumentException("No run configuration found for: " + args[1]
					+"\nExpected either \"display\" or \"load 'filepath'\"");
		}
		
	}

}
