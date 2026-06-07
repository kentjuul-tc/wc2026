package dk.kentjuul.footballtracker.model;

/**
 * Represents a football team.
 *
 * @param id        Unique identifier from the data provider.
 * @param name      Full club/national team name.
 * @param shortName Abbreviated display name.
 * @param tla       Three-letter abbreviation (e.g. "GER").
 */
public record Team(
        long id,
        String name,
        String shortName,
        String tla
) {}
