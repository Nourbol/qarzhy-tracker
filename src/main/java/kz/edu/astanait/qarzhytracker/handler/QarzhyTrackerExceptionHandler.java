package kz.edu.astanait.qarzhytracker.handler;

import kz.edu.astanait.qarzhytracker.domain.ApiError;
import kz.edu.astanait.qarzhytracker.util.ValidationErrorUtil;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class QarzhyTrackerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<String>> handleException(final WebRequest request,
                                                            final Exception exception) {
        return handleThrowable(request, exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maps {@link Throwable} to a {@link org.springframework.http.ResponseEntity} containing {@link ApiError}.
     *
     * @param request    An object that contains information about the web request.
     * @param throwable  A throwable that has to be processed.
     * @param httpStatus An HTTP status of the response.
     * @return A {@link org.springframework.http.ResponseEntity} containing {@link ApiError}.
     */
    public ResponseEntity<ApiError<String>> handleThrowable(final WebRequest request,
                                                            final Throwable throwable,
                                                            final HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                             .body(ApiError.construct(httpStatus, throwable, request));
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
