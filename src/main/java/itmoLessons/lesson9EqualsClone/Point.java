package itmoLessons.lesson9EqualsClone;

import java.util.Objects;

/**
 * В одном файле должно быть только один public class
 * а не public файлов сколько угодно
 * Все классы в языке неявно наследуются от класса Object
 * в наследство от Object мы получаем методы (сравнение/кланирование/многопоточнсти/приведение объекта к строчке)
 */

//Если мы переопределили метод clone() с обращением к родителю через super
//то нужно имплементировать Cloneable
public class Point implements Cloneable {

    //private не дает менять эти поля (x , y), изменить их значение можно только через методы (сеттеры)
    private int x;
    private int y;

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //переопределяем метод родителя (то есть Object)
    @Override
    public String toString() {
        return "Point{" +
                "значение x=" + x +
                ", значение y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        //ниже приводим к типу данных Point что бы получить доступ к свойствам и методам
        //потому что не переопределенный метод будет обращаться только к метадам Obbject а не к методам
        //класса где его переопределили
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    /**
     * Возвращает просто число
     * Будет использоваться для хранения данных в map
     * <p>
     * Если состояние объекта не меняется то  hashCode() возвращает одно и тоже значение
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * По умолчанию метод protected, поэтому можно поменять модификтаор
     * Меняем ему тип Object на тот тип который нужено что бы он вернул Object
     */
    @Override
    public Point clone() {
        try {
            //что бы метод вернул тип данных Point, нужно его привести к этому типу
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
