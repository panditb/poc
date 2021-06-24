package org.learning.hazelcast.jet.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public final class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date convertTodate(String openDate) throws Exception {
        if (openDate != null) {
           Date convertedDate = new SimpleDateFormat(DATE_FORMAT).parse(openDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(convertedDate);
            calendar.set( Calendar.HOUR_OF_DAY, 0);
            calendar.set( Calendar.MINUTE, 0);
            calendar.set( Calendar.SECOND, 0);
            calendar.set( Calendar.MILLISECOND, 0);
            return  calendar.getTime();
        }
        return null;
    }

    public static String convertToString(Date openDate) {
        if (openDate != null)
            return new SimpleDateFormat(DATE_FORMAT).format(openDate);
        return null;
    }

    public static String localDatetoString(LocalDate localDate) {
        if(localDate!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return localDate.format(formatter);
        }
        return null;
    }
    public static LocalDate stringToLocalDate(String localDate)  {
        if(localDate!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(localDate,formatter);
        }
        return null;
    }

    public static LocalDate sqlDateToLocalDate(java.sql.Date localDate)  {
        if(localDate!=null) {
           return localDate.toLocalDate();
        }
        return null;
    }

    public static void main(String[] args) throws  Exception {
        System.out.println(DateConverter.convertTodate("2021-05-13"));
    }
}
