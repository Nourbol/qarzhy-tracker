package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
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
    private final TransactionRepository transactionRepository;
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
        var user = userRepository.getReferenceById(userId);
        var transactions = transactionRepository.saveAll(mapper.mapToTransactionEntities(request, user));
        return mapper.mapToTransactions(transactions);
    }
}
