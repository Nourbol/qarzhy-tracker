package kz.edu.astanait.qarzhytracker.exception;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;

public class ExpiredVerificationCodeException extends ServiceException {

    public ExpiredVerificationCodeException(final String message) {
        super(ErrorType.EXPIRED_VERIFICATION_CODE, message);
    }
}
