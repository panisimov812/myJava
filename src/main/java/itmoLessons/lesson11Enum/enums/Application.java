package itmoLessons.lesson11Enum.enums;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        Article article1 = new Article("Путешествие по Австралии");
        //передаем ссылку на страну
        //все страны final и static поэтому Вызываем класс и выбираем элемент
        article1.setCountry(Country.AUSTRALIA);
        System.out.println(article1.getCountry());

        Article article2 = new Article("Путешествие по Британии");
        article2.setCountry(Country.UK);
        System.out.println(article2.getCountry());

        Article article3 = new Article("Путешествие по Франции");
        article3.setCountry(Country.FRANCE);

        //Методы перечислений
        //получание массива констант
        //values() - вернет массив всех элементов перечисления Country
        Country[] countries = Country.values();
        System.out.println(Arrays.toString(countries)); //[AUSTRALIA, UK, FRANCE]
        //индекс элемента перечисления в массиве
        System.out.println(Country.FRANCE.ordinal()); //2

        //  Country - тип данных элемента массива
        //  country - переменная (на каждой этерации в ней будет ссылка на элемент массива) берется со строки 23
        for (Country country : countries) {
            System.out.println("country " + country.name());
        }
        //country AUSTRALIA
        //country UK
        //country FRANCE

        Country country = Country.valueOf("UK");
        System.out.println(country);

        Priority low = Priority.LOW;
        System.out.println(low.getCode()); //1

        //поменяли значение для элемента LOW
        low.setCode(2);
        System.out.println(low.getCode()); //2

        for (Priority pr1 : Priority.values()) {
            System.out.println(pr1.getCode());
        }

        //метод один, реализация разная (полиморфизм наследования)
        int  sumRes = Operation.SUM.action(12,12); //24
        System.out.println(sumRes);

        int multiRes = Operation.MULTI.action(12,12);
        System.out.println(multiRes);

        //создать enum, перечислябщий 3 любые планеты
        //в методе main перебрать массив с элементами перечисления,
        //вывести массу, радиус и название каждой планеты

        Planet[] planets = Planet.values();
        for (Planet planet : planets) {
            System.out.println(planet.getName());
            System.out.println(planet.getRadius());
        }

    }
}
