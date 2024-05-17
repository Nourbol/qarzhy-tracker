package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.CategorizedFinancialOverview;
import kz.edu.astanait.qarzhytracker.domain.CategoriesStatistic;
import kz.edu.astanait.qarzhytracker.domain.GeneralFinancialSummary;
import java.time.LocalDate;
import java.util.UUID;

public interface TransactionStatisticReader {

    GeneralFinancialSummary getUserGeneralFinancialSummaryInRange(UUID userId, LocalDate from, LocalDate to);

    CategorizedFinancialOverview getUserCategorizedFinancialOverview(UUID userId, LocalDate from, LocalDate to);

    CategoriesStatistic getUserCategoriesTotalExpenseInRange(UUID userId, LocalDate from, LocalDate to);

    CategoriesStatistic getUserCategoriesTotalRevenueInRange(UUID userId, LocalDate from, LocalDate to);
}
