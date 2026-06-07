package dk.kentjuul.footballtracker.ods;

import dk.kentjuul.footballtracker.model.Match;

import java.util.List;

/**
 * Writes a list of matches to a single sheet in an ODS workbook.
 */
public interface MatchSheetWriter {

    /**
     * Populates the match sheet.
     *
     * @param matches list of matches to write
     */
    void write(List<Match> matches);
}
