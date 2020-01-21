package com.example.project.domain.validators;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyDateValidator implements ConstraintValidator<MyDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null)
            return false;
    
        return date.after(ini()) && date.before(end());
    }

    /*
    // REGRA DE NEGÓCIO
    // A data só é aceita se estiver em um range de 7 dias antes e depois da data atual
    */

    //Retorna o milissegundo 0 de 7 dias atras
    private Date ini() {
        Calendar ini = today();
        ini.add(Calendar.DAY_OF_MONTH, -7);
        ini.add(Calendar.MILLISECOND,-1);

        return ini.getTime();
    }

    //Retorna o último milissegundo 7 dias a frente
    private Date end() {
        Calendar end = today();
        end.add(Calendar.DAY_OF_MONTH, 8);

        return end.getTime();
    }

    private Calendar today(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }
}