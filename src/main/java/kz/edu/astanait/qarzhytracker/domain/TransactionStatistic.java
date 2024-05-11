package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;

public record TransactionStatistic(BigDecimal currentBalance,
                                   BigDecimal totalExpense,
                                   BigDecimal totalRevenue) {
}
