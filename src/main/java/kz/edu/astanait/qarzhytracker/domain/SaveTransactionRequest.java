package kz.edu.astanait.qarzhytracker.domain;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaveTransactionRequest(@NotNull
                                     LocalDate date,
                                     @NotNull
                                     BigDecimal amount,
                                     @NotBlank
                                     String details,
                                     @Nullable
                                     @Size(min = 1)
                                     String categoryName) {
}
