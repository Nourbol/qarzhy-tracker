package kz.edu.astanait.qarzhytracker.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder(builderClassName = "Builder")
public record Finance(LocalDate date,
                      BigDecimal amount,
                      FinanceType type,
                      String details) {
}
