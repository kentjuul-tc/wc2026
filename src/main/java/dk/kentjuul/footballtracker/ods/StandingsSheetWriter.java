package dk.kentjuul.footballtracker.ods;

import dk.kentjuul.footballtracker.model.GroupStanding;

import java.util.List;

/**
 * Writes group standings to a single sheet in an ODS workbook.
 */
public interface StandingsSheetWriter {

    /**
     * Populates the standings sheet.
     *
     * @param standings list of group standings to write
     */
    void write(List<GroupStanding> standings);
}
