package itmoLessons.lesson5StringBuilder.homeWork;

import java.util.Arrays;
import java.util.Scanner;

public class TaskHomeWorkOne {
    /**
     * Заполнить массив
     * Задать массив на N слов
     * В циклк считывать с консоли слова scanner
     * и добавлять их в массив (добавлять новое слово в массив можно только, если в нем его еще нет)
     * В итоге в массиве будет только уникальные слова
     * Выход из программы по слову exit. (его в массив не добавлять) или
     * если массив заполнен
     * Перед завершением программы вывести массив в консоль
     */
    public static void main(String[] args) {
        String[] strArr = new String[3];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < strArr.length; i++) {
            System.out.println("введите слово ");
            String words = scanner.nextLine();
            if (words.toLowerCase().equals("exit")) break;
            if (!Arrays.asList(strArr).contains(words)) {
                strArr[i] = words;
            }
            else {
                System.out.println("слово уже есть, попробуйте другое");
            }
        }
        System.out.println(Arrays.toString(strArr));


    }
}
