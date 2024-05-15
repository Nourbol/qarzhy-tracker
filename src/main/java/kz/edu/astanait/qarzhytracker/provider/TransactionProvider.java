package kz.edu.astanait.qarzhytracker.provider;

import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.specification.TransactionSearchSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionProvider {

    private final TransactionRepository repository;
    private final TransactionSearchSpecificationBuilder specificationBuilder;

    public TransactionEntity getUserTransactionById(final UUID id, final UUID userId) {
        return repository.findByIdAndUserId(id, userId)
                         .orElseThrow(() -> ResourceNotFoundException.transactionNotFound(id));
    }

    public Page<TransactionEntity> getUserTransactions(final UUID userId, final TransactionFilter filter, Pageable pageable) {
        var specification = specificationBuilder.buildSpecification(filter, userId);
        return repository.findAll(specification, pageable);
    }
}
