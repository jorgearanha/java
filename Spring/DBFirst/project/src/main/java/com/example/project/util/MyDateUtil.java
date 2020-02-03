package com.example.project.util;

import java.util.Calendar;
import java.util.Date;

/**
 * MyDateUtil
 */
public class MyDateUtil {

    public static Date dayPlusOne() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    public static Date datePlus(Date date, int x) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, x);

        return cal.getTime();
    }

    public static Date zeraDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date fimDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MILLISECOND, -1);

        return cal.getTime();
    }

    public static Date montaDate(int dia, int mes, int ano) {
        Calendar cal = Calendar.getInstance();

        mes--;
        cal.set(ano, mes, dia);

        return cal.getTime();
    }

    public static boolean maiorOuIgual(Date date, Date reference) {
        return date.compareTo(reference) >= 0;
    }

    public static boolean menorOuIgual(Date date, Date reference) {
        return date.compareTo(reference) <= 0;
    }

    public static String stringDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
    }
}