package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import java.util.UUID;

public interface TransactionModifier {

    void update(UUID transactionId, UUID userId, SaveTransactionRequest request);

    void delete(UUID transactionId, UUID userId);
}
