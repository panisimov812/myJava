package itmoLessons.lesson4Array;

import java.util.Arrays;

public class lessonArray {
    public static void main(String[] args) {
        int[] ints1 = new int[7]; //массив на 7 элементов
        //Для вывода в консоль массива используем класс Arrays
        //toString(ints1) создает строчку
        System.out.println(Arrays.toString(ints1));

        int len = 10;
        int[] ints4 = new int[len + 5];//создали массив на 15 элементов

        System.out.println("ints4>>>" + Arrays.toString(ints4));

        //как узнать длинну массива
        System.out.println("длинна ints4>>> " + ints4.length);

        //присволили значение через элемент
        ints4[2] = 56;
        System.out.println("присвоили новое знаечние элементу через индекс>>> " + Arrays.toString(ints4));

        //ArrayIndexOutOfBoundsException - выход за пределы массива
        //  System.out.println(ints4[100]);



        //доступ к элементам массива
        System.out.println("доступ к элементку ints4>>> " + ints4[3]);


        /**
         * Многомерный массив
         */
        // 3 длинна внешнего массива
        // 4 количество элементов внутри массивов
        //[[0,0,0,0],[0,0,0,0],[0,0,0,0]]
        int[][] ints5 = new int[3][4];
        //что бы вывести многомерный массив используют deeptoString
        System.out.println("Многомерный массив" + Arrays.deepToString(ints5));

        //[[0,0],[0,0,0,0],[0,0,0,0]] - при создании массивов с разным размером не нужно прописывать кол-во элементов
        int[][] ints61 = new int[3][];
        System.out.println(Arrays.deepToString(ints61)); //[null, null, null] - инфо по созданию мы не передали (отсутвие ссылки) поэтому там null
        ints61[0] = new int[2]; // добавляем массив целых чисел с размером 2
        ints61[1] = new int[3]; // добавляем массив целых чисел с размером 3
        ints61[2] = new int[4]; // добавляем массив целых чисел с размером 4
        System.out.println(Arrays.deepToString(ints61)); //[[0, 0], [0, 0, 0], [0, 0, 0, 0]]



        int[][] ints6 = new int[3][]; //[null, null, null]
        ints6[0] = new int[2];
        ints6[1] = new int[3];
        ints6[2] = new int[4];
        System.out.println(Arrays.deepToString(ints6)); //[[0, 0], null, null]
        ints6[2][1] = 90;
        ints6[1][2] = 36;
        //[[0, 0], [0, 0, 0], [0, 90, 0, 0]]
        ints61[2][1] = 90;
        System.out.println(Arrays.deepToString(ints61));

        /**
         * Передор значений массива c изменением
         * цикл for  предоставляет досутп к индексам
         */
        System.out.println("Передор значений массива через for");
        int[] arrForCycle = {12, 13, 14, 15, 16, 17, 18};
        for (int i = 0; i < arrForCycle.length; i++) {
            System.out.println(arrForCycle[i]); //прочитали элементы
            arrForCycle[i] += 3;
        }
        System.out.println(Arrays.toString(arrForCycle));

        int[] ints7 = {3, -6, 12, 0, 4};
        //for используется для изменения значения массива, вместо одного положить другое
        int sum = 0;
        for (int index = 0; index < ints7.length; index += 1) {
            //обращаемся к элементам массива в квадратных скобках передаем индекс элемнета массива
            //первая итерация ints7[0] *= ints7[0] получается тут мы умножили 0вой индекс на 0нулевой и перезаписали в массив ints7 3 * 3
            //вторая итерация ints7[1] *= ints7[1] получается тут мы умножили 0 на 0 и перезаписали в массив ints7
            ints7[index] *= ints7[index];
            sum += ints7[index];
        }
        System.out.println("Сумма масива: " + sum);

        //проход в обратном порядке
        //  System.out.println("Массив по которому мы пойдем в обратном порядке:" + Arrays.toString(ints7));
//        for (int i = ints7.length -1; i <ints7.length ; i++) {
//
//        }

        /**
         * Передор значений массива через
         * цикл foreach не предоставляет досутпа к индексам
         * НЕ ДАЕТ ВОЗМОЖНОСТЬ ИЗМЕНИТЬ ЗНАЧЕНИЕ ЭЛЕМЕНТА МАССИВА
         *
         * цикл сам определит размер массива и пойдет от первого до последнего элемента
         * нет вариации идти от последнего к первому
         */

        sum = 0;
        //int element - тип данных переменных массива и его название element это название
        //если тип данных массива Scanner или Double то и тип данных в переменной должен быть такой же
        for (int element : ints7) {
            //читаем значение элемента массива
            System.out.println(element);
            sum += element;
        }

        // найти и вывести в консоль минимально значение
        // элемент массива ints8
        double[] ints8 = {3.7, -6.2, 12.9, 0.4, 4.1};
        double minValue = ints8[0];
        for (double elem : ints8) {
            if (minValue > elem) minValue = elem;

        }
        System.out.println("minValue>>> " + minValue);

        //вывести случайный элемент массива
        int randomNum = (int) (Math.random() * ints8.length);
        System.out.println("случайный элемент массива>>> " + ints8[randomNum]);

        Arrays.sort(ints8);
        System.out.println("Отсартированный массив в порядке возрастания>>" + Arrays.toString(ints8));

        int element = Arrays.binarySearch(ints8, 3.7);
        //если элемент в массиве не найден то вернет значение меньше нуля
        System.out.println("индекс искомого элемента в бинарном поиске>>" + element);

        /**
         * копирование массива
         */
        //так копировать массивы нельзя (получается мы просто создали еще одну ссылку на существующий массив
        // то есть мы можем обратиться к массиву или через ints9 или через ints10
        //  double[] ints9 = {3.7, -6.2, 12.9, 0.4, 4.1};
        // double[] ints10 = ints9;

        //правильное копирование массива
        //в памяти будут занимать разные области
        // получилось два самостоятельных массива
        double[] ints9 = {3.7, -6.2, 12.9, 0.4, 4.1};
        double[] cloneInts9 = ints9.clone();

        //Arrays.copyOf(из какого массива копируем, сколько элементов копируем)
        double[] copyInts9 = Arrays.copyOf(ints9, 3);
        double[] copyInts30 = Arrays.copyOf(ints9, 30);
        System.out.println("Оригинальный массив>>>" + Arrays.toString(ints9)); //[3.7, -6.2, 12.9, 0.4, 4.1]
        System.out.println("скопированная часть массива>>>" + Arrays.toString(copyInts9)); //[3.7, -6.2, 12.9]
        System.out.println("скопированная часть массива>>>" + Arrays.toString(copyInts30)); //3.7, -6.2, 12.9, 0.4, 4.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0...

        double[] newDoubles = new double[10];
        /*
        1 - в начале откуда копируем
        2 - с какого индекса копируем
        3 - куда копируем (в какой массив)
        4 - начиная с какого индекса в новом массиве будут добавляться элементы
        5 - сколько всего элементов хотим скопировать
         */
        System.arraycopy(ints9, 1, newDoubles, 3, 2);
        System.out.println(Arrays.toString(newDoubles)); // [0.0, 0.0, 0.0, -6.2, 12.9, 0.0, 0.0, 0.0, 0.0, 0.0]


    }
}
