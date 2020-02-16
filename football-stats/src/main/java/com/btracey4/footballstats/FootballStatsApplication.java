package com.btracey4.footballstats;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.btracey4.footballstats.loader.NFLGameRecordLoader;

@SpringBootApplication
@ImportResource("applicationContext.xml")
public class FootballStatsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FootballStatsApplication.class, args);
		
		switch (args[0]) {
		case "display":
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
			break;
		default:
			throw new IllegalArgumentException("No run configuration found for: " + args[1]
					+"\nExpected either \"display\" or \"load 'filepath'\"");
		}
		
	}

}
