package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.CategorizedFinancialOverview;
import kz.edu.astanait.qarzhytracker.domain.CategoriesStatistic;
import kz.edu.astanait.qarzhytracker.domain.GeneralFinancialSummary;
import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.service.BalanceReader;
import kz.edu.astanait.qarzhytracker.service.TransactionStatisticReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionStatisticReaderImpl implements TransactionStatisticReader {

    private final BalanceReader balanceReader;
    private final TransactionRepository transactionRepository;

    @Override
    public GeneralFinancialSummary getUserGeneralFinancialSummaryInRange(final UUID userId, final LocalDate from, final LocalDate to) {
        var currentBalance = balanceReader.getUserBalance(userId);
        var expenseSum = transactionRepository.sumExpensesInRange(userId, from, to);
        var revenueSum = transactionRepository.sumRevenuesInRange(userId, from, to);
        return new GeneralFinancialSummary(currentBalance, expenseSum, revenueSum);
    }

    @Override
    public CategorizedFinancialOverview getUserCategorizedFinancialOverview(final UUID userId,
                                                                            final LocalDate from,
                                                                            final LocalDate to) {
        var expenseStatistic = getUserCategoriesTotalExpenseInRange(userId, from, to);
        var revenueStatistic = getUserCategoriesTotalRevenueInRange(userId, from, to);
        return new CategorizedFinancialOverview(expenseStatistic, revenueStatistic);
    }

    @Override
    public CategoriesStatistic getUserCategoriesTotalExpenseInRange(final UUID userId,
                                                                    final LocalDate from,
                                                                    final LocalDate to) {
        var categoryExpenses = transactionRepository.getUserCategoryExpensesInRange(userId, from, to);
        var totalExpense = transactionRepository.sumExpensesInRange(userId, from, to);
        return new CategoriesStatistic(categoryExpenses, totalExpense);
    }

    @Override
    public CategoriesStatistic getUserCategoriesTotalRevenueInRange(final UUID userId,
                                                                    final LocalDate from,
                                                                    final LocalDate to) {
        var categoryRevenues = transactionRepository.getUserCategoryRevenuesInRange(userId, from, to);
        var totalExpense = transactionRepository.sumRevenuesInRange(userId, from, to);
        return new CategoriesStatistic(categoryRevenues, totalExpense);
    }
}
