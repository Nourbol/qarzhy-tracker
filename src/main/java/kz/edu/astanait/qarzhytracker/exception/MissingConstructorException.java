package kz.edu.astanait.qarzhytracker.exception;

import java.lang.annotation.Annotation;

public class MissingConstructorException extends RuntimeException {

    public MissingConstructorException(final String message, final Exception cause) {
        super(message, cause);
    }

    public MissingConstructorException(final String message) {
        super(message);
    }

    public static MissingConstructorException missingConstructorWithAnnotatedParameters(final Class<?> type,
                                                                                        final Class<? extends Annotation> annotation) {
        return new MissingConstructorException(
                String.format("Class %s does not have a constructor with parameters annotated wih %s", type, annotation)
        );
    }

    public static MissingConstructorException missingNoArgsConstructor(final Class<?> type, final Exception cause) {
        return new MissingConstructorException(
                String.format("Class %s does not have constructor with no arguments", type), cause
        );
    }
}
