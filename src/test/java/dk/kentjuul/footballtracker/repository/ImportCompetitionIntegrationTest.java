package dk.kentjuul.footballtracker.repository;

import dk.kentjuul.footballtracker.db.DatabaseConfig;
import dk.kentjuul.footballtracker.model.*;
import dk.kentjuul.footballtracker.provider.fake.FakeFootballProvider;
import dk.kentjuul.footballtracker.service.ImportCompetitionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests that verify teams and matches are persisted correctly in an
 * in-memory SQLite database.
 */
class ImportCompetitionIntegrationTest {

    private DatabaseConfig db;
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @BeforeEach
    void setUp() {
        // Use a unique named in-memory database per test so connections share the same DB
        // and the database is isolated between tests.
        String dbName = "testdb-" + UUID.randomUUID().toString().replace("-", "");
        db = new DatabaseConfig("jdbc:sqlite:file:" + dbName + "?mode=memory&cache=shared");
        teamRepository = new JdbcTeamRepository(db);
        matchRepository = new JdbcMatchRepository(db);
    }

    @AfterEach
    void tearDown() {
        db.close();
    }

    @Test
    @DisplayName("ImportCompetitionService saves all teams from fake provider")
    void importCompetition_savesAllTeams() {
        ImportCompetitionService service = new ImportCompetitionService(
                new FakeFootballProvider(), teamRepository, matchRepository);

        service.importCompetition(1L, 1L);

        List<Team> teams = teamRepository.findAll();
        assertEquals(6, teams.size(), "Expected 6 unique teams to be saved");
    }

    @Test
    @DisplayName("ImportCompetitionService saves all matches from fake provider")
    void importCompetition_savesAllMatches() {
        ImportCompetitionService service = new ImportCompetitionService(
                new FakeFootballProvider(), teamRepository, matchRepository);

        service.importCompetition(1L, 1L);

        List<Match> matches = matchRepository.findAll();
        assertEquals(6, matches.size(), "Expected 6 matches to be saved");
    }

    @Test
    @DisplayName("Saved team can be retrieved by id")
    void savedTeam_canBeFoundById() {
        Team germany = new Team(1L, "Germany", "GER", "GER");
        teamRepository.save(germany);

        Optional<Team> found = teamRepository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("Germany", found.get().name());
        assertEquals("GER", found.get().tla());
    }

    @Test
    @DisplayName("Saved match contains correct team references")
    void savedMatch_hasCorrectTeams() {
        Team germany = new Team(1L, "Germany", "GER", "GER");
        Team brazil  = new Team(2L, "Brazil",  "BRA", "BRA");
        teamRepository.save(germany);
        teamRepository.save(brazil);

        Match match = new Match(101L, Stage.GROUP_STAGE, "GROUP_A",
                LocalDateTime.of(2026, 6, 14, 18, 0),
                germany, brazil, null, null, MatchStatus.SCHEDULED);
        matchRepository.save(match);

        Optional<Match> found = matchRepository.findById(101L);
        assertTrue(found.isPresent());
        assertEquals("Germany", found.get().homeTeam().name());
        assertEquals("Brazil",  found.get().awayTeam().name());
        assertNull(found.get().homeGoals());
        assertNull(found.get().awayGoals());
        assertEquals(MatchStatus.SCHEDULED, found.get().status());
    }

    @Test
    @DisplayName("Saved finished match retains score")
    void savedFinishedMatch_retainsScore() {
        Team france    = new Team(3L, "France",    "FRA", "FRA");
        Team argentina = new Team(4L, "Argentina", "ARG", "ARG");
        teamRepository.save(france);
        teamRepository.save(argentina);

        Match match = new Match(102L, Stage.FINAL, null,
                LocalDateTime.of(2026, 7, 19, 18, 0),
                france, argentina, 2, 3, MatchStatus.FINISHED);
        matchRepository.save(match);

        Match found = matchRepository.findById(102L).orElseThrow();
        assertEquals(2, found.homeGoals());
        assertEquals(3, found.awayGoals());
        assertEquals(Stage.FINAL, found.stage());
        assertNull(found.group());
    }

    @Test
    @DisplayName("findAll returns empty list when no data is present")
    void findAll_emptyWhenNoData() {
        assertTrue(teamRepository.findAll().isEmpty());
        assertTrue(matchRepository.findAll().isEmpty());
    }
}
