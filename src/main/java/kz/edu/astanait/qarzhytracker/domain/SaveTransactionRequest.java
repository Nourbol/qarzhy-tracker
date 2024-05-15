package kz.edu.astanait.qarzhytracker.domain;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SaveTransactionRequest(@NotNull
                                     LocalDate date,
                                     @NotNull
                                     BigDecimal amount,
                                     @NotBlank
                                     String details,
                                     @Nullable
                                     UUID categoryId) {
}
