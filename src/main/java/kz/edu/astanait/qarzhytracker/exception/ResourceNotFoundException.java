package kz.edu.astanait.qarzhytracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public static ResourceNotFoundException transactionNotFound(final UUID transactionId) {
        return new ResourceNotFoundException("Transaction with id %s was not found".formatted(transactionId));
    }

    public static ResourceNotFoundException userNotFound(final UUID userId) {
        return new ResourceNotFoundException("User with id %s was not found".formatted(userId));
    }

    public static ResourceNotFoundException balanceHistoryRecordNotFound(final UUID recordId) {
        return new ResourceNotFoundException("Balance history record with id %s was not found".formatted(recordId));
    }
}
