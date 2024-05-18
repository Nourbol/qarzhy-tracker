package kz.edu.astanait.qarzhytracker.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransactionSuggestion(LocalDate date,
                                    BigDecimal amount,
                                    String details,
                                    Category category) {

    public TransactionSuggestion(final LocalDate date, final BigDecimal amount, final String details) {
        this(date, amount, details, null);
    }
}
