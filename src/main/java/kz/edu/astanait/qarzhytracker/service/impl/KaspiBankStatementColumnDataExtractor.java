package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.dto.BankStatementProperty;
import kz.edu.astanait.qarzhytracker.dto.Finance;
import kz.edu.astanait.qarzhytracker.dto.FinanceType;
import kz.edu.astanait.qarzhytracker.service.BankStatementColumnDataExtractor;
import kz.edu.astanait.qarzhytracker.util.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class KaspiBankStatementColumnDataExtractor implements BankStatementColumnDataExtractor {

    private static final String REVENUE_SIGN = "+";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public Finance extract(final Map<BankStatementProperty, String> columnData) {
        var financeBuilder = Finance.builder();
        columnData.forEach((key, value) -> {
            switch (key) {
                case DATE -> financeBuilder.date(extractDate(value));
                case AMOUNT -> financeBuilder.amount(extractAmount(value));
                case TYPE -> financeBuilder.type(extractType(value));
                case DETAILS -> financeBuilder.details(value.trim());
            }
        });
        return financeBuilder.build();
    }

    public LocalDate extractDate(final String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    public BigDecimal extractAmount(final String amount) {
        return StringUtils.extractBigDecimalFrom(amount);
    }

    public FinanceType extractType(final String amount) {
        return amount.contains(REVENUE_SIGN) ? FinanceType.REVENUE : FinanceType.EXPENSE;
    }
}
