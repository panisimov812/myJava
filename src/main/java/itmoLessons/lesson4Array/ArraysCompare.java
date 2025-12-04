package itmoLessons.lesson4Array;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysCompare {
    /**
     * compare() - это метод интерфейса Comparator<T>,
     * который используется для сравнения двух объектов.
     * Он определяет порядок сортировки элементов.
     */

    public static void main(String[] args) {
        //Пример 1: Сравнение чисел
        Integer[] numbers = {5, 2, 8, 1, 9};
        System.out.println("до сортировки " + Arrays.toString(numbers));
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(numbers));
        //сортировка по убыванию
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(numbers));

/**
 * String[] words = {"apple", "cat", "banana", "hi"};
 *
 * Arrays.sort(words, new Comparator<String>() {
 *     @Override
 *     public int compare(String s1, String s2) {
 *         return s1.length() - s2.length(); // по длине строки
 *     }
 * });
 *
 */
//сортировка строки по длинне
        String[] words = {"apple", "cat", "banana", "hi"};
        System.out.println("до сортировки строкм " + Arrays.toString(words));

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(Arrays.toString(words));
    }

}
