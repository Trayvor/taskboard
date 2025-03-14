package com.portfolio.template.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import com.portfolio.template.annotation.FieldMatch;
import com.portfolio.template.exception.NoValidationFieldsException;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = new BeanWrapperImpl(value)
                    .getPropertyValue(this.firstFieldName);
            final Object secondObj = new BeanWrapperImpl(value)
                    .getPropertyValue(this.secondFieldName);
            valid = firstObj == null && secondObj == null || firstObj != null
                    && firstObj.equals(secondObj);
        } catch (final Exception exception) {
            throw new NoValidationFieldsException("There is no fields " + firstFieldName + " or "
                    + secondFieldName, exception);
        }

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(secondFieldName)
                    .addConstraintViolation();
        }

        return valid;
    }
}
