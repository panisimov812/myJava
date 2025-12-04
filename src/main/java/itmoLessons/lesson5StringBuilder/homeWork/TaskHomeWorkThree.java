package itmoLessons.lesson5StringBuilder.homeWork;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class TaskHomeWorkThree {
    /**
     * Заменить все буквы в слове на строчные а первую букву на заглавную
     * Например дано hello, получаем Hello/ дано HeLLo, получили Hello
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите слово");
        String word = scanner.nextLine();
        System.out.println(word.substring(0,1).toUpperCase(Locale.ROOT) + word.toLowerCase().substring(1));



    }
}
