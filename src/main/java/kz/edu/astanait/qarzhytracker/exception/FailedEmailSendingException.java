package kz.edu.astanait.qarzhytracker.exception;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;

public class FailedEmailSendingException extends ServiceException {

    public FailedEmailSendingException(final String message) {
        super(ErrorType.FAILED_EMAIL_SENDING, message);
    }
}
