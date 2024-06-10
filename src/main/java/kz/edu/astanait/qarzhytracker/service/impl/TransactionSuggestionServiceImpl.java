package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionSuggestion;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.service.CategorySuggestionService;
import kz.edu.astanait.qarzhytracker.service.TransactionSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionSuggestionServiceImpl implements TransactionSuggestionService {

    private final TransactionMapper transactionMapper;
    private final CategorySuggestionService categorySuggestionService;

    @Override
    public List<TransactionSuggestion> suggest(final List<BankStatementTransaction> bankStatementTransactions, final UUID userId) {
        return bankStatementTransactions.stream()
            .map(bankStatementTransaction -> suggest(bankStatementTransaction, userId))
            .toList();
    }

    @Override
    public TransactionSuggestion suggest(final BankStatementTransaction bankStatementTransaction, final UUID userId) {
        return categorySuggestionService.suggest(bankStatementTransaction.details(), userId)
                                        .map(suggestedCategory -> transactionMapper.mapToTransactionSuggestion(
                                            bankStatementTransaction, suggestedCategory
                                        ))
                                        .orElseGet(() -> transactionMapper.mapToTransactionSuggestion(bankStatementTransaction));
    }
}
