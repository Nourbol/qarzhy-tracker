package kz.edu.astanait.qarzhytracker.util;

import java.math.BigDecimal;

public final class BigDecimalUtils {

    private BigDecimalUtils() {
        throw new UnsupportedOperationException();
    }

    public static BigDecimal fromString(final String string) {
        var numericString = string.replaceAll("[^\\d,]", "")
                .replace(" ", "")
                .replace(",", ".");
        return new BigDecimal(numericString);
    }
}
