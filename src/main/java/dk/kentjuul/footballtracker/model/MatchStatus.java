package dk.kentjuul.footballtracker.model;

/**
 * Lifecycle status of a single match.
 */
public enum MatchStatus {
    SCHEDULED,
    TIMED,
    IN_PLAY,
    PAUSED,
    FINISHED,
    SUSPENDED,
    POSTPONED,
    CANCELLED,
    AWARDED
}
