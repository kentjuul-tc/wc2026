package dk.kentjuul.footballtracker.model;

/**
 * Classifies the phase (round) of a competition.
 */
public enum PhaseType {
    /** Preliminary group stage where teams are divided into groups. */
    GROUP_STAGE,
    /** Round of 32 knockout round. */
    ROUND_OF_32,
    /** Round of 16 knockout round. */
    ROUND_OF_16,
    /** Quarter-finals knockout round. */
    QUARTER_FINALS,
    /** Semi-finals knockout round. */
    SEMI_FINALS,
    /** Third-place play-off. */
    THIRD_PLACE,
    /** Grand final of the competition. */
    FINAL
}
