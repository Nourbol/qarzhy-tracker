package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import java.util.List;

public interface TransactionFactory {

    List<Transaction> create(List<BankStatementTransaction> bankStatementTransactions);
}
