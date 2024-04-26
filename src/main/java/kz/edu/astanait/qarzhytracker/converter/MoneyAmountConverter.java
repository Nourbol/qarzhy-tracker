package kz.edu.astanait.qarzhytracker.converter;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class MoneyAmountConverter implements ColumnDataConverter<BigDecimal> {

    private static final String MINUS_SIGN = "-";
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE = " ";
    private static final String COMMA = ",";
    private static final String PERIOD = ".";

    @Override
    public BigDecimal convert(String data) {
        var numericString = data.replaceAll("[^\\d,]", EMPTY_STRING)
            .replace(WHITESPACE, EMPTY_STRING)
            .replace(COMMA, PERIOD);
        var bigDecimal = new BigDecimal(numericString);
        return data.contains(MINUS_SIGN) ? bigDecimal.negate() : bigDecimal;
    }
}
