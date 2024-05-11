package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BalanceHistoryRecord(UUID id,
                                   BigDecimal balance,
                                   LocalDate recordedAt) {

    public BigDecimal getBalanceAdding(final BigDecimal augend) {
        return balance.add(augend);
    }
}
