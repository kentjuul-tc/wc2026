package dk.kentjuul.footballtracker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data Transfer Object representing a competition as returned by football-data.org.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CompetitionDto(
        long id,
        String name,
        String code,
        String type
) {}
