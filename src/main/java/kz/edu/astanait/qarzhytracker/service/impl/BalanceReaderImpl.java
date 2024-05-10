package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.service.BalanceHistoryReader;
import kz.edu.astanait.qarzhytracker.service.BalanceReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceReaderImpl implements BalanceReader {

    private final BalanceHistoryReader balanceHistoryReader;
    private final TransactionRepository transactionRepository;

    @Override
    public BigDecimal getUserBalance(final UUID userId) {
        return balanceHistoryReader.getUserLastBalanceHistoryRecord(userId)
            .map(balanceHistoryRecord -> {
                var sumAmount = transactionRepository.sumAmountStartingOperationDateFrom(userId, balanceHistoryRecord.recordedAt());
                return balanceHistoryRecord.getSubtractedBalance(sumAmount);
            })
            .orElse(transactionRepository.sumAmount(userId));
    }
}
