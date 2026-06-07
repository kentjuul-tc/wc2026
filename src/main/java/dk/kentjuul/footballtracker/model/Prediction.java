package dk.kentjuul.footballtracker.model;

/**
 * A user's score prediction for a single match.
 *
 * @param matchId              The identifier of the predicted match.
 * @param predictedHomeGoals   Predicted goals for the home team.
 * @param predictedAwayGoals   Predicted goals for the away team.
 */
public record Prediction(
        long matchId,
        Integer predictedHomeGoals,
        Integer predictedAwayGoals
) {}
