package kz.edu.astanait.qarzhytracker.domain;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SaveBalanceHistoryRecordRequest(@NotNull BigDecimal balance,
                                              @NotNull LocalDate recordedAt) {
}
