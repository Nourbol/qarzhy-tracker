package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.mapper.TransactionMapper;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.TransactionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionFactoryImpl implements TransactionFactory {

    private final UserRepository userRepository;
    private final TransactionMapper mapper;

    @Override
    @Transactional
    public List<Transaction> create(final String email,
                                    final List<BankStatementTransaction> bankStatementTransactions) {
        var user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User with email %s was not found".formatted(email)));
        var transactions = mapper.mapToTransactionEntities(bankStatementTransactions);
        user.addTransactions(transactions);
        return mapper.mapToTransactions(transactions);
    }
}
