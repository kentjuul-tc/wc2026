package dk.kentjuul.footballtracker.provider.fake;

import dk.kentjuul.footballtracker.model.*;
import dk.kentjuul.footballtracker.provider.FootballProvider;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A fake {@link FootballProvider} that returns a fixed set of test matches.
 *
 * <p>Intended for testing and local development only. The API-Football integration
 * will be implemented in a subsequent iteration.
 */
public class FakeFootballProvider implements FootballProvider {

    private static final Team GERMANY  = new Team(1L, "Germany",   "GER", "GER");
    private static final Team BRAZIL   = new Team(2L, "Brazil",    "BRA", "BRA");
    private static final Team FRANCE   = new Team(3L, "France",    "FRA", "FRA");
    private static final Team ARGENTINA = new Team(4L, "Argentina", "ARG", "ARG");
    private static final Team SPAIN    = new Team(5L, "Spain",     "ESP", "ESP");
    private static final Team ENGLAND  = new Team(6L, "England",   "ENG", "ENG");

    @Override
    public Competition getCompetition(long competitionId) {
        return new Competition(competitionId, "FIFA World Cup 2026", CompetitionType.CUP, 1, 8);
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
        return List.of(
                new Match(101L, Stage.GROUP_STAGE, "GROUP_A",
                        LocalDateTime.of(2026, 6, 14, 18, 0),
                        GERMANY, BRAZIL, null, null, MatchStatus.SCHEDULED),
                new Match(102L, Stage.GROUP_STAGE, "GROUP_A",
                        LocalDateTime.of(2026, 6, 15, 15, 0),
                        FRANCE, ARGENTINA, null, null, MatchStatus.SCHEDULED),
                new Match(103L, Stage.GROUP_STAGE, "GROUP_B",
                        LocalDateTime.of(2026, 6, 16, 18, 0),
                        SPAIN, ENGLAND, null, null, MatchStatus.SCHEDULED),
                new Match(104L, Stage.GROUP_STAGE, "GROUP_A",
                        LocalDateTime.of(2026, 6, 18, 18, 0),
                        BRAZIL, FRANCE, 2, 1, MatchStatus.FINISHED),
                new Match(105L, Stage.GROUP_STAGE, "GROUP_A",
                        LocalDateTime.of(2026, 6, 19, 20, 0),
                        GERMANY, ARGENTINA, 1, 1, MatchStatus.FINISHED),
                new Match(106L, Stage.GROUP_STAGE, "GROUP_B",
                        LocalDateTime.of(2026, 6, 20, 20, 0),
                        ENGLAND, SPAIN, 0, 2, MatchStatus.FINISHED)
        );
    }
}
