package dk.kentjuul.footballtracker.model;

import java.time.LocalDate;

/**
 * Represents a season (edition) of a {@link Competition}.
 *
 * @param id             Unique identifier from the data provider.
 * @param startDate      First day of the season.
 * @param endDate        Last day of the season (may be {@code null} while ongoing).
 * @param currentMatchday The current matchday within this season, or {@code null} if not applicable.
 * @param winner         The winning {@link Team} of the season, or {@code null} if not yet decided.
 */
public record Season(
        long id,
        LocalDate startDate,
        LocalDate endDate,
        Integer currentMatchday,
        Team winner
) {}
