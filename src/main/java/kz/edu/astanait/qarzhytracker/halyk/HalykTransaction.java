package kz.edu.astanait.qarzhytracker.halyk;

import kz.edu.astanait.qarzhytracker.annotation.TableColumn;
import kz.edu.astanait.qarzhytracker.converter.DdMmYyyyConverter;
import kz.edu.astanait.qarzhytracker.converter.MoneyAmountConverter;
import java.math.BigDecimal;
import java.time.LocalDate;

public record HalykTransaction(@TableColumn(columnIndex = 0, converter = DdMmYyyyConverter.class)
                               LocalDate operationData,
                               @TableColumn(columnIndex = 1, converter = DdMmYyyyConverter.class)
                               LocalDate processDate,
                               @TableColumn(columnIndex = 2)
                               String details,
                               @TableColumn(columnIndex = 4)
                               String currency,
                               @TableColumn(columnIndex = 5, converter = MoneyAmountConverter.class)
                               BigDecimal revenue,
                               @TableColumn(columnIndex = 6, converter = MoneyAmountConverter.class)
                               BigDecimal expense,
                               @TableColumn(columnIndex = 7, converter = MoneyAmountConverter.class)
                               BigDecimal commission) {
}
