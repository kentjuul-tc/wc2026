package dk.kentjuul.footballtracker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object representing a match as returned by football-data.org.
 *
 * <p>Score information is nested inside the API response; this DTO flattens
 * the structure for ease of mapping to the domain model.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MatchDto(
        long id,
        String stage,
        String group,
        String utcDate,
        String status,
        TeamDto homeTeam,
        TeamDto awayTeam,
        ScoreDto score
) {
    /**
     * Nested score structure from the football-data.org API.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ScoreDto(
            HalfDto fullTime,
            HalfDto extraTime,
            HalfDto penalties
    ) {}

    /** Goals scored in a single half or phase (home/away). */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record HalfDto(
            @JsonProperty("home") Integer home,
            @JsonProperty("away") Integer away
    ) {}
}
