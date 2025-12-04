package itmoLessons.lesson4Array;

import java.util.Arrays;
import java.util.Objects;

public class lesson4 {
    //объявление массива
    public static void main(String[] args) {
        int[] arr;
        boolean[] boolArr;
        Objects[] objectsArr;
        arr = new int[10]; //массив на 10 элементов
        boolArr = new boolean[10];
        objectsArr = new Objects[10];
        System.out.println(Arrays.toString(arr)); // [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        System.out.println(Arrays.toString(boolArr)); //[false, false, false, false, false, false, false, false, false, false]
        System.out.println(Arrays.toString(objectsArr)); //[null, null, null, null, null, null, null, null, null, null]

        //смотрим длинну массива
        int arrLenth = arr.length;
        System.out.println(arrLenth); //10

        /*
        Инициализация массива в момент создания
         */
        int[] arr2 = {2, 21, 4, 52, 35};
        System.out.println(Arrays.toString(arr2)); //[2, 21, 4, 52, 35]

        //Заполнение массива значенияеми в цикле
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 2;
        }
        System.out.println(Arrays.toString(arr)); //[0, 2, 4, 6, 8, 10, 12, 14, 16, 18]

        /**
         Копирование массива
         **/
        int[] arrLinkTArr2 = arr; //не копирование а ссылка на один массив
        /*
        Способы копировать массив
        .clone() метод для полного копирования массива
         */
        int[] arr3 = arr.clone(); //полная копия массива


        /**
         * System.arraycopy()
         Берем исходный массив arr: [10, 20, 30, 40]
         Начинаем с индекса 0 (srcPos): Первый элемент — 10.
         Целевой массив arr2: [0, 0, 0, 0, 0, 0]
         Начинаем записывать с индекса 2 (destPos).
         Копируем 3 элемента (length).
         */
        int[] arrCopyOne = {1, 2, 3, 4, 5};
        int arrForCopy[];
        arrForCopy = new int[6];
        System.arraycopy(arrCopyOne, 2, arrForCopy, 2, 2);
        System.out.println(Arrays.toString(arrForCopy)); //[0, 0, 3, 4, 0, 0]


        /**Копирование массива
         * copyOf()
         */
        int[] arrX = new int[]{1, 2, 3};
        int[] arrCopy = Arrays.copyOf(arrX, 5); // из массива arr скопируются все элементы, элементы 4й и 5й заполнятся 0ми
        System.out.println(Arrays.toString(arrCopy));

        /**
         * Arrays.copyOfRange((originalArr, int from, int to)
         * возвращает массив, в который копируект часть массива originalArr, начиная с элемента с индексом from
         * to индекс можеть быть больше чем длина исходного массива, тогда
         * остальные элементы заполняются значениями по умолчанию.
         * Длина нового массива будет равна to - from.
         */

        int arrayForCopyOfRange[] = {12, 13, 14, 15, 16, 17, 18};
        int[] copy = Arrays.copyOfRange(arrayForCopyOfRange, 2, 12);
        System.out.println("Метод copyOfRange");
        System.out.println("Изначальный массив  " + Arrays.toString(arrayForCopyOfRange));
        System.out.println("Метода : " + Arrays.toString(copy));


        /**
         * через  foreach
         */
        System.out.println("Перебор через через foreach");
        for (int num : arr) {
            System.out.println(num);//вывод значений элементо массива
            num += 3; //не может изменить элемент тк num перезапишется на следующей итерации
        }
        System.out.println("вывод  foreach " + Arrays.toString(arr));


        /**
         * Сравенение массива
         * Оператор == сравнивает ссылки (адреса в памяти), а не содержимое.
         * Метод .equals() по умолчанию (из класса Object)
         * тоже сравнивает ссылки, если его не переопределили.
         *
         * А класс int[] не переопределяет метод equals,
         * поэтому он ведёт себя точно так же, как ==
         *
         * Метод Arrays.equals() из пакета java.util сравнивает элементы массива по содержимому.
         *
         * 📘 Алгоритм:
         * Проверяет, равна ли длина массивов.
         * Потом проходит по каждому элементу и сравнивает значения.
         * Так как оба массива у тебя заполнены нулями (new int[10] → все элементы = 0),
         * результат будет true.
         */
        //нельзя сравнивать массив с помощью == или .equals()
        int[] arrA = new int[10];
        int[] arrB = new int[10];
        System.out.println(arrA.equals(arrB)); //false
        System.out.println(arrA == arrB); //false

        //Массивы нужно сравнивать по содежримому
        System.out.println(Arrays.equals(arrA, arrB)); // true


        /**
         * Другие операции над массивами с
         * использованием класса Arrays
         * Наполнение массива данными
         */

        //fill(arr, val) - наполняет массив arr значениями val
        System.out.println("Метод fill(arr, val)");
        int[] arr1 = new int[10];
        Arrays.fill(arr1, 1);
        System.out.println("наполнели через fill" + Arrays.toString(arr1));

        //Сортировка массива
        //sort(arr, int fromIndex, int toIndex) - сортирует массив методом quick
        //sort. Сортирует массив arr или часть массива arr от fromIndex до
        //toIndex.
        System.out.println("Сортировка массива sort(arr, int fromIndex, int tolndex");

        int[] arrForSort = new int[]{4, 3, 7, 1, 9, 2, 0, 8, 6, 5};
        Arrays.sort(arrForSort);
        System.out.println(Arrays.toString(arrForSort)); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //Содержит ли массив указанный элемент
        //Используется прием с представлением массива в виде коллекции и
        //вызовом метода contains()
        String[] stringArr = {"a", "c", "e"};
        boolean contains = Arrays.asList(stringArr).contains("e");
        System.out.println(contains);


    }


}
