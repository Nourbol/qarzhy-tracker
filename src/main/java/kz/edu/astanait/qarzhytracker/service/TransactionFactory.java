package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionFactory {

    List<Transaction> create(List<BankStatementTransaction> bankStatementTransactions, UUID userId);

    List<Transaction> create(SaveTransactionsRequest request, UUID userId);

    Transaction create(SaveTransactionRequest request, UUID userId);
}
