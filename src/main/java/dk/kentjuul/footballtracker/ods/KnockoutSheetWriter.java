package dk.kentjuul.footballtracker.ods;

import dk.kentjuul.footballtracker.model.KnockoutMatch;

import java.util.List;

/**
 * Writes the knockout bracket to a single sheet in an ODS workbook.
 */
public interface KnockoutSheetWriter {

    /**
     * Populates the knockout sheet.
     *
     * @param matches list of knockout matches to write
     */
    void write(List<KnockoutMatch> matches);
}
