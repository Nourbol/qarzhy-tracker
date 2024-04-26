package kz.edu.astanait.qarzhytracker.util;

import kz.edu.astanait.qarzhytracker.domain.TableRow;
import technology.tabula.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TableManager {

    private final List<List<TableRow>> rowsPerTable;

    private TableManager(final List<List<TableRow>> rowsPerTable) {
        this.rowsPerTable = rowsPerTable;
    }

    public static TableManager from(final List<Table> tables) {
        var tableRows = tables.stream()
            .map(TableRow::from)
            .collect(Collectors.toCollection(ArrayList::new));
        return new TableManager(tableRows);
    }

    public TableManager deleteFirstTables(final long n) {
        for (var index = 0L; index < n; index++) {
            rowsPerTable.removeFirst();
        }
        return this;
    }

    public TableManager deleteFirstRowFromAllTables() {
        rowsPerTable.forEach(List::removeFirst);
        return this;
    }

    public TableManager deleteFirstRow(final int tableIndex) {
        rowsPerTable.get(tableIndex).removeFirst();
        return this;
    }

    public List<TableRow> getAllRows() {
        return rowsPerTable.stream()
            .flatMap(Collection::stream)
            .toList();
    }
}
