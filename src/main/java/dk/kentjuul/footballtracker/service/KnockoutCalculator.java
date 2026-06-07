package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.KnockoutMatch;

import java.util.List;

/**
 * Calculates the knockout bracket and determines match winners/advancement.
 */
public interface KnockoutCalculator {

    /**
     * Resolves the next round of the knockout bracket based on the current
     * state of the given matches.
     *
     * @param matches list of knockout matches; may be empty
     * @return list of {@link KnockoutMatch} objects representing the resolved bracket
     */
    List<KnockoutMatch> resolveBracket(List<KnockoutMatch> matches);
}
