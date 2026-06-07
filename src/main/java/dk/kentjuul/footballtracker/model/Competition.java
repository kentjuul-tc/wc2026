package dk.kentjuul.footballtracker.model;

/**
 * Represents a football competition (tournament or league).
 *
 * @param id              Unique identifier from the data provider.
 * @param name            Display name (e.g. "FIFA World Cup").
 * @param type            Classification of the competition.
 * @param currentMatchday The current matchday number (null if not applicable).
 * @param totalMatchdays  Total number of matchdays (null if not applicable).
 */
public record Competition(
        long id,
        String name,
        CompetitionType type,
        Integer currentMatchday,
        Integer totalMatchdays
) {}
