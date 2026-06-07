package dk.kentjuul.footballtracker.repository;

import dk.kentjuul.footballtracker.model.Team;

import java.util.List;
import java.util.Optional;

/**
 * Repository abstraction for {@link Team} persistence.
 */
public interface TeamRepository {

    /**
     * Saves a team. Performs an upsert (insert or replace).
     *
     * @param team the team to save
     */
    void save(Team team);

    /**
     * Saves all teams in the given list.
     *
     * @param teams the teams to save
     */
    void saveAll(List<Team> teams);

    /**
     * Finds a team by its identifier.
     *
     * @param id the team identifier
     * @return an {@link Optional} containing the team, or empty if not found
     */
    Optional<Team> findById(long id);

    /**
     * Returns all teams stored in the repository.
     *
     * @return an immutable list of all teams
     */
    List<Team> findAll();
}
