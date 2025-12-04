package itmoLessons.lesson4Array;

import java.util.Arrays;
import java.util.Random;

public class lesson4HomeWork {

    public static void main(String[] args) {

        Random random = new Random();
        /**
         * Заполните массив на N элементов слаучайными целыми числами
         * и выведите максимальное минимальное и среднее занчение
         */
        System.out.println(">>> Задача 1");
        int randomNum = (int) (Math.random() * 9);
        int[] ints1 = new int[randomNum];
        int minValue = ints1[0];
        int maxValue = ints1[0];

        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = i;
        }
        for (int num : ints1) {
            if (minValue > num) minValue = num;
            if (minValue < num) maxValue = num;
        }
        System.out.println("масcив>>> " + Arrays.toString(ints1));
        System.out.println("Минимальное число: " + minValue);
        System.out.println("Максимально число: " + maxValue);
        System.out.println("Среднее число: " + (minValue + maxValue) / 2);
        System.out.println("___________");


        System.out.println(">>> Задача 2");
        int indexFirst = 2;
        int[] ints2 = new int[21];
        //создаем диапозон чисел
        for (int i = indexFirst; i <= 20; i++) {
            if (i % 2 == 0) ints2[indexFirst++] = i;
        }
        //копирование с деапозоном (убираю нули)
        int[] copy4 = Arrays.copyOfRange(ints2, 3, 12);
        for (int i = copy4.length - 1; i >= 0; i--) {
            System.out.print(copy4[i] + " ");
        }

        System.out.println("\n___________");


        /**
         * Создайие массив из 11 случайных целых чисел [-1;1]
         * выведете массив в консоль
         * определите какой элемент встречается в массиве чаще всего и выведете в консоль
         * если два каких-то элемента встречаются одинаковое кол-во раз то не выводите ни чего
         */
        System.out.println("\n>>> Задача 3");
        int countMinusOne = 0;
        int countZero = 0;
        int countOne = 0;

        int[] array = new int[11];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(3) - 1; // генерирует числа: -1, 0, 1
        }
        System.out.println("созданный массив: " + Arrays.toString(array));
        for (int replyElem : array) {
            if (replyElem == 0) countZero++;
            else if (replyElem == -1) countMinusOne++;
            else if (replyElem == 1) countOne++;
        }
        System.out.println("0 отображается : " + countZero);
        System.out.println("-1 отображается : " + countMinusOne);
        System.out.println("1 отображается : " + countOne);

        int maxCount = Math.max(countMinusOne, Math.max(countZero, countOne));

        if ((countOne == countMinusOne) || (countZero == countMinusOne)) ;
        else if (maxCount == countMinusOne) System.out.println(" -1 встречается чаще");
        else if (maxCount == countOne) {
            System.out.println("1 встречается чаще");
        } else if (maxCount == countZero) {
            System.out.println("0 встречается чаще");
        }
        System.out.println("___________");


        //задача 4
        int[] arr = {7, 1, 2, 3, 8, 4, 5, 6, 9, 10};

        int[] firestArr = new int[arr.length];
        int[] secondArr = new int[arr.length];
        int firstIndex = 0;
        int secondIndex = 0;

        System.out.println("Не отсортированный массив " + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                firestArr[firstIndex++] = arr[i];  // Заполняем последовательно
            } else {
                secondArr[secondIndex++] = arr[i]; // Заполняем последовательно
            }
        }

        // Обрезаем массивы до реального размера
        firestArr = Arrays.copyOf(firestArr, firstIndex);
        secondArr = Arrays.copyOf(secondArr, secondIndex);

        Arrays.sort(firestArr);
        Arrays.sort(secondArr);

        System.out.println("Массив с четными индексами: " + Arrays.toString(firestArr));
        System.out.println("Массив с нечетными индексами: " + Arrays.toString(secondArr));

        int[] a = {2, 4, 5};
        int[] b = {2, 5, 6};
        int res = Arrays.compare(a, b);
        System.out.println(res); //-1 (первый меньше) 1 первый больше
    }
}

