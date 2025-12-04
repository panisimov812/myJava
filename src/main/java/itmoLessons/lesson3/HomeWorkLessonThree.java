package itmoLessons.lesson3;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkLessonThree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean switcher = true;
        boolean binarySearchSwitcher = true;
        int counter = 0;
        int startElement;
        int randomInt = random.nextInt(9) + 1;


        for (startElement = 1; counter <= 55; counter += 2) {
            System.out.println(startElement);
            startElement += 2;
        }

        System.out.println(">>>Угадай число");
        do {
            System.out.println("Введите целое число");
            int guessNum = scanner.nextInt();
            if (guessNum == randomInt) {
                System.out.println("Вы угадали");
                switcher = false;
            } else if (guessNum == 0) switcher = false;
            else if (guessNum > randomInt) {
                System.out.println("число меньше");
            } else if (guessNum < randomInt) {
                System.out.println("число больше");
            }
        } while (switcher);

        // Бинарный поиск


    }



}

