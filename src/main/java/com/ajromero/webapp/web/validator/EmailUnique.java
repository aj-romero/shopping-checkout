package com.ajromero.webapp.web.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailDuplicationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnique {

    String message() default "Check email address is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
