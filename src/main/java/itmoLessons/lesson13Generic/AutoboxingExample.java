package itmoLessons.lesson13Generic;

import java.util.ArrayList;
import java.util.List;

public class AutoboxingExample {
    //Автоупаковка — это автоматическое превращение примитива в объект.
    public static void main(String[] args) {
        int a = 10;

        /*под капотом Integer b = Integer.valueOf(a);*/
        Integer b = 10; //autoboxing

        //Где ты постоянно её используешь (даже не замечая):
        List<Integer> list = new ArrayList<>();
        list.add(5);//int -> Integer

    }
}
