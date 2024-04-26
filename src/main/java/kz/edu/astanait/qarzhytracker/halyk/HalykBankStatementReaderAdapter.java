package kz.edu.astanait.qarzhytracker.halyk;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.Finance;
import kz.edu.astanait.qarzhytracker.service.BankStatementReaderAdapter;
import kz.edu.astanait.qarzhytracker.util.PdfUtils;
import kz.edu.astanait.qarzhytracker.util.TableManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HalykBankStatementReaderAdapter implements BankStatementReaderAdapter {

    private final HalykTransactionMapper mapper;

    @Override
    public List<Finance> extract(final MultipartFile statement) throws IOException {
        var tables = PdfUtils.getTables(statement);
        var rows = TableManager.from(tables)
            .deleteFirstRow(0)
            .deleteFirstRowFromAllTables()
            .getAllRows();
        return mapper.mapToFinances(rows);
    }

    @Override
    public BankStatementType getType() {
        return BankStatementType.HALYK;
    }
}
