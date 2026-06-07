package dk.kentjuul.footballtracker.provider;

import dk.kentjuul.footballtracker.model.Competition;
import dk.kentjuul.footballtracker.model.Group;
import dk.kentjuul.footballtracker.model.Match;
import dk.kentjuul.footballtracker.model.Phase;
import dk.kentjuul.footballtracker.model.Season;

import java.util.List;

/**
 * Abstraction layer for fetching football data from an external data source.
 *
 * <p>Implementations are responsible for communicating with a concrete data
 * provider (e.g. api-football.com) and mapping the provider-specific responses
 * to the application's domain model.
 */
public interface FootballProvider {

    /**
     * Fetches a single competition by its identifier.
     *
     * @param competitionId the unique identifier of the competition
     * @return the matching {@link Competition}
     */
    Competition getCompetition(long competitionId);

    /**
     * Fetches all seasons for a given competition.
     *
     * @param competitionId the unique identifier of the competition
     * @return a list of {@link Season}s, never {@code null}
     */
    List<Season> getSeasons(long competitionId);

    /**
     * Fetches all phases for a given season of a competition.
     *
     * @param competitionId the unique identifier of the competition
     * @param seasonId      the unique identifier of the season
     * @return a list of {@link Phase}s, never {@code null}
     */
    List<Phase> getPhases(long competitionId, long seasonId);

    /**
     * Fetches all groups for a given season of a competition.
     *
     * @param competitionId the unique identifier of the competition
     * @param seasonId      the unique identifier of the season
     * @return a list of {@link Group}s, never {@code null}
     */
    List<Group> getGroups(long competitionId, long seasonId);

    /**
     * Fetches all matches for a given season of a competition.
     *
     * @param competitionId the unique identifier of the competition
     * @param seasonId      the unique identifier of the season
     * @return a list of {@link Match}es, never {@code null}
     */
    List<Match> getMatches(long competitionId, long seasonId);
}
