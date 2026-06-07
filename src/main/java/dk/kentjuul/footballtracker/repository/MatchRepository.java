package dk.kentjuul.footballtracker.repository;

import dk.kentjuul.footballtracker.model.Match;

import java.util.List;
import java.util.Optional;

/**
 * Repository abstraction for {@link Match} persistence.
 */
public interface MatchRepository {

    /**
     * Saves a match. Performs an upsert (insert or replace).
     *
     * @param match the match to save
     */
    void save(Match match);

    /**
     * Saves all matches in the given list.
     *
     * @param matches the matches to save
     */
    void saveAll(List<Match> matches);

    /**
     * Finds a match by its identifier.
     *
     * @param id the match identifier
     * @return an {@link Optional} containing the match, or empty if not found
     */
    Optional<Match> findById(long id);

    /**
     * Returns all matches stored in the repository.
     *
     * @return an immutable list of all matches
     */
    List<Match> findAll();
}
