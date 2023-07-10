package com.example.phonecontacts.ValidationTests;

import com.example.phonecontacts.validation.EmailValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
public class ValidationEmailTests {
    @Autowired
    private TestEntityManager entityManager;

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidEmail() {
        String email = "test@example.com";

        Set<ConstraintViolation<String>> violations = validator.validate(email);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidEmail() {
        EmailValidator emailValidator = new EmailValidator();

        assertFalse(emailValidator.isValid("invalid_email", null));
    }

    @Test
    public void testNullEmail() {
        EmailValidator emailValidator = new EmailValidator();

        assertFalse(emailValidator.isValid(null, null));
    }

}
