package kz.edu.astanait.qarzhytracker.exception;

public class ExceededAttemptsException extends RuntimeException {

    public ExceededAttemptsException(final String message) {
        super(message);
    }
}
