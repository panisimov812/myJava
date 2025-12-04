package itmoLessons.lesson11Enum.dateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeExample {

    public static void getLocalTimeDescription() {
        LocalTime someTime = LocalTime.of(20, 50);
        someTime = LocalTime.of(20, 50, 45);
        System.out.println(someTime);

        //парсим время
        String strWithDate = "в 23 часа 45 минут и 49 секунд";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("в HH часа mm минут и ss секунд");
        LocalTime parseDate = LocalTime.parse(strWithDate, dtf);
        System.out.println("объект LocalDate из строки " + parseDate);

//получение времени из строки
        String strWithTime = "23:56:05"; //hour:minute:second
        LocalDate parseTime = LocalDate.parse(strWithTime);
        System.out.println("объект LocalTime из строки " + parseTime);

    }



}
