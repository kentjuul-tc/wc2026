package dk.kentjuul.footballtracker.provider.apifootball;

import dk.kentjuul.footballtracker.model.Competition;
import dk.kentjuul.footballtracker.model.Group;
import dk.kentjuul.footballtracker.model.Match;
import dk.kentjuul.footballtracker.model.Phase;
import dk.kentjuul.footballtracker.model.Season;
import dk.kentjuul.footballtracker.provider.FootballProvider;

import java.util.List;

/**
 * Skeleton implementation of {@link FootballProvider} backed by the api-football.com REST API.
 *
 * <p>Concrete REST calls and response mapping will be implemented in a subsequent iteration.
 * All methods currently throw {@link UnsupportedOperationException}.
 */
public class ApiFootballProvider implements FootballProvider {

    @Override
    public Competition getCompetition(long competitionId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<Season> getSeasons(long competitionId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<Phase> getPhases(long competitionId, long seasonId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<Group> getGroups(long competitionId, long seasonId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<Match> getMatches(long competitionId, long seasonId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
