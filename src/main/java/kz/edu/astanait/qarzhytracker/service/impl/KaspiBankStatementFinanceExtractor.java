package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.dto.BankStatementProperty;
import kz.edu.astanait.qarzhytracker.dto.Finance;
import kz.edu.astanait.qarzhytracker.service.BankStatementColumnDataExtractor;
import kz.edu.astanait.qarzhytracker.service.BankStatementFinanceExtractor;
import kz.edu.astanait.qarzhytracker.util.PdfUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class KaspiBankStatementFinanceExtractor implements BankStatementFinanceExtractor {

    private final BankStatementColumnDataExtractor columnDataExtractor;

    private static final Map<Integer, Set<BankStatementProperty>> COLUMNS = Map.of(
            0, Set.of(BankStatementProperty.DATE),
            1, Set.of(BankStatementProperty.AMOUNT, BankStatementProperty.TYPE),
            3, Set.of(BankStatementProperty.DETAILS)
    );

    public List<Finance> extract(final Stream<Table> tables) {
        return PdfUtils.getRows(tables)
                .stream()
                .skip(1L)
                .map(this::extractRowData).toList();
    }

    public Finance extractRowData(final List<RectangularTextContainer> row) {
        var rowData = new HashMap<BankStatementProperty, String>();
        for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
            if (!COLUMNS.containsKey(columnIndex)) {
                continue;
            }
            var data = row.get(columnIndex).getText().trim();
            COLUMNS.get(columnIndex).forEach(property -> rowData.put(property, data));
        }
        return columnDataExtractor.extract(rowData);
    }
}
