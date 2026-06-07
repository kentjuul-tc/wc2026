package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.Competition;
import dk.kentjuul.footballtracker.model.Match;

import java.util.List;

/**
 * Orchestrates the import of matches from the data provider into the domain model.
 */
public interface MatchImportService {

    /**
     * Imports all matches for the given competition.
     *
     * @param competition the competition to import matches for
     * @return an immutable list of domain {@link Match} objects
     */
    List<Match> importMatches(Competition competition);
}
