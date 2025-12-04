package itmoLessons.lesson6.books;

import java.util.Arrays;

public class Shelf {
    private String color = "белый";
    private Book[] books = new Book[10];

    //метод добавления одной книги на полку
    public void addBook(Book book) {
        for (int i = 0; i < books.length; i += 1) {
            //если элемент массива null то мы добавляем туда сслыку на книгу
            if (books[i] == null) {
                books[i] = book;
                break; //заканчиваем целеком метод
            }
        }
        System.out.println("Нет места");
    }

    //перегрузка метода (с другими аргуменатми)
    //Метод добавления нескольких книг на полку
    // ссылка на мессив будет присвоена локальной переменной books
    public void addBook(Book... booksValue) {
        System.out.println(Arrays.toString(booksValue));
        for (Book book: booksValue){
            addBook(book);
        }


    }
}
