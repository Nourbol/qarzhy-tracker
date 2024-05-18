package kz.edu.astanait.qarzhytracker.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

public final class PdfUtils {

    private PdfUtils() {
        throw new UnsupportedOperationException("Utility class cannot have an instance");
    }

    public static List<Table> getTables(final MultipartFile pdf) throws IOException {
        try (var document = PDDocument.load(pdf.getInputStream())){
            return getTables(document);
        }
    }

    public static List<Table> getTables(final PDDocument document) {
        var spreadsheetExtractionAlgorithm = new SpreadsheetExtractionAlgorithm();
        var pageIterable = (Iterable<Page>) () -> new ObjectExtractor(document).extract();
        return StreamSupport.stream(pageIterable.spliterator(), false)
            .flatMap(page -> spreadsheetExtractionAlgorithm.extract(page).stream())
            .toList();
    }
}
