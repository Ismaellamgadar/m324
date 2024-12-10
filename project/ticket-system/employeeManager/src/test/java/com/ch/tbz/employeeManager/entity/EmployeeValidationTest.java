package com.ch.tbz.employeeManager.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidEmployee() {
        Employee employee = new Employee("John", "Doe", new Date(), 3);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertTrue(violations.isEmpty(), "Employee should be valid");
    }

    @Test
    void testFirstnameNotBlank() {
        Employee employee = new Employee("", "Doe", new Date(), 3);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
        assertEquals("Firstname is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testLastnameNotBlank() {
        Employee employee = new Employee("John", "", new Date(), 3);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
        assertEquals("Lastname is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testStartDateNotNull() {
        Employee employee = new Employee("John", "Doe", null, 3);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
        assertEquals("Start date is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testSkillLevelWithinRange() {
        Employee employee = new Employee("John", "Doe", new Date(), 6);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
        assertEquals("must be less than or equal to 5", violations.iterator().next().getMessage());
    }

    @Test
    void testSkillLevelNotNull() {
        Employee employee = new Employee("John", "Doe", new Date(), null);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
        assertEquals("Skill Level is mandatory", violations.iterator().next().getMessage());
    }
}