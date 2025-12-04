package itmoLessons.lesson5StringBuilder.examples;

public class SubStringExample {
    public static void main(String[] args) {
        String text = "Hello World";
        // substring(int beginIndex) - от указанного индекса до конца
        String subStr1 = text.substring(6);
        System.out.println("substring(6): " + subStr1); //substring(6): World

        // substring(int beginIndex, int endIndex) - от beginIndex до endIndex-1
        String subStr2 = text.substring(0,5);
        System.out.println("substring(6): " + subStr2); //substring(6): Hello
    }

}
