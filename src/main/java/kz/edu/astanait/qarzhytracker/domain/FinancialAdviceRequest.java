package kz.edu.astanait.qarzhytracker.domain;

import java.time.LocalDate;

public record FinancialAdviceRequest(SupportedLanguage responseLanguage,
                                     LocalDate statisticPeriodFrom,
                                     LocalDate statisticPeriodTo) {
}
