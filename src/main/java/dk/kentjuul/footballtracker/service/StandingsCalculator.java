package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.GroupStanding;
import dk.kentjuul.footballtracker.model.Match;

import java.util.List;

/**
 * Calculates group standings from a list of played matches.
 */
public interface StandingsCalculator {

    /**
     * Computes the group standings for all groups present in the given matches.
     *
     * @param matches list of matches to process; may be empty
     * @return sorted list of {@link GroupStanding} entries (sorted by group, then position)
     */
    List<GroupStanding> calculate(List<Match> matches);
}
