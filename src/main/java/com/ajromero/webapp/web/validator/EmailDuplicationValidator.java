package com.ajromero.webapp.web.validator;


import com.ajromero.webapp.persistence.repositories.ICustomers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;



@Component
@NoArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    ICustomers customers;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isExistEmail = false;
        if (email != null) {
            isExistEmail = customers.existsByEmail(email);
        }

        if (isExistEmail) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
             MessageFormat.format("{0} customer already exists with the given email account", email)
            ).addConstraintViolation();
        }

        return !isExistEmail;
    }
}
