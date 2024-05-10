package kz.edu.astanait.qarzhytracker.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public record SaveCategoryRequest(String name,
                                  BigDecimal goal,
                                  String resetCron,
                                  LocalDateTime startAt,
                                  Integer priority,
                                  Duration remindBefore,
                                  Boolean active) {
}
