package kz.edu.astanait.qarzhytracker.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Transaction(UUID id,
                          LocalDate date,
                          BigDecimal amount,
                          String details,
                          Category category) {

    public Transaction(final UUID id, final LocalDate date, final BigDecimal amount, final String details) {
        this(id, date, amount, details, null);
    }
}
