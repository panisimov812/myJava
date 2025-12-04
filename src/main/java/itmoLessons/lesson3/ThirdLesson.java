package itmoLessons.lesson3;

import java.util.Scanner;

public class ThirdLesson {
    public static void main(String[] args) {

        //пользовательский ввод
        Scanner in = new Scanner(System.in);
        System.out.println("Введите целое число");
        //метод получения целого число
        int userNum = in.nextInt();
        System.out.println(userNum * userNum);


        while (true) {
            System.out.println("Введите целое положительное число " +
                    "или 0 для выхода из программы");
            userNum = in.nextInt();
            //директива break используется для выхода из того цикла в котором она написанна
            if (userNum == 0) break;
            if (userNum < 0) continue; //переход на следующую итерацию, инструкции после continue выполняться не будут
            System.out.println(userNum * userNum);
        }

        //do выполняется всегда в не зависимости от условия а потом уже while
        do {
            System.out.println("DO WHILE: Введите целое положительное число " +
                    "или 0 для выхода из программы");
            userNum = in.nextInt();
            if (userNum < 0) continue;
            System.out.println(userNum * userNum);
        } while (userNum != 0);

        int start = 1, end = 12;
        //вывести все четные числа от start до end
        //четное число: % 2 == 0
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        while (start <= end) {
            if (start % 2 == 0) System.out.println(start);
            start += 1;
        }

        for (start = 1, end = 12; //инициализация переменных (можно присвоить сколбко угодно)
             start <= end; //булевое выражение(условие) можно через логические операторы добавлять условия
             start += 1 /*обнавление значений переменных*/) {
            if (start % 2 == 0) {
                System.out.println(start);
            }
        }
        System.out.println("?>>>вывести все неотрицательные элементы последовательности\n");
        //вывести все неотрицательные элементы последовательности
        // 90 85 80 70 65 60 и тд
        for (int i = 90; i >= 0; i -= 5) {
            System.out.println(i);
        }

        System.out.println("?>>>вывести на экран первые 10 элементов последовательности 2 4 6 8 10");
        int firstElem;
        int counter = 0;

        for (firstElem = 2; counter < 10; firstElem += 2, counter += 1) {
            System.out.println(firstElem);
        }

        System.out.println("введите кол-во тарелок: ");
        int plateCounter = in.nextInt();
        System.out.println("Введите кол-во моющего средства");
        int soapBottle = in.nextInt();
        float eteration = 0.5F;

        do {
            if (plateCounter <= 0) break;
            int soapResult = (int) (plateCounter - eteration);
            plateCounter -= 1;
            soapBottle -= soapResult;
            System.out.println("средства осталось: " + soapBottle);
            System.out.println("тарелок вымато: " + plateCounter);
        } while (plateCounter != 0);

    }
}
