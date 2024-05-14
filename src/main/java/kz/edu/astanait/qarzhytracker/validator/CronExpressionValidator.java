package kz.edu.astanait.qarzhytracker.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kz.edu.astanait.qarzhytracker.validator.annotation.ValidCronExpression;
import org.springframework.scheduling.support.CronExpression;

public class CronExpressionValidator implements ConstraintValidator<ValidCronExpression, String> {

    @Override
    public boolean isValid(final String cronExpression, final ConstraintValidatorContext constraintValidatorContext) {
        return CronExpression.isValidExpression(cronExpression);
    }
}
