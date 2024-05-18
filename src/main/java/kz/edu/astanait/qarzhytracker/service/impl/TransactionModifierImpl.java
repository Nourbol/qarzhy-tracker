package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.provider.CategoryProvider;
import kz.edu.astanait.qarzhytracker.provider.TransactionProvider;
import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.service.TransactionModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionModifierImpl implements TransactionModifier {

    private final TransactionMapper mapper;
    private final TransactionProvider provider;
    private final TransactionRepository repository;
    private final CategoryProvider categoryProvider;

    @Override
    public void update(final UUID transactionId, final UUID userId, final SaveTransactionRequest request) {
        var transaction = provider.getUserTransactionById(transactionId, userId);
        mapper.mapToTransactionEntity(request, transaction);
        var newCategoryName = request.categoryName();
        var category = transaction.getCategory();
        if (Objects.isNull(newCategoryName)) {
            transaction.setCategory(null);
        } else if (Objects.nonNull(category) && !Objects.equals(category.getName(), newCategoryName)) {
            var newCategory = categoryProvider.findByNameOrCreate(newCategoryName, userId);
            transaction.setCategory(newCategory);
        }
    }

    @Override
    public void delete(final UUID transactionId, final UUID userId) {
        var transaction = provider.getUserTransactionById(transactionId, userId);
        repository.delete(transaction);
    }
}
