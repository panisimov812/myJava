package itmoLessons.lesson9EqualsClone;

import java.util.Arrays;
import java.util.Objects;

//final не даст создать другую фигуру на основе этого класса
public final class Figure {
    //из-за final тут, будет всегда ссылаться на массив из конструктора (строка 7)
    private final Point[] points;

    public Figure(int pointCount) {
        if (pointCount < 2) {
            throw new IllegalArgumentException("Фигура должна состоять минимум из 2 точек");
        }
        this.points = new Point[pointCount];
    }

    public Point[] getPoints() {
        return points;
    }

    //метод добавления точки в массив
    //одинаковые точки в массив добавлять нельзя
    public void addPoint(Point point) {
        for (int i = 0; i < points.length; i++) {
            //тут мы прирываем работу метода если точка уже содержится в масиве, нет смысла смотреть дальше
            if (points[i] != null && points[i].equals(point)) {
                System.out.println("Точка " + point + " не была добавлена." +
                        "Можно добавлять только разные точки ");
                return;
            }
            if (points[i] == null) {
                points[i] = point;
                System.out.println("Точка " + point + " была добавлена");
                return;
            }
        }
        System.out.println("Точка " + point + " не была добавлена." +
                "Можно добавлять только разные точки ");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.deepEquals(points, figure.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "points=" + Arrays.toString(points) +
                '}';
    }

    @Override
    //расширяем модификатор на public
    //тип данных возвращаем Figure
    public Figure clone() {
        Figure copy = new Figure(points.length);
        for (int i = 0; i < points.length; i++) {
//записываем копии в массив <-  создаем копию каждой точки
            copy.points[i] = points[i].clone();
        }
        return copy;
    }
}
