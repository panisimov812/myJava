package itmoLessons.lesson6.books;

import java.util.Objects;

public class Book {

    //если private значит что к свойству и методу
    // можно обратиться только из текущего класса
    private String title = "Книга";
    private int pageCount;

    //объект книги будет ссылаться на объект книги который ее написал
    private Author author;//author это ссылка на экземпляо автора

    /**
     * По умолчанию конструктор есть во всех классах
     * Можно определять пустой конструктор
     * Или конструктор который принимает что то на вход
     * Можно два сразу
     * можно сделать сколько нужно конструкторов все в зависимости от реализации
     */
    public Book(){}
    public Book(Author author) {
        //инструкции котрые должны выполняться при вызове конструктора
        //В классе Objects собран набор методов для работы с объектами
        //первое значение ссылка на объект, второй аргумент сообщение об ошибке
        Objects.requireNonNull(author, "author не мб null");
        this.author = author;
    }
    public Book(String title, Author author){
        // вызываем другой конструктор
        // при заполнении аргумента мы заполнили второй конструктор
        //вызываем второй конструктор что бы там вызывать проверку на null
        this(author);
        setTitle(title);

    }

    //методы, позволяющие установаить значения свойств
    //со всеми необходимыми проерками - сеттеры
    //задача сеттера проверить или(и) установить (в) свойств
    //обчно сеттеры со свойством void
    public void setTitle(String titleValue) {
        //при вызове сеттера из аргумента будет созданна локальная переменная titleValue
        //сначало на null      или
        if (titleValue == null || titleValue.length() < 3) {
            //создание экземпляра ошибки
            throw new IllegalArgumentException("Значение title от 3 символов");
        }
        //если все ок, то устанавливаем значение
        title = titleValue;
    }

    //методы, которые возвращают значения свойства - геттеры
    //если мы возвращаем, то определяем тип данных которые он вернет
    public String getTitle() {
        return title;
    }

    public void setPageCount(int pageCount) {
        if (pageCount < 10) {
            throw new IllegalArgumentException("Значение должно быть больше");
        }
        //если имя свойства совпадает с именем локальной переменной
        //то к свойству обращаемся через .this
        //.this - ссылка на текущий объект
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Author getAuthor() {
        return author;
    }
}
