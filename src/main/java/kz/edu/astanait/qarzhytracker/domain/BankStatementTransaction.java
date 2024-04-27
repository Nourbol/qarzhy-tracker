package kz.edu.astanait.qarzhytracker.domain;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder(builderClassName = "Builder")
public record BankStatementTransaction(LocalDate date,
                                       BigDecimal amount,
                                       String details) {
}
