package kz.edu.astanait.qarzhytracker.exception;

public class ExpiredVerificationCodeException extends RuntimeException {

    public ExpiredVerificationCodeException(final String message) {
        super(message);
    }
}
