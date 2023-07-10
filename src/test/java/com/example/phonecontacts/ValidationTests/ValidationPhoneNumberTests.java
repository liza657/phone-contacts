package com.example.phonecontacts.ValidationTests;

import com.example.phonecontacts.entity.PhoneNumber;
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

@DataJpaTest
public class ValidationPhoneNumberTests {
    @Autowired
    private TestEntityManager entityManager;

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone("1234567890");

        Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(phoneNumber);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone("abc123");

        Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(phoneNumber);
        assertEquals(1, violations.size());

        ConstraintViolation<PhoneNumber> violation = violations.iterator().next();
        assertEquals("Invalid phone number", violation.getMessage());
    }

    @Test
    public void testPhoneNumberLength() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone("123456"); // Phone number length is less than 8 characters

        Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(phoneNumber);
        assertEquals(1, violations.size());

        ConstraintViolation<PhoneNumber> violation = violations.iterator().next();
        assertEquals("Invalid phone number", violation.getMessage());
    }

    @Test
    public void testPhoneNumberNull() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone(null); // Phone number is null

        Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(phoneNumber);
        assertEquals(1, violations.size());

        ConstraintViolation<PhoneNumber> violation = violations.iterator().next();
        assertEquals("Invalid phone number", violation.getMessage());
    }


}
