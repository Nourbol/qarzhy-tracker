package kz.edu.astanait.qarzhytracker.kaspi;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.mapper.TableMapper;
import kz.edu.astanait.qarzhytracker.domain.Finance;
import kz.edu.astanait.qarzhytracker.service.BankStatementReaderAdapter;
import kz.edu.astanait.qarzhytracker.util.PdfUtils;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class KaspiBankStatementReaderAdapter implements BankStatementReaderAdapter {

    private static final long SKIP_FIRST_TABLES = 3L;

    private final KaspiTransactionMapper mapper;
    private final TableMapper tableMapper;

    @Override
    public List<Finance> extract(final MultipartFile statement) throws IOException {
        var pdf = PDDocument.load(statement.getInputStream());
        var filteredTables = PdfUtils.getTables(pdf).skip(SKIP_FIRST_TABLES);
        return extract(filteredTables);
    }

    @Override
    public BankStatementType getType() {
        return BankStatementType.KASPI;
    }

    public List<Finance> extract(final Stream<Table> tables) {
        return PdfUtils.getRows(tables)
                .stream()
                .skip(1L)
                .map(this::extractRowData).toList();
    }

    public Finance extractRowData(final List<RectangularTextContainer> row) {
        var kaspiTransaction = tableMapper.mapToObject(row, KaspiTransaction.class);
        return mapper.mapToFinance(kaspiTransaction);
    }
}
