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

@RunWith(MockitoJUnitRunner.class)
public class MyDateValidatorTest {

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    MyDateValidator validator;

    @Test
    public void should_isValid() {
        Calendar teste = Calendar.getInstance();
        teste.add(Calendar.DAY_OF_MONTH, 1);

        assertTrue(validator.isValid(teste.getTime(), constraintValidatorContext));
    }

    @Test
    public void should_isNotValid() {
        Calendar teste = zeraDia(Calendar.getInstance());
        
        teste.add(Calendar.DAY_OF_MONTH, 1);
        teste.add(Calendar.MILLISECOND, -1);

        assertFalse(validator.isValid(teste.getTime(), constraintValidatorContext));
    }

    public Calendar zeraDia(Calendar cal) {
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}