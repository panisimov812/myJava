package itmoLessons.lesson13Generic;

public class Examples {

    public static void main(String[] args) {
        //Полезные методы классов-обёрток
        //Парсинг строк

        Integer y = Integer.valueOf("123");
        int x = Integer.parseInt("123");
        System.out.println(x); //123 - именно цифра
        System.out.println(y);  //123 - именно цифра

        //Сравнение (НЕ через ==)
        Integer a = 100;
        Integer b = 100;
        System.out.println(a.equals(b));

        //🔥 Boolean
        Boolean.parseBoolean("true");
        //🔥 Character
        Character.isDigit('5');      // true
        Character.isLetter('a');     // true
        Character.isUpperCase('A');  // true

        //🧠 Важные тонкие моменты (часто спрашивают)
        //Почему?
        //диапазон [-128..127] кэшируется
        //== сравнивает ссылки
        //➡ Всегда сравнивай через equals()
        Integer c = 123;
        Integer g = 123;
        System.out.println(c == g); //true




        Integer v = 128;
        Integer d = 128;
        System.out.println(v == d); // false



        //Константы - Это границы, которые int физически может хранить.
        //Потому что int — это 32 бита.
//        Integer.MAX_VALUE  //  2147483647
//        Integer.MIN_VALUE  // -2147483648
//        Double.NaN
//        Double.POSITIVE_INFINITY
        Integer z = Integer.MAX_VALUE;
        z++; // 💥 переполнение → станет MIN_VALUE
        System.out.println(z);

        //✅ Алгоритмы
        //Очень часто используют как «стартовое» значение:
//
//        int min = Integer.MIN_VALUE;
//        for (int v : arr) {
//            if (v < min) {
//                min = v;
//            }

    }
}
