package kz.edu.astanait.qarzhytracker.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class PdfUtils {

    private PdfUtils() {
        throw new UnsupportedOperationException("Utility class cannot have an instance");
    }

    public static Stream<Table> getTables(final PDDocument document) {
        var spreadsheetExtractionAlgorithm = new SpreadsheetExtractionAlgorithm();
        var pageIterable = (Iterable<Page>) () -> new ObjectExtractor(document).extract();
        return StreamSupport.stream(pageIterable.spliterator(), false)
                .flatMap(page -> spreadsheetExtractionAlgorithm.extract(page).stream());
    }

    public static List<List<RectangularTextContainer>> getRows(final Stream<Table> tables) {
        return tables
                .flatMap(table -> table.getRows().stream())
                .toList();
    }
}
