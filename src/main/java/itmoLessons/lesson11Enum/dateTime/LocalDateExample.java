package itmoLessons.lesson11Enum.dateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class LocalDateExample {

    public static void getLocalDateDescription() {
        //получение текущей даты
        LocalDate date = LocalDate.now();
        System.out.println("Текущая дата" + date);

        //Установка определенной даты
        LocalDate someDate = LocalDate.of(2012, Month.DECEMBER, 31);
        System.out.println("someDate " + someDate);

        //получение даты из строки, фомат: год - месяц - число
        String strWithDate ="2025-05-14";
        LocalDate parsedDate = LocalDate.parse(strWithDate);
        System.out.println("parsedDate " + parsedDate);



        //форматирование строки
        strWithDate = "14 декабря 2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        parsedDate = LocalDate.parse(strWithDate, formatter);
        System.out.println("объект LocalDate из строки" + parsedDate);

        //дату можно увеличить/уменьшить
        System.out.println(parsedDate.minusDays(1));
        System.out.println(parsedDate.minusMonths(1));

        //Время можно сравнивать
        LocalDate first = LocalDate.of(2012, Month.DECEMBER, 31);
        LocalDate likeFirst = LocalDate.of(2012, Month.DECEMBER, 31);
        LocalDate second = LocalDate.of(2022, Month.FEBRUARY, 3);

        //isEqual - возвращает true, если дата равна и false если нет
        boolean isEqual = first.isEqual(likeFirst);
        System.out.println("isEqual 31 декабря 2012/ 31 декабря 2012 " + isEqual);

        //isAfter - возвращает true, если вызывающий метод даты идет после той что передана
        boolean isAfter = second.isAfter(first);
        System.out.println("isAfter 31 февраля 2022/ 31 декабря 2012 " + isAfter);

        //isBefore - возвращает true, если вызывающий метод даты идет до той что передана
        boolean isBefore = first.isAfter(second);
        System.out.println("isBefore 31 декабря 2012/ 31 февраля 2022 " + isBefore);
    }

    public static void main(String[] args) {
        getLocalDateDescription();
    }

}
