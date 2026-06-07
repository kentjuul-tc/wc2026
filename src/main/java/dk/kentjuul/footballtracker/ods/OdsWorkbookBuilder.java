package dk.kentjuul.footballtracker.ods;

import java.io.OutputStream;

/**
 * Builds and writes an OpenDocument Spreadsheet (.ods) workbook.
 *
 * <p>Collaborates with {@link MatchSheetWriter}, {@link StandingsSheetWriter},
 * and {@link KnockoutSheetWriter} to populate individual sheets.
 */
public interface OdsWorkbookBuilder {

    /**
     * Writes a fully populated .ods workbook to the given output stream.
     *
     * @param outputStream target stream to write to
     */
    void build(OutputStream outputStream);
}
