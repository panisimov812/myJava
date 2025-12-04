package itmoLessons.lesson4Array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ArraysSortEx {

    public static void main(String[] args) {
        int[] intArr = {5, 2, 1, 8, 10};
        System.out.println("До сартировки " + Arrays.toString(intArr));
        Arrays.sort(intArr);
        System.out.println(Arrays.toString(intArr)); //[1, 2, 5, 8, 10]

        String[] strArr = {"Banana", "Apple", "Cherry"};
        System.out.println("До сартировки " + Arrays.toString(strArr));
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr)); //[Apple, Banana, Cherry]

        /**
         * Можно отсортировать не весь массив, а только его часть. Диапазон задается как [fromIndex, toIndex)
         * (от начального индекса включительно до конечного индекса исключительно)
         */
        int[] arr = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};
        System.out.println("До сартировки " + Arrays.toString(arr));
        //сортирует элементы с индексом 0, 1, 2
        Arrays.sort(arr, 0, 3);
        System.out.println(Arrays.toString(arr));

        /**
         * Сортировка по убыванию и с помощью Comparator
         * Для сортировки массивов объектов в порядке убывания используется Collections.reverseOrder().
         * Для настройки любого порядка сортировки применяется интерфейс Comparator.
         *  Integer[] arr1 = {5, 2, 1, 8, 10};
         *         Arrays.sort(arr1, Collections.reverseOrder());
         *         System.out.println(Arrays.toString(arr1)); // [10, 8, 5, 2, 1]
         *
         *         // Сортировка по убыванию с собственным Comparator
         *         Integer[] arr2 = {5, 2, 1, 8, 10};
         *         Arrays.sort(arr2, new Comparator<Integer>() {
         *             @Override
         *             public int compare(Integer a, Integer b) {
         *                 return b - a; // Если результат > 0, элементы меняются местами
         *             }
         *         });
         *         System.out.println(Arrays.toString(arr2)); // [10, 8, 5, 2, 1]
         */

        Integer[] arr1 = {5, 2, 1, 8, 10};
        System.out.println("До сортировки Collections.reverseOrder()" + Arrays.toString(arr1));
        Arrays.sort(arr1, Collections.reverseOrder());
        System.out.println(Arrays.toString(arr1));

        // Сортировка по убыванию с собственным Comparator
        Integer[] arr2 = {5, 2, 1, 8, 10};
        System.out.println("До сортировки new Comparator<Integer>()" + Arrays.toString(arr1));
        Arrays.sort(arr2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2; // Если результат > 0, элементы меняются местами
            }
        });
        System.out.println(Arrays.toString(arr2)); // [10, 8, 5, 2, 1]


    }
}
