package kz.edu.astanait.qarzhytracker.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionFactoryImpl implements TransactionFactory {

    private final UserRepository userRepository;
    private final CategoryProvider categoryProvider;
    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    public List<Transaction> create(final SaveTransactionsRequest request, final UUID userId) {
        return request.transactions().stream()
                      .map(saveTransactionRequest -> create(saveTransactionRequest, userId))
                      .toList();
    }

    @Override
    public Transaction create(final SaveTransactionRequest request, final UUID userId) {
        var user = userRepository.getReferenceById(userId);
        var category = categoryProvider.findByNameOrCreate(request.categoryName(), userId);
        var createdTransaction = repository.save(mapper.mapToTransactionEntity(request, user, category));
        return mapper.mapToTransaction(createdTransaction);
    }
}
