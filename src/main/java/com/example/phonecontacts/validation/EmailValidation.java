package com.example.phonecontacts.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
