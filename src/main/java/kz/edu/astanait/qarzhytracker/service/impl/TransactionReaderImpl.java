package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.service.TransactionReader;
import kz.edu.astanait.qarzhytracker.specification.TransactionSearchSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionReaderImpl implements TransactionReader {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    private final TransactionSearchSpecificationBuilder specificationBuilder;

    @Override
    public Page<Transaction> getUserTransactions(final UUID userId, final TransactionFilter filter, final Pageable pageable) {
        var specification = specificationBuilder.buildSpecification(filter);
        var transactions = repository.findAll(specification, pageable);
        return transactions.map(mapper::mapToTransaction);
    }
}
