package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TransactionMapper {

    public List<TransactionEntity> mapToTransactionEntities(final List<BankStatementTransaction> bankStatementTransactions) {
        return bankStatementTransactions.stream()
            .map(this::mapToTransactionEntity)
            .toList();
    }

    public TransactionEntity mapToTransactionEntity(final BankStatementTransaction bankStatementTransaction) {
        var transaction = new TransactionEntity();
        transaction.setAmount(bankStatementTransaction.amount());
        transaction.setOperationDate(bankStatementTransaction.date());
        transaction.setDetails(bankStatementTransaction.details());
        return transaction;
    }

    public List<Transaction> mapToTransactions(final List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream()
            .map(this::mapToTransaction)
            .toList();
    }

    public Transaction mapToTransaction(final TransactionEntity transactionEntity) {
        return new Transaction(
            transactionEntity.getId(),
            transactionEntity.getOperationDate(),
            transactionEntity.getAmount(),
            transactionEntity.getDetails()
        );
    }
}
