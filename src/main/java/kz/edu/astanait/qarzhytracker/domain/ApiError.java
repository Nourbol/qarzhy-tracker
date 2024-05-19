package kz.edu.astanait.qarzhytracker.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

/**
 * This class represents an API error response containing information about the error.
 *
 * @param statusCode An HTTP status code for the error.
 * @param message    A message about the error.
 * @param data       Additional information about the error.
 * @param <T>        The type of error details that can be included in the response.
 */
public record ApiError<T>(int statusCode,
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
        return new ApiError<>(HttpStatus.BAD_REQUEST.value(), errorMessage, errorMessagePerField);
    }

    /**
     * Constructs an {@link ApiError} instance for general errors.
     *
     * @param httpStatus The HTTP status code representing the error.
     * @param exception  The exception that caused the error.
     * @param webRequest The web request associated with the error.
     * @return An {@link ApiError} instance for general errors.
     */
    public static ApiError<String> construct(final HttpStatus httpStatus,
                                             final Throwable exception,
                                             final WebRequest webRequest) {
        return new ApiError<>(httpStatus.value(), exception.getMessage(), webRequest.getDescription(false));
    }
}
