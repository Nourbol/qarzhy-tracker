package kz.edu.astanait.qarzhytracker.domain;

import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableRow {

    private final List<RectangularTextContainer> row;

    public TableRow(final List<RectangularTextContainer> row) {
        this.row = new ArrayList<>(row);
    }

    public static List<TableRow> from(final Table table) {
        return table.getRows().stream()
            .map(TableRow::new)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public String getTextFromColumn(final int columnIndex) {
        return row.get(columnIndex).getText();
    }
}
