package kz.edu.astanait.qarzhytracker.exception;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;

public class ExceededAttemptsException extends ServiceException {

    public ExceededAttemptsException(final String message) {
        super(ErrorType.EXPIRED_VERIFICATION_CODE, message);
    }
}
