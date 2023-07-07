package com.hcg.booking.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFutureOrPresentDateValidator.class)
@ReportAsSingleViolation
public @interface ValidFutureOrPresent {
    String message() default "Invalid date. Please provide a valid future or present date (yyyy/MM/dd).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String pattern() default "yyyy/MM/dd";
}
