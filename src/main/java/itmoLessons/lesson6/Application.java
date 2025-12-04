package itmoLessons.lesson6;

import itmoLessons.lesson6.books.Author;
import itmoLessons.lesson6.books.Book;
import itmoLessons.lesson6.books.Shelf;

import java.util.Arrays;

//полное имя класса: имя пакета + имя класса
public class Application {
    public static void main(String[] args) {
        // класс - способ описания сущности
        //определяющий ее состояние и поведение
        // класс - набор свойств и методов будущи

        //На основе класса создаются объекты
        //(экземпляры данного класса) - представители данного клааса, имеющие конкретное состояние и
        //поведение, определенное в классе

        //Тип данных- Author
        //имя переменной - author1
        // = оператор присвоить
        // new Author()- экземпляр класса


        Author author1 = new Author();
        //обращение к свойствам осуществляется через (.)
        author1.name = "Tom";
        author1.surname = "Crowed";

        Author author2 = new Author();
        author2.name = "Mike";
        author2.surname = "Tompson";

        //вызов метода
        author1.printFullName();
        author2.printFullName();
        String fullName = author1.getFullName();
        System.out.println(fullName);

        Book book1 = new Book();
        // не удасться вызвать потмоу что
        // у title модификатор доступа private
        //book1.title = ""
        book1.setTitle("книга");
        System.out.println(book1.getTitle());
        book1.setPageCount(22);

        Shelf shelf = new Shelf();
        shelf.addBook(book1);
        shelf.addBook(book1, book1, book1);



    }
}
