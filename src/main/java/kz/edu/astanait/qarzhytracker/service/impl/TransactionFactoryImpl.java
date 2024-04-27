package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.repository.TransactionRepository;
import kz.edu.astanait.qarzhytracker.service.TransactionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionFactoryImpl implements TransactionFactory {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    public List<Transaction> create(final List<BankStatementTransaction> bankStatementTransactions) {
        var transactions = mapper.mapToTransactionEntities(bankStatementTransactions);
        transactions = repository.saveAll(transactions);
        return mapper.mapToTransactions(transactions);
    }
}
