package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.KnockoutMatch;
import dk.kentjuul.footballtracker.model.MatchStatus;
import dk.kentjuul.footballtracker.model.Stage;
import dk.kentjuul.footballtracker.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link KnockoutCalculator} (via {@link DefaultKnockoutCalculator}).
 */
class KnockoutCalculatorTest {

    private KnockoutCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DefaultKnockoutCalculator();
    }

    @Test
    @DisplayName("Empty match list returns empty bracket")
    void emptyMatchList_returnsEmptyBracket() {
        List<KnockoutMatch> result = calculator.resolveBracket(List.of());

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Null match list returns empty bracket")
    void nullMatchList_returnsEmptyBracket() {
        List<KnockoutMatch> result = calculator.resolveBracket(null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Non-empty match list throws UnsupportedOperationException (pending implementation)")
    void nonEmptyMatchList_throwsUnsupportedOperationException() {
        Team argentina = new Team(3L, "Argentina", "ARG", "ARG");
        Team france = new Team(4L, "France", "FRA", "FRA");

        KnockoutMatch finalMatch = new KnockoutMatch(
                200L,
                Stage.FINAL,
                argentina,
                france,
                3,
                3,
                null,
                null,
                4,
                2,
                argentina,
                MatchStatus.FINISHED
        );

        assertThrows(UnsupportedOperationException.class,
                () -> calculator.resolveBracket(List.of(finalMatch)));
    }
}
