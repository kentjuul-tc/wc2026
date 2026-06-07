package dk.kentjuul.footballtracker.model;

/**
 * Represents a phase (round) within a {@link Season} of a competition.
 *
 * @param id   Unique identifier from the data provider.
 * @param type The type of phase this represents.
 * @param name Display name of the phase (e.g. "Group Stage", "Quarter-finals").
 */
public record Phase(
        long id,
        PhaseType type,
        String name
) {}
