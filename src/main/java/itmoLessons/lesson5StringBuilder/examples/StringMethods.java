package itmoLessons.lesson5StringBuilder.examples;

public class StringMethods {

    public static void main(String[] args) {
        String text = "Hello World";

        //Длинна строки
        int lenght = text.length();
        System.out.println(".length: " + lenght);

        //получение символа по индексу
        char ch = text.charAt(1);
        System.out.println("получение символа по индексу char: " + ch);

        //Проверка на пустоту
        boolean isEmpty = text.isEmpty(); //false
        boolean isBlank = text.isBlank(); //false

        /**
         * МЕТОДЫ СРАВНЕНИЯ
         */
        String str1 = "Hello";
        String str2 = "World";
        String str3 = "hello";

        // Сравнение
        boolean equals = str1.equals(str2); // false
        boolean equalsIgnoreCase = str1.equalsIgnoreCase(str3); // true

        // Сравнение с учетом порядка
        int compare = str1.compareTo(str2); // отрицательное число
        int compareIgnoreCase = str1.compareToIgnoreCase(str3); // 0
        System.out.println(compareIgnoreCase);
    }

}
