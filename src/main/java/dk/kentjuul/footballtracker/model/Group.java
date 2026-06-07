package dk.kentjuul.footballtracker.model;

import java.util.List;

/**
 * Represents a group within the group stage of a competition.
 *
 * @param id    Unique identifier from the data provider.
 * @param name  Display name of the group (e.g. "Group A").
 * @param teams The list of {@link Team}s competing in this group.
 */
public record Group(
        long id,
        String name,
        List<Team> teams
) {}
