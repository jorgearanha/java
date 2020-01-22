package com.example.project.domain.validators;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyDateValidator implements ConstraintValidator<MyDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        Calendar teste = zeraDia(Calendar.getInstance());
        
        teste.add(Calendar.DAY_OF_MONTH, 1);

        return date.compareTo(teste.getTime()) >= 0;
    }

    public Calendar zeraDia(Calendar cal) {
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
    
}