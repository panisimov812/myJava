package itmoLessons.lesson11Enum.dateTime;

import java.time.LocalDateTime;
import java.time.Month;

public class LocalDateTimeExample {

    public static void getLocalDateTimeDeserialization() {
        //Получение текущей даты и времени
        LocalDateTime current = LocalDateTime.now();
        System.out.println("Текушая дата и время" + current);

        //Установка текущей даты и времени
        LocalDateTime some = LocalDateTime.of(2020, Month.DECEMBER, 31, 12, 31);
        System.out.println("some" + some);

        //получение даты и времени из строки
        String strWithDate = "2017-05-14T23:56:05";
        LocalDateTime parseDate = LocalDateTime.parse(strWithDate);
        System.out.println("объект LocalDateTime из строки" + parseDate);

        //Время можно сравнивать
        LocalDateTime first = LocalDateTime.of(1999, Month.JULY, 10, 10, 20);
        LocalDateTime likeFirst = LocalDateTime.of(1999, Month.JULY, 10, 10, 20);
        LocalDateTime second = LocalDateTime.of(2027, Month.APRIL, 1, 22, 31);


        //isEqual - возвращает true, если дата равна и false если нет
        boolean isEqual = first.isEqual(likeFirst);
        System.out.println("isEqual 10 июля 1999 10:20/ 10 июля 1999 10:20 " + isEqual);

        //isAfter - возвращает true, если вызывающий метод даты идет после той что передана
        boolean isAfter = second.isAfter(first);
        System.out.println("isAfter 1 апреля 2027 22:30/ 10 июля 10:20 " + isAfter);

        //isBefore - возвращает true, если вызывающий метод даты идет до той что передана
        boolean isBefore = first.isAfter(second);
        System.out.println("isBefore 10 июля 1999 10:20/ 1 апреля 2027 22:30/" + isBefore);
    }
}
