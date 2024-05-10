package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import kz.edu.astanait.qarzhytracker.entity.UserEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TransactionMapper {

    public List<TransactionEntity> mapToTransactionEntities(final SaveTransactionsRequest saveTransactionsRequest, final UserEntity userEntity) {
        return saveTransactionsRequest.transactions()
            .stream()
            .map(saveTransactionRequest -> mapToTransactionEntity(saveTransactionRequest, userEntity))
            .toList();
    }

    public void mapToTransactionEntity(final SaveTransactionRequest saveTransactionRequest, final TransactionEntity transactionEntity) {
        transactionEntity.setOperationDate(saveTransactionRequest.date());
        transactionEntity.setAmount(saveTransactionRequest.amount());
        transactionEntity.setDetails(saveTransactionRequest.details());
    }

    public TransactionEntity mapToTransactionEntity(final SaveTransactionRequest saveTransactionRequest, final UserEntity userEntity) {
        var transactionEntity = new TransactionEntity();
        transactionEntity.setOperationDate(saveTransactionRequest.date());
        transactionEntity.setAmount(saveTransactionRequest.amount());
        transactionEntity.setDetails(saveTransactionRequest.details());
        transactionEntity.setUser(userEntity);
        return transactionEntity;
    }

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
