package com.hcg.booking.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidFutureOrPresentDateValidator implements ConstraintValidator<ValidFutureOrPresent, String> {
    private String pattern;

    @Override
    public void initialize(ValidFutureOrPresent constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true; // Empty values are considered valid
        }

        try {
            LocalDate date = LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
            return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}
