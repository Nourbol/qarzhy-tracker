package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BalanceHistoryRecord(UUID id,
                                   BigDecimal balance,
                                   LocalDate recordedAt) {

    public BigDecimal getSubtractedBalance(final BigDecimal subtrahend) {
        return balance.subtract(subtrahend);
    }
}
