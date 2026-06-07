package dk.kentjuul.footballtracker.api;

import dk.kentjuul.footballtracker.api.dto.CompetitionDto;
import dk.kentjuul.footballtracker.api.dto.MatchDto;
import dk.kentjuul.footballtracker.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Default implementation of {@link FootballDataClient} using {@link java.net.http.HttpClient}.
 *
 * <p>The full HTTP and JSON-parsing logic will be added in a subsequent iteration.
 * Currently acts as a documented stub.
 */
public class FootballDataClientImpl implements FootballDataClient {

    private static final Logger log = LoggerFactory.getLogger(FootballDataClientImpl.class);

    private final ApplicationConfig config;

    public FootballDataClientImpl(ApplicationConfig config) {
        this.config = config;
    }

    @Override
    public List<CompetitionDto> fetchCompetitions() {
        log.info("Fetching competitions from {}", config.getBaseUrl());
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<MatchDto> fetchMatches(String competitionCode) {
        log.info("Fetching matches for competition '{}' from {}", competitionCode, config.getBaseUrl());
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
