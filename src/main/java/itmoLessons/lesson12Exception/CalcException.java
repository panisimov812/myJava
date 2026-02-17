package itmoLessons.lesson12Exception;

//можно создавать собственные классы с исключениями
//если мы хотим создать исключение времени выполнения, то класс должен наследоваться RuntimeException

//если мы хотим создать исключение времени компиляции, то класс должен наследоваться Exception
public class CalcException extends Exception {
    //можно переопределять конструкторы, методы и свойства
    public CalcException(String message) {
        super(message);
    }

    public CalcException(String message, Throwable cause) {
        super(message, cause);
    }


    //можно будет переопределять методы родителя
    @Override
    public String getMessage() {
        return super.getMessage() + "Будьте внимательны!";
    }
}
