package itmoLessons.lesson11Enum.enums;

//у элементов перечесления могут быть свойства (задаются в конструкторе)
//могут быть любые методы (включая геттеры и сеттеры)
public enum Priority {
    HIGH(10), MIDDLE(5), LOW(1);
    private int code;

    //экземпляры данного класса должны создаваться с помощью конструктора
    Priority(int code) {
        this.code = code;
    }

    //методы доступен всем элементам перечисления
    //согласно модификаторам доступа
    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
