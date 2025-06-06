package kz.edu.astanait.qarzhytracker.domain;

import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

/**
 * This class represents an API error response containing information about the error.
 *
 * @param type     A type of the error.
 * @param message  A message about the error.
 * @param data     Additional information about the error.
 * @param <T>      The type of error details that can be included in the response.
 */
public record ApiError<T>(ErrorType type,
                          String message,
                          T data) {

    private static final String VALIDATION_ERROR_MESSAGE = "Invalid request body. Check the following fields: %s";

    /**
     * Constructs an {@link ApiError} instance representing validation errors.
     *
     * @param errorMessagePerField A map containing field names and their corresponding error messages.
     * @return An {@link ApiError} instance for validation errors.
     */
    public static ApiError<Map<String, List<String>>> constructValidationError(final Map<String, List<String>> errorMessagePerField) {
        String errorMessage = String.format(VALIDATION_ERROR_MESSAGE, errorMessagePerField.keySet());
        return new ApiError<>(ErrorType.INVALID_DATA, errorMessage, errorMessagePerField);
    }

    /**
     * Constructs an {@link ApiError} instance for general errors.
     *
     * @param errorType  The type of the error.
     * @param exception  The exception that caused the error.
     * @param webRequest The web request associated with the error.
     * @return An {@link ApiError} instance for general errors.
     */
    public static ApiError<String> construct(final ErrorType errorType,
                                             final Throwable exception,
                                             final WebRequest webRequest) {
        return new ApiError<>(errorType, exception.getMessage(), webRequest.getDescription(false));
    }
}
