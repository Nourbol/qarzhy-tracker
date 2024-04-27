package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Transaction(UUID id,
                          LocalDate date,
                          BigDecimal amount,
                          String details) {
}
