package dk.kentjuul.footballtracker.model;

/**
 * Represents a match in the knockout phase of a tournament.
 *
 * @param id                   Unique identifier from the data provider.
 * @param stage                The knockout stage (e.g. {@link Stage#SEMI_FINALS}).
 * @param homeTeam             The home/first team ({@code null} if not yet determined).
 * @param awayTeam             The away/second team ({@code null} if not yet determined).
 * @param homeGoals            Regular-time goals for the home team, {@code null} if unplayed.
 * @param awayGoals            Regular-time goals for the away team, {@code null} if unplayed.
 * @param homeGoalsExtraTime   Extra-time goals for the home team, {@code null} if not required.
 * @param awayGoalsExtraTime   Extra-time goals for the away team, {@code null} if not required.
 * @param homePenalties        Penalty goals for the home team, {@code null} if not required.
 * @param awayPenalties        Penalty goals for the away team, {@code null} if not required.
 * @param winner               The winning team, {@code null} if the match is unresolved.
 * @param status               Current lifecycle status of the match.
 */
public record KnockoutMatch(
        long id,
        Stage stage,
        Team homeTeam,
        Team awayTeam,
        Integer homeGoals,
        Integer awayGoals,
        Integer homeGoalsExtraTime,
        Integer awayGoalsExtraTime,
        Integer homePenalties,
        Integer awayPenalties,
        Team winner,
        MatchStatus status
) {}
