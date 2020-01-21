package com.example.project.domain.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

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
public class MyDateValidatorTest {

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    MyDateValidator myDateValidator;

    @Test
    public void should_NotBeValid_WhenIsNull() {
        assertFalse(myDateValidator.isValid(null, constraintValidatorContext));
    }

    @Test
    public void should_NotBeValid_WhenMoreThan7daysBehind() {
        Calendar cal = today();
        cal.add(Calendar.DAY_OF_MONTH, -8);
        assertFalse(myDateValidator.isValid(cal.getTime(), constraintValidatorContext));
    }

    @Test
    public void should_NotBeValid_WhenMoreThan7daysAbove() {
        Calendar cal = today();
        cal.add(Calendar.DAY_OF_MONTH, 8);
        assertFalse(myDateValidator.isValid(cal.getTime(), constraintValidatorContext));
    }

    @Test
    public void should_BeValid_When7daysBehind() {
        Calendar cal = today();
        cal.add(Calendar.DAY_OF_MONTH, -7);

        assertTrue(myDateValidator.isValid(cal.getTime(), constraintValidatorContext));
    }

    @Test
    public void should_BeValid_When7daysAbove() {
        Calendar cal = today();
        cal.add(Calendar.DAY_OF_MONTH, 8);
        cal.set(Calendar.MILLISECOND, -1);

        assertTrue(myDateValidator.isValid(cal.getTime(), constraintValidatorContext));
    }

    @Test
    public void should_BeValid_WhenIsToday() {
        assertTrue(myDateValidator.isValid(today().getTime(), constraintValidatorContext));
    }

    public Calendar today() {
        Calendar cal;

        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
       
        return cal;
    }

}