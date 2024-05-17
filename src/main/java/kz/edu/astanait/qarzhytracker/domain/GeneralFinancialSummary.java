package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;

public record GeneralFinancialSummary(BigDecimal currentBalance,
                                      BigDecimal totalExpense,
                                      BigDecimal totalRevenue) {
}
