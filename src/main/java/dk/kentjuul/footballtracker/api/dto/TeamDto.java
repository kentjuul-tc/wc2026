package dk.kentjuul.footballtracker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data Transfer Object representing a team as returned by football-data.org.
 *
 * <p>Annotated with {@code @JsonIgnoreProperties(ignoreUnknown = true)} so that
 * extra fields in the API response do not break deserialisation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamDto(
        long id,
        String name,
        String shortName,
        String tla
) {}
