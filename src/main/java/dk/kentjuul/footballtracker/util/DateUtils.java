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
     * Formats a {@link LocalDateTime} as an ISO-8601 string (UTC assumed).
     *
     * @param dateTime the value to format
     * @return formatted string, e.g. {@code "2026-06-14T18:00:00Z"}
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(ISO_FORMATTER);
    }
}
