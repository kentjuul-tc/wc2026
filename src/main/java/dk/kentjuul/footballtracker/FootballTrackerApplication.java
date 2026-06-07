package dk.kentjuul.footballtracker;

import dk.kentjuul.footballtracker.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for the Football Tracker application.
 */
public class FootballTrackerApplication {

    private static final Logger log = LoggerFactory.getLogger(FootballTrackerApplication.class);

    public static void main(String[] args) {
        log.info("Football Tracker starting…");
        ApplicationConfig config = new ApplicationConfig();
        log.info("Using API base URL: {}", config.getBaseUrl());
        log.info("Football Tracker initialised. Full implementation pending.");
    }
}
