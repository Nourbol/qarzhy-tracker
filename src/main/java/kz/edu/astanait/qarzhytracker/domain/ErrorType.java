package kz.edu.astanait.qarzhytracker.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorType {
    CATEGORY_NAME_TAKEN(HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_TAKEN(HttpStatus.BAD_REQUEST),
    FAILED_EMAIL_SENDING(HttpStatus.SERVICE_UNAVAILABLE),
    EXCEEDED_VERIFICATION_ATTEMPTS(HttpStatus.BAD_REQUEST),
    EXPIRED_VERIFICATION_CODE(HttpStatus.BAD_REQUEST),
    TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    BALANCE_HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND),
    VERIFICATION_CODE_NOT_FOUND(HttpStatus.NOT_FOUND),
    INVALID_DATA(HttpStatus.NOT_FOUND),
    UNEXPECTED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED);

    private final HttpStatusCode httpStatusCode;

    ErrorType(final HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
