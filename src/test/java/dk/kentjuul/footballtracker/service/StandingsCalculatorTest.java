package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.GroupStanding;
import dk.kentjuul.footballtracker.model.Match;
import dk.kentjuul.footballtracker.model.MatchStatus;
import dk.kentjuul.footballtracker.model.Stage;
import dk.kentjuul.footballtracker.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StandingsCalculator} (via {@link DefaultStandingsCalculator}).
 */
class StandingsCalculatorTest {

    private StandingsCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DefaultStandingsCalculator();
    }

    @Test
    @DisplayName("Empty match list returns empty standings")
    void emptyMatchList_returnsEmptyStandings() {
        List<GroupStanding> result = calculator.calculate(List.of());

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Null match list returns empty standings")
    void nullMatchList_returnsEmptyStandings() {
        List<GroupStanding> result = calculator.calculate(null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Non-empty match list throws UnsupportedOperationException (pending implementation)")
    void nonEmptyMatchList_throwsUnsupportedOperationException() {
        Team germany = new Team(1L, "Germany", "GER", "GER");
        Team brazil = new Team(2L, "Brazil", "BRA", "BRA");

        Match match = new Match(
                100L,
                Stage.GROUP_STAGE,
                "GROUP_A",
                LocalDateTime.of(2026, 6, 14, 18, 0),
                germany,
                brazil,
                2,
                1,
                MatchStatus.FINISHED
        );

        assertThrows(UnsupportedOperationException.class,
                () -> calculator.calculate(List.of(match)));
    }
}
