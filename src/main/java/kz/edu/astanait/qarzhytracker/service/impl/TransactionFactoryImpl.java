package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.provider.CategoryProvider;
import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.TransactionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionFactoryImpl implements TransactionFactory {

    private final UserRepository userRepository;
    private final CategoryProvider categoryProvider;
    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    public List<Transaction> create(final List<BankStatementTransaction> bankStatementTransactions,
                                    final UUID userId) {
        var user = userRepository.getReferenceById(userId);
        var transactions = mapper.mapToTransactionEntities(bankStatementTransactions);
        user.addTransactions(transactions);
        return mapper.mapToTransactions(transactions);
    }

    @Override
    public List<Transaction> create(final SaveTransactionsRequest request, final UUID userId) {
        return request.transactions().stream()
                      .map(saveTransactionRequest -> create(saveTransactionRequest, userId))
                      .toList();
    }

    @Override
    public Transaction create(final SaveTransactionRequest request, final UUID userId) {
        var user = userRepository.getReferenceById(userId);
        var category = categoryProvider.getUserCategoryById(request.categoryId(), userId);
        var createdTransaction = repository.save(mapper.mapToTransactionEntity(request, user, category));
        return mapper.mapToTransaction(createdTransaction);
    }
}
