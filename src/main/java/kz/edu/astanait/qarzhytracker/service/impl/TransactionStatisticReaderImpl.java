package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.TransactionStatistic;
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

    public TransactionStatistic getUserStatisticInRange(final UUID userId, final LocalDate from, final LocalDate to) {
        var currentBalance = balanceReader.getUserBalance(userId);
        var expenseSum = transactionRepository.sumExpensesInRange(userId, from, to);
        var revenueSum = transactionRepository.sumRevenuesInRange(userId, from, to);
        return new TransactionStatistic(currentBalance, expenseSum, revenueSum);
    }
}
