package dk.kentjuul.footballtracker.service;

import dk.kentjuul.footballtracker.model.Match;
import dk.kentjuul.footballtracker.model.Prediction;

import java.util.List;

/**
 * Calculates points earned by a user based on their predictions vs actual results.
 */
public interface PredictionCalculator {

    /**
     * Calculates points for a single prediction against the actual match result.
     *
     * @param prediction the user's prediction
     * @param match      the actual match with result
     * @return points earned (0 or positive)
     */
    int calculatePoints(Prediction prediction, Match match);

    /**
     * Calculates the total points for a collection of predictions.
     *
     * @param predictions list of predictions
     * @param matches     list of actual matches (matched by {@code matchId})
     * @return total points earned
     */
    int calculateTotalPoints(List<Prediction> predictions, List<Match> matches);
}
