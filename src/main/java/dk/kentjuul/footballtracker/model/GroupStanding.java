package dk.kentjuul.footballtracker.model;

/**
 * Standing of a team within a group at a point in time.
 *
 * @param group         Group identifier (e.g. "GROUP_A").
 * @param position      Position within the group (1-based).
 * @param team          The team.
 * @param played        Number of matches played.
 * @param won           Number of matches won.
 * @param drawn         Number of matches drawn.
 * @param lost          Number of matches lost.
 * @param goalsFor      Total goals scored.
 * @param goalsAgainst  Total goals conceded.
 * @param points        Total points accumulated.
 */
public record GroupStanding(
        String group,
        int position,
        Team team,
        int played,
        int won,
        int drawn,
        int lost,
        int goalsFor,
        int goalsAgainst,
        int points
) {
    /** Computed goal difference. */
    public int goalDifference() {
        return goalsFor - goalsAgainst;
    }
}
