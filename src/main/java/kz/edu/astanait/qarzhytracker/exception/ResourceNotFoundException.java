package kz.edu.astanait.qarzhytracker.exception;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException {

    public ResourceNotFoundException(final ErrorType errorType, final String message) {
        super(errorType, message);
    }

    public static ResourceNotFoundException transactionNotFound(final UUID transactionId) {
        return new ResourceNotFoundException(ErrorType.TRANSACTION_NOT_FOUND, "Transaction with id %s was not found".formatted(transactionId));
    }

    public static ResourceNotFoundException userNotFound(final UUID userId) {
        return new ResourceNotFoundException(ErrorType.USER_NOT_FOUND, "User with id %s was not found".formatted(userId));
    }

    public static ResourceNotFoundException userNotFoundByEmail(final String email) {
        return new ResourceNotFoundException(ErrorType.USER_NOT_FOUND, "User with email %s was not found".formatted(email));
    }

    public static ResourceNotFoundException balanceHistoryRecordNotFound(final UUID recordId) {
        return new ResourceNotFoundException(ErrorType.BALANCE_HISTORY_NOT_FOUND, "Balance history record with id %s was not found".formatted(recordId));
    }

    public static ResourceNotFoundException categoryNotFound(final UUID categoryId) {
        return new ResourceNotFoundException(ErrorType.CATEGORY_NOT_FOUND, "Category with id %s was not found".formatted(categoryId));
    }

    public static ResourceNotFoundException verificationCodeNotFound(final UUID userId) {
        return new ResourceNotFoundException(ErrorType.VERIFICATION_CODE_NOT_FOUND, "No verification code was sent to user with id %s".formatted(userId));
    }
}
