package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.mapper.CategoryMapper;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.provider.TransactionProvider;
import kz.edu.astanait.qarzhytracker.service.TransactionReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionReaderImpl implements TransactionReader {

    private final TransactionMapper mapper;
    private final TransactionProvider provider;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<Transaction> getUserTransactions(final UUID userId, final TransactionFilter filter, final Pageable pageable) {
        var transactions = provider.getUserTransactions(userId, filter, pageable);
        return transactions.map(transaction -> {
            var category = transaction.getCategory();
            return Objects.isNull(category)
                ? mapper.mapToTransaction(transaction)
                : mapper.mapToTransaction(transaction, categoryMapper.mapToCategory(category));
        });
    }

    @Override
    public Transaction getUserTransaction(final UUID transactionId, final UUID userId) {
        var transaction = provider.getUserTransactionById(transactionId, userId);
        var category = transaction.getCategory();
        return Objects.isNull(category)
            ? mapper.mapToTransaction(transaction)
            : mapper.mapToTransaction(transaction, categoryMapper.mapToCategory(category));
    }
}
