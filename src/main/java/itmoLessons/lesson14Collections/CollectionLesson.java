package itmoLessons.lesson14Collections;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CollectionLesson {
    public static void main(String[] args) {
        //1. многопоточная или однопоточная среда
        //2. возможнасть хранить дублирующиеся элементы
        //3. возможность хранить элементы в порядке добавления
        //4. возможность хранить элементы в отсортированном виде и тд.

        System.out.println("ArrayList");
        //реализован на основе массива
        //можно хранить null
        //можно хранить дубдирующие элеименты
        //порядок хранения элементов соответствет порядку добавления

        Student student1 = new Student(1, "Petr", 28);
        Student student2 = new Student(2, "Anna", 19);
        Student student3 = new Student(3, "Maxim", 48);
        Student student4 = new Student(4, "Sasha", 28);
        //в коллекции мы храним студентов
        //хранить мы можем только обертки
        ArrayList<Student> studentArrayList = new ArrayList<>();

        //конструктор сразу определили емкасть, внутри создался массив на 30 элементов
        studentArrayList = new ArrayList<>(30);
        System.out.println(studentArrayList.size()); //метод size() вернет кол-во элементов в коллекции == 0
        //обращаемся к экземпояру коллекции
        studentArrayList.add(student1); // индекс 0
        studentArrayList.add(student2); // индекс 1
        //  studentArrayList.add(10, student3); //обращение к несуществующему индексу 10ого еше нет - IndexOutOfBoundsException
        studentArrayList.add(1, student3); // student3 встал на индекс 1 где был student2, а student2 подвинулся
        System.out.println(studentArrayList.size());
        studentArrayList.add(null);

        /*можно урезать внутренний массив до кол-во добавленных элементов,
         если после этого мы добавим еще элементы, массив динамически рассширится
         */
        studentArrayList.trimToSize();

        //получение по индексу
        System.out.println(studentArrayList.get(1));

        //удаление
        studentArrayList.remove(3); //remove() удаление по индексу так же он возвращает ссылку на то что удалено

        //возвращает ture - false, для удаления использует метод equals
        studentArrayList.remove(student1); //remove() принемает ссылку на удаляемый объект

        //создаем коллекцию из массива
        Student[] students = {student1, student2};
        List<Student> studentList = Arrays.asList(students);
        studentArrayList.removeAll(Arrays.asList(students));

        //пербор циклом for each
        for (Student student : studentArrayList) {
            //к имени каждого студента добавить "Student"
            student.setName("Student: " + student.getName());
            //если мы удалем элементы в for each это приведет к ConcurrentModificationException
        }

        //удалить всех кто старше 20 лет
        Iterator<Student> iterator = studentArrayList.listIterator();
        //hasNext() - возвращает true пока элементы присутствуюточ
        while (iterator.hasNext()) {
            //next() - возвращает ссылку на элемент коллекции
            if (iterator.next().getAge() > 20) {
                iterator.remove();
            }
        }

        //в LinkedList нельзя задать размер
        //внутри LinkedList не массив
        //можно хранить null и дублирующие элементы
        //тут мы создаем связный список в котором есть все элементы с предыдущей коллекции
        LinkedList<Student> studentLinkedList = new LinkedList<>(studentArrayList);
        //LinkedList является двунаправленный связанный список, все данные в нем храняться в Node()
        // он хранит ссылки только на первый (Node(student1))узел и на последний ( Node(student3))
        //Node(student1) Node(student2) Node(student3)  Node(student4)
        //каждый такой узел хранит ссылку на следующий элемент и на предыдущий

        System.out.println("Set");
        //Используется для хранение уникальных элементов
        //Обязательно должен быть переопределн метод equals

        //HashSet
        //Позволяет хранить null (при этом null всегда будет на первом месте)
        //порядок хранения может отличаться от порядка добавления
        //основан на hash таблице
        //должен быть переопределен hashCode

        Student student11 = new Student(1, "Petr", 28);
        Student student12 = new Student(2, "Anna", 19);
        Student student13 = new Student(3, "Sasha", 48);
        Student student14 = new Student(4, "Sasha", 28);

        HashSet<Student> studentHashSet = new HashSet<>();
        // на основе коллекции
        // список студентов
        List<Student> studentList1 = Arrays.asList(student11, student12, student13, student14);
        //сюда н едобавятся одинаковые элементы
        studentHashSet= new HashSet<>(studentList1);
        studentHashSet.remove(student13);

        double avAge = 0;
        for (Student student : studentHashSet) {
                avAge += student.getAge();
        }
        System.out.println(avAge/studentHashSet.size());

        //LinkedHashSet
        //1. порядок хранения не отличается от порядка добавления
        //2. медленее, чем HashSet

        //TreeSet
        //1. хранит элементы в отсортированном виде
        //2. нельзя добавлять null
        //3. основа на алгоритме красно-черного дерева

        // Для добавления элемента в TreeSet необходимо:
        // 1. что бы класс, элементы которого будут хранится в TreeSet
        // реализовал интерфейс Comparable и его метод compareTo

        // 2. создать экземпляры класса Comparator
        TreeSet<Student> treeSet1 = new TreeSet<>(studentHashSet);
        treeSet1.add(student1);
        treeSet1.add(student2);
        treeSet1.add(student3);
        treeSet1.add(student4);

        //2. создать экземпляры класса Comparator

        Comparator<Student> studentComparator = new NameComparator().thenComparing(new AgeComparator());
        //если имена одинаковые смотрим на id через .thenComparing(new AgeComparator());
        TreeSet<Student> treeSet2 = new TreeSet<>(studentComparator);
        treeSet2.add(student13);
        treeSet2.add(student14);


        
    }
}
