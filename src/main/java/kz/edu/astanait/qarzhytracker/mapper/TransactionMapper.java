package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.Category;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionSuggestion;
import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import kz.edu.astanait.qarzhytracker.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public void mapToTransactionEntity(final SaveTransactionRequest saveTransactionRequest, final TransactionEntity transactionEntity) {
        transactionEntity.setOperationDate(saveTransactionRequest.date());
        transactionEntity.setAmount(saveTransactionRequest.amount());
        transactionEntity.setDetails(saveTransactionRequest.details());
    }

    public TransactionEntity mapToTransactionEntity(final SaveTransactionRequest saveTransactionRequest,
                                                    final UserEntity userEntity,
                                                    final CategoryEntity categoryEntity) {
        var transactionEntity = new TransactionEntity();
        transactionEntity.setOperationDate(saveTransactionRequest.date());
        transactionEntity.setAmount(saveTransactionRequest.amount());
        transactionEntity.setDetails(saveTransactionRequest.details());
        transactionEntity.setUser(userEntity);
        transactionEntity.setCategory(categoryEntity);
        return transactionEntity;
    }

    public Transaction mapToTransaction(final TransactionEntity transactionEntity, final Category category) {
        return new Transaction(
            transactionEntity.getId(),
            transactionEntity.getOperationDate(),
            transactionEntity.getAmount(),
            transactionEntity.getDetails(),
            category
        );
    }

    public Transaction mapToTransaction(final TransactionEntity transactionEntity) {
        return new Transaction(
            transactionEntity.getId(),
            transactionEntity.getOperationDate(),
            transactionEntity.getAmount(),
            transactionEntity.getDetails()
        );
    }

    public TransactionSuggestion mapToTransactionSuggestion(final BankStatementTransaction bankStatementTransaction) {
        return new TransactionSuggestion(
            bankStatementTransaction.date(),
            bankStatementTransaction.amount(),
            bankStatementTransaction.details()
        );
    }

    public TransactionSuggestion mapToTransactionSuggestion(final BankStatementTransaction bankStatementTransaction, final Category category) {
        return new TransactionSuggestion(
            bankStatementTransaction.date(),
            bankStatementTransaction.amount(),
            bankStatementTransaction.details(),
            category
        );
    }
}
