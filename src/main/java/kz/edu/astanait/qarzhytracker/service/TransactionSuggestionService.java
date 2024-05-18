package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionSuggestion;

import java.util.List;
import java.util.UUID;

public interface TransactionSuggestionService {

    List<TransactionSuggestion> suggest(List<BankStatementTransaction> bankStatementTransactions, UUID userId);

    TransactionSuggestion suggest(BankStatementTransaction bankStatementTransaction, UUID userId);
}
