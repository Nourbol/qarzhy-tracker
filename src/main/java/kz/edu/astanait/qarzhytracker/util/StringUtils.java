package kz.edu.astanait.qarzhytracker.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class StringUtils {

    public BigDecimal extractBigDecimalFrom(final String string) {
        var numericString = string.replaceAll("[^\\d,]", "")
                .replace(" ", "")
                .replace(",", ".");
        return new BigDecimal(numericString);
    }
}
