package dk.kentjuul.footballtracker.exception;

/**
 * Thrown when a call to the football data API fails.
 */
public class ApiException extends FootballTrackerException {

    private final int statusCode;

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApiException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    /** HTTP status code returned by the API, or {@code -1} if unavailable. */
    public int getStatusCode() {
        return statusCode;
    }
}
