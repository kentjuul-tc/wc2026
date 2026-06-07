package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.Match;
import dk.kentjuul.footballtracker.model.Team;
import dk.kentjuul.footballtracker.provider.FootballProvider;
import dk.kentjuul.footballtracker.repository.MatchRepository;
import dk.kentjuul.footballtracker.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Orchestrates the import of a competition's data from a {@link FootballProvider}
 * into the persistence layer.
 *
 * <p>The service:
 * <ol>
 *   <li>Fetches matches from the provider.</li>
 *   <li>Extracts and saves all unique teams.</li>
 *   <li>Saves all matches.</li>
 *   <li>Prints import statistics.</li>
 * </ol>
 */
public class ImportCompetitionService {

    private static final Logger log = LoggerFactory.getLogger(ImportCompetitionService.class);

    private final FootballProvider provider;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public ImportCompetitionService(FootballProvider provider,
                                    TeamRepository teamRepository,
                                    MatchRepository matchRepository) {
        this.provider = provider;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    /**
     * Imports all matches (and the teams referenced by them) for the given competition/season.
     *
     * @param competitionId the competition identifier passed to the provider
     * @param seasonId      the season identifier passed to the provider
     * @return the list of persisted matches
     */
    public List<Match> importCompetition(long competitionId, long seasonId) {
        log.info("Importing competition {} / season {}", competitionId, seasonId);

        // 1. Fetch matches from provider
        List<Match> matches = provider.getMatches(competitionId, seasonId);
        log.info("Fetched {} matches from provider", matches.size());

        // 2. Extract unique teams
        Map<Long, Team> teams = new LinkedHashMap<>();
        for (Match match : matches) {
            teams.put(match.homeTeam().id(), match.homeTeam());
            teams.put(match.awayTeam().id(), match.awayTeam());
        }

        // 3. Save teams
        teamRepository.saveAll(List.copyOf(teams.values()));
        log.info("Saved {} unique teams", teams.size());

        // 4. Save matches
        matchRepository.saveAll(matches);
        log.info("Saved {} matches", matches.size());

        // 5. Print statistics
        printStatistics(matches, teams);

        return List.copyOf(matches);
    }

    private void printStatistics(List<Match> matches, Map<Long, Team> teams) {
        long finished = matches.stream()
                .filter(m -> m.homeGoals() != null && m.awayGoals() != null)
                .count();
        long scheduled = matches.size() - finished;

        log.info("=== Import statistics ===");
        log.info("  Teams   : {}", teams.size());
        log.info("  Matches : {} total ({} finished, {} scheduled/upcoming)", matches.size(), finished, scheduled);
    }
}
