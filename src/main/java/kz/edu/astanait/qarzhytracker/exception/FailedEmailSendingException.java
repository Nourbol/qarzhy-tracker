package kz.edu.astanait.qarzhytracker.exception;

public class FailedEmailSendingException extends RuntimeException {

    public FailedEmailSendingException(final String message) {
        super(message);
    }
}
