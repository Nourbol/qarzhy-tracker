package kz.edu.astanait.qarzhytracker.handler;

import kz.edu.astanait.qarzhytracker.domain.ApiError;
import kz.edu.astanait.qarzhytracker.domain.ErrorType;
import kz.edu.astanait.qarzhytracker.exception.ServiceException;
import kz.edu.astanait.qarzhytracker.util.ValidationErrorUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class QarzhyTrackerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiError<String>> handleServiceException(final WebRequest request,
                                                                   final ServiceException serverException) {
        return handleThrowable(request, serverException, serverException.getErrorType());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError<String>> handleBadCredentialsException(final WebRequest request,
                                                                          final BadCredentialsException exception) {
        return handleThrowable(request, exception, ErrorType.BAD_CREDENTIALS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<String>> handleException(final WebRequest request,
                                                            final Exception exception) {
        return handleThrowable(request, exception, ErrorType.UNEXPECTED_EXCEPTION);
    }

    /**
     * Maps {@link Throwable} to a {@link ResponseEntity} containing {@link ApiError}.
     *
     * @param request    An object that contains information about the web request.
     * @param throwable  A throwable that has to be processed.
     * @param errorType  A type of the error.
     * @return A {@link ResponseEntity} containing {@link ApiError}.
     */
    public ResponseEntity<ApiError<String>> handleThrowable(final WebRequest request,
                                                            final Throwable throwable,
                                                            final ErrorType errorType) {
        log.error("Exception was thrown", throwable);
        return ResponseEntity.status(errorType.getHttpStatusCode())
                             .body(ApiError.construct(errorType, throwable, request));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final @NonNull MethodArgumentNotValidException exception,
        final @NonNull HttpHeaders headers,
        final @NonNull HttpStatusCode status,
        final @NonNull WebRequest request
    ) {
        var errorMessagesPerField = ValidationErrorUtil.getErrorMessagePerField(exception);
        return ResponseEntity.badRequest()
                             .body(ApiError.constructValidationError(errorMessagesPerField));
    }
}
