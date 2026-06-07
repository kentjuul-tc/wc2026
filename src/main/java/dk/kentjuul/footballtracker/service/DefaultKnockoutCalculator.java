package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.KnockoutMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Default stub implementation of {@link KnockoutCalculator}.
 *
 * <p>Full bracket-resolution logic will be implemented in a subsequent iteration.
 */
public class DefaultKnockoutCalculator implements KnockoutCalculator {

    private static final Logger log = LoggerFactory.getLogger(DefaultKnockoutCalculator.class);

    @Override
    public List<KnockoutMatch> resolveBracket(List<KnockoutMatch> matches) {
        if (matches == null || matches.isEmpty()) {
            return List.of();
        }
        log.debug("Resolving knockout bracket for {} matches", matches.size());
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
