package itmoLessons.lesson5StringBuilder.homeWork;

import java.util.Scanner;

public class TaskHomeWorkPalindrome {
    /**
     * является ли строка палиндромом
     *  а роза упала на лапу Азора - палиндром
     *  ывмы -  не палиндром
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sbWord = new StringBuilder();

        System.out.println("Введите слово");
        sbWord.append(scanner.nextLine());

        String originalWord = sbWord.toString().replace(" ","").toLowerCase();
        String reverseWord = sbWord.reverse().toString().replaceAll(" ", "").toLowerCase();
        System.out.println("строка перевернута:" + sbWord.reverse().toString().replace(" ","").toLowerCase());

        if (originalWord.equals(reverseWord)) System.out.println("палиндром");
        else System.out.println("не палиндром");

    }

}
