package kz.edu.astanait.qarzhytracker.util;

import java.util.function.Predicate;

public final class ArrayUtils {

    private ArrayUtils() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    public static <T> boolean noneMatch(final T[] array, final Predicate<T> predicate) {
        for (var element : array) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }
}
