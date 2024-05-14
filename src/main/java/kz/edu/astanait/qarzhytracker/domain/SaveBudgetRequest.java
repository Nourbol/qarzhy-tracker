package kz.edu.astanait.qarzhytracker.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import kz.edu.astanait.qarzhytracker.validator.annotation.ValidCronExpression;
import java.math.BigDecimal;

public record SaveBudgetRequest(@NotNull
                                @Digits(integer = 15, fraction = 6)
                                @DecimalMin(value = "0", inclusive = false)
                                BigDecimal amount,
                                @ValidCronExpression
                                String repeatCron,
                                @NotNull
                                @PositiveOrZero
                                Integer priority,
                                @NotNull
                                Boolean active) {
}
