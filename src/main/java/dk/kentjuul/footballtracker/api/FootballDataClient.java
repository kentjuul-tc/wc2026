package dk.kentjuul.footballtracker.api;

import dk.kentjuul.footballtracker.api.dto.CompetitionDto;
import dk.kentjuul.footballtracker.api.dto.MatchDto;

import java.util.List;

/**
 * Client for the football-data.org REST API (v4).
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Execute HTTP calls using {@link java.net.http.HttpClient}</li>
 *   <li>Attach the API key header</li>
 *   <li>Deserialise JSON responses into DTOs</li>
 * </ul>
 */
public interface FootballDataClient {

    /**
     * Fetches all available competitions.
     *
     * @return list of competition DTOs
     */
    List<CompetitionDto> fetchCompetitions();

    /**
     * Fetches all matches for the given competition.
     *
     * @param competitionCode competition code (e.g. {@code "WC"} for World Cup)
     * @return list of match DTOs
     */
    List<MatchDto> fetchMatches(String competitionCode);
}
