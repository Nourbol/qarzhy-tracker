package kz.edu.astanait.qarzhytracker.domain;

import java.time.LocalDate;

public record TransactionFilter(String search,
                                TransactionType type,
                                LocalDate from,
                                LocalDate to) {
}
