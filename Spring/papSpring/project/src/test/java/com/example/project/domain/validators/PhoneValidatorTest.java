package com.example.project.domain.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * PhoneValidatorTest
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneValidatorTest {

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    PhoneValidator phoneValidator;

    // @Before
    // public void setUp() {
    //     phoneValidator = new PhoneValidator();
    // }

    @Test
    public void should_NotBeValid_WhenIsNull() {
        assertFalse(phoneValidator.isValid(null, constraintValidatorContext));
    }
    
    @Test
    public void should_NotBeValid_WhenNotNumber() {
        assertFalse(phoneValidator.isValid("String phone", constraintValidatorContext));
    }
    
    @Test
    public void should_NotBeValid_When7digits() {
        assertFalse(phoneValidator.isValid("7654321", constraintValidatorContext));
    }
        
    @Test
    public void should_NotBeValid_When12digits() {
        assertFalse(phoneValidator.isValid("119876543213", constraintValidatorContext));
    }

    @Test
    public void should_BeValid_When9digits() {
        assertTrue(phoneValidator.isValid("987654321", constraintValidatorContext));
    }

    @Test
    public void should_BeValid_When11digits() {
        assertTrue(phoneValidator.isValid("11987654321", constraintValidatorContext));
    }

} 