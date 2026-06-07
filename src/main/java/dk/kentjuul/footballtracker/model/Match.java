package dk.kentjuul.footballtracker.model;

import java.time.LocalDateTime;

/**
 * Represents a single football match.
 *
 * @param id         Unique identifier from the data provider.
 * @param stage      Tournament stage in which this match is played.
 * @param group      Group identifier (e.g. "GROUP_A"), {@code null} for knockout matches.
 * @param date       Scheduled or actual kick-off time (UTC).
 * @param homeTeam   The home team.
 * @param awayTeam   The away team.
 * @param homeGoals  Goals scored by the home team, {@code null} if not yet played.
 * @param awayGoals  Goals scored by the away team, {@code null} if not yet played.
 * @param status     Current lifecycle status of the match.
 */
public record Match(
        long id,
        Stage stage,
        String group,
        LocalDateTime date,
        Team homeTeam,
        Team awayTeam,
        Integer homeGoals,
        Integer awayGoals,
        MatchStatus status
) {}
