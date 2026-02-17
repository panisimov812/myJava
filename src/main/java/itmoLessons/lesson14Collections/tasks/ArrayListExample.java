package itmoLessons.lesson14Collections.tasks;

import itmoLessons.lesson14Collections.Student;

import java.util.ArrayList;

public class ArrayListExample {
    /*
    Создаётся внутренний массив на 30 элементов
    new ArrayList<>(30);

    ArrayList → внутри обычный массив
    Что важно:
    ✔ хранит дубли
    ✔ хранит null
    ✔ сохраняет порядок добавления
    ✔ быстрый доступ по индексу O(1)
    ❌ вставка в середину — O(n)
     */

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(1, "Petr", 20));
        list.add(new Student(1, "Anna", 20));
        list.add(new Student(1, "Max", 20));

        /*
        Все элементы начиная с индекса 1 сдвигаются вправо
        Новый элемент встаёт на индекс 1
         */
        list.add(1, new Student(4, "Sasha", 18));
        System.out.println(list); //[Student{id=4, name='Sasha', age=18}, Student{id=1, name='Petr', age=20}, Student{id=1, name='Anna', age=20}, Student{id=1, name='Max', age=20}]

        //🔥 ЗАДАЧА 2 — remove и equals
        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(new Student(1,"Petr", 20));
        list.remove(new Student(1,"RandomName", 99));
     }




}
