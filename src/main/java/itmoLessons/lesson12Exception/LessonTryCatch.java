package itmoLessons.lesson12Exception;

import java.io.IOException;

public class LessonTryCatch {
    public static void main(String[] args) {

        // Exception - исключения
        //1. Исключения времени выполнения (наследники RunTimeException)
        //unchecked, необрабатываемы, неотслеживаемые, неконтролируемые
        //разработчики могут по своему усмотрению обрабатывать данный тип исключения

        //2. Исключения времени компиляции (все остальные наследники Exception)
        //checked, обрабатываемые, отслеживаемые, контролируемые
        //разработчики обязаны обрабатывать данный тип исключений
        int a = 1;
        int b = 0;
        int res;
        int[] ints = new int[3];
        String s = null;
        Object obj = "123";
        String s1 = (String) obj;
        System.out.println(s1.toUpperCase());
        Integer integer = null;
        try {
            if (System.currentTimeMillis() % 2 == 0) integer = (Integer) obj;
            else ints[90] = 100;
        } catch (ClassCastException e) { //перехватит ClassCastException
            //и всех его потомков
            System.out.println("Проблемы с приведением типов " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива " + e.getMessage());
        }

        // 2. несколько исключений обрабатываются одинаковым способом
        try {
            if (System.currentTimeMillis() % 2 == 0) integer = (Integer) obj;
            else ints[90] = 100;
            //ставится побитовое исключение |
        } catch (ClassCastException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Проблема с приведением или выход за пределы массива " + e.getMessage());
        } finally { // необязательный блок finally
            // инструкции выполненятся в случае любого исключения в блоке try
            // инструкции связанные с закрытием ресурсов
        }

        // 3. через общего родителя - несколько исключений обрабатываются одинаковым способом
        try {
            if (System.currentTimeMillis() % 2 == 0) integer = (Integer) obj;
            else ints[90] = 100;
        } catch (RuntimeException e) { //перехватывает всех наследников RuntimeException
            System.out.println("Исключение времени выполнения " + e.getMessage());
        }

        // 4. через общего родителя - несколько исключений обрабатываются одинаковым способом
        // некоторые из них обрабатываются другим способом
        //Важное правило:
        //Всегда размещайте catch-блоки от самых конкретных (дочерних) исключений к самым общим (родительским).
        try {
            if (System.currentTimeMillis() % 2 == 0) integer = (Integer) obj;
            else ints[90] = 100;
        } catch (ClassCastException e) { // Дочерний класс
            System.out.println("Исключение приведения типов " + e.getMessage());
        } catch (RuntimeException e) {  // Родительский класс
            System.out.println("Исключение времени выполнения " + e.getMessage());
        }

//
//        try {
//            readFromFileJson("file.txt");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            Calculate.random(2,2);
        } catch (CalcException e) {
            e.printStackTrace();

        }
    }


    //наш метод выбрасывает исключение при этом ответственность на обработку исключения ложится туда где он будет вызываться
    public static void readFromFileJson(String fileName) throws IOException {
        if (!fileName.endsWith(".json")) {
            //исключение времени компиляции
            throw new IOException("Проблема с файлом");
        }
        System.out.println("Чтение из файла");
    }

}
