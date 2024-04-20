package kz.edu.astanait.qarzhytracker.kaspi;

import kz.edu.astanait.qarzhytracker.converter.DdMmYyConverter;
import kz.edu.astanait.qarzhytracker.annotation.TableColumn;
import java.time.LocalDate;

public record KaspiTransaction(@TableColumn(columnIndex = 0, converter = DdMmYyConverter.class)
                               LocalDate date,
                               @TableColumn(columnIndex = 1)
                               String amount,
                               @TableColumn(columnIndex = 2)
                               String operation,
                               @TableColumn(columnIndex = 3)
                               String details) {
}
