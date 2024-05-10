package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface TransactionReader {

    Page<Transaction> getUserTransactions(UUID userId, TransactionFilter filter, Pageable pageable);

    Transaction getUserTransaction(UUID transactionId, UUID userId);
}
