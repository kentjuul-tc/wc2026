package dk.kentjuul.footballtracker.exception;

/**
 * Base runtime exception for the Football Tracker application.
 */
public class FootballTrackerException extends RuntimeException {

    public FootballTrackerException(String message) {
        super(message);
    }

    public FootballTrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
