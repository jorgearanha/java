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
    public Date ini() {
        Calendar ini = Calendar.getInstance();
        ini.set(Calendar.HOUR_OF_DAY,0);
        ini.set(Calendar.MINUTE,0);
        ini.set(Calendar.SECOND,0);
        ini.set(Calendar.MILLISECOND,0);
        ini.add(Calendar.DAY_OF_MONTH, -7);

        return ini.getTime();
    }

    //Retorna o último milissegundo 7 dias a frente
    public Date end() {
        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY,0);
        end.set(Calendar.MINUTE,0);
        end.set(Calendar.SECOND,0);
        end.set(Calendar.MILLISECOND,0);
        end.add(Calendar.DAY_OF_MONTH, 8);
        end.add(Calendar.MILLISECOND, -1);

        return end.getTime();
    }
}