package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Budget(UUID id,
                     BigDecimal amount,
                     String repeatCron,
                     Integer priority,
                     Boolean active) {
}
