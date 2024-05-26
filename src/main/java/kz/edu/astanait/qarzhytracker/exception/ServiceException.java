package kz.edu.astanait.qarzhytracker.exception;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorType errorType;

    public ServiceException(final ErrorType errorType, final String message) {
        super(message);
        this.errorType = errorType;
    }
}
