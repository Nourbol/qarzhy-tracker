package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionModifier {

    List<Transaction> create(List<BankStatementTransaction> bankStatementTransactions, UUID userId);

    List<Transaction> create(SaveTransactionsRequest request, UUID userId);

    void update(UUID transactionId, UUID userId, SaveTransactionRequest request);

    void delete(UUID transactionId, UUID userId);
}
