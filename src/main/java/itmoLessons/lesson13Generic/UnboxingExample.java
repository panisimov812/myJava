package itmoLessons.lesson13Generic;

//Автораспаковка — объект → примитив.
public class UnboxingExample {
    Integer a = 10;
    int b = a; //unboxing

    //Фактически: int b = a.intValue();

   // ⚠️ Опасность NullPointerException
//    Integer c = null;
//    int c = c; // 💥 NPE
}
