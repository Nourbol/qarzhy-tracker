package kz.edu.astanait.qarzhytracker.util;

import kz.edu.astanait.qarzhytracker.exception.FailedObjectConstructionException;
import kz.edu.astanait.qarzhytracker.exception.MissingConstructorException;
import org.springframework.util.ReflectionUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.function.Function;

public final class ConstructorUtil {

    private ConstructorUtil() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    public static <T> T createInstance(final Constructor<T> constructor, final Function<Parameter, ?> parameterMapper) {
        try {
            var parameters = constructor.getParameters();
            var parameterValues = new Object[parameters.length];
            for (var index = 0; index < parameters.length; index++) {
                parameterValues[index] = parameterMapper.apply(parameters[index]);
            }
            return constructor.newInstance(parameterValues);
        } catch (Exception exception) {
            throw new FailedObjectConstructionException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> findConstructorWithAnnotatedParameters(
            final Class<T> type,
            final Class<? extends Annotation> annotationType
    ) {
        return (Constructor<T>) Arrays.stream(type.getDeclaredConstructors())
                .filter(constructor -> allParametersHaveAnnotation(constructor, annotationType))
                .findAny()
                .orElseThrow(() -> MissingConstructorException.missingConstructorWithAnnotatedParameters(type, annotationType));
    }

    public static boolean allParametersHaveAnnotation(final Constructor<?> constructor,
                                                      final Class<? extends Annotation> annotationType) {
        return ArrayUtils.noneMatch(
                constructor.getParameterAnnotations(),
                parameterAnnotations -> ArrayUtils.noneMatch(
                        parameterAnnotations,
                        parameterAnnotation -> parameterAnnotation.annotationType().equals(annotationType)
                )
        );
    }

    public static <T> Constructor<T> getNoArgsConstructor(final Class<T> type) {
        try {
            return ReflectionUtils.accessibleConstructor(type);
        } catch (final NoSuchMethodException e) {
            throw MissingConstructorException.missingNoArgsConstructor(type, e);
        }
    }
}
