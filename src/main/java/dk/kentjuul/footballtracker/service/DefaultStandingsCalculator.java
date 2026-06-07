package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.GroupStanding;
import dk.kentjuul.footballtracker.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Default stub implementation of {@link StandingsCalculator}.
 *
 * <p>Full group-standing calculation logic will be implemented in a subsequent iteration.
 */
public class DefaultStandingsCalculator implements StandingsCalculator {

    private static final Logger log = LoggerFactory.getLogger(DefaultStandingsCalculator.class);

    @Override
    public List<GroupStanding> calculate(List<Match> matches) {
        if (matches == null || matches.isEmpty()) {
            return List.of();
        }
        log.debug("Calculating standings for {} matches", matches.size());
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
