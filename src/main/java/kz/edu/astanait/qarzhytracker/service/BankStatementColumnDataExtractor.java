package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.dto.BankStatementProperty;
import kz.edu.astanait.qarzhytracker.dto.Finance;
import kz.edu.astanait.qarzhytracker.dto.FinanceType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface BankStatementColumnDataExtractor {

    Finance extract(Map<BankStatementProperty, String> columnData);

    LocalDate extractDate(String date);

    BigDecimal extractAmount(String amount);

    FinanceType extractType(String amount);
}
