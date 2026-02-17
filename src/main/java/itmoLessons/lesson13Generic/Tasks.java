package itmoLessons.lesson13Generic;

public class Tasks {

    /*
    Требования:
        Возвращает первый элемент массива
        Если массив null или пустой → бросает IllegalArgumentException
        Должен работать для String[], Integer[], User<?>[]

        пример вывода
        String[] s = {"a", "b"};
        System.out.println(firstElement(s)); // "a"
     */
    public static <T> T firstElement(T[] arr) {
        for (int i = 0; i < arr.length;) {
            if (arr[i] != null) {
              return arr[i];
            } else {
                throw new IllegalArgumentException("Значение элемент пустой или равен null");
            }
        }
        return null;
    }




    /*🟡 Задача 2 — Ограничение типа (extends)
    Цель: понять T extends Number.
    Напиши метод:
        Требования:
            Складывает два числа
            Возвращает double
        Должен работать для:
            Integer
            Double
            Long
        Подсказка:
            используй a.doubleValue()

    */
    public static <T extends Number> double sum(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }


    public static void main(String[] args) {

        String[] s = {"a", "b"};
        System.out.println(firstElement(s)); // "a"

        Integer[] i = {10, 20};
        System.out.println(firstElement(i)); // 10
        System.out.println(sum(1123123,1.1));

    }
}
