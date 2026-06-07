package dk.kentjuul.footballtracker.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * General date/time utilities used across the application.
 */
public final class DateUtils {

    private static final DateTimeFormatter ISO_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private DateUtils() {
        // Utility class – do not instantiate.
    }

    /**
     * Formats a {@link LocalDateTime} as an ISO-8601 string with a literal {@code Z} suffix.
     *
     * <p><strong>Note:</strong> {@code LocalDateTime} carries no timezone information.
     * The {@code Z} suffix is appended as a literal to indicate that callers are
     * expected to supply values that are already expressed in UTC (as returned by
     * the football-data.org API). No timezone conversion is performed.
     *
     * @param dateTime the UTC-based date/time value to format
     * @return formatted string, e.g. {@code "2026-06-14T18:00:00Z"}
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(ISO_FORMATTER);
    }
}
