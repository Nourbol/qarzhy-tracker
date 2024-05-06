package kz.edu.astanait.qarzhytracker.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SaveTransactionRequest(@NotNull
                                     LocalDate date,
                                     @NotNull
                                     BigDecimal amount,
                                     @NotBlank
                                     String details) {
}
