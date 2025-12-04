package itmoLessons.lesson9EqualsClone;

public class Application {
    public static void main(String[] args) {
        Point a = new Point(23, -12);
        Point b = new Point(23, -12);

        System.out.println(a);
        System.out.println(b);

        //сравнение ссылок
        System.out.println(a == b); //false потому что ссылаются на разные ячейки памяти а это два разных объекта

        //по умолчанию метод equals сравнивает ссылки
        //и должен быть переопределен в классе
        System.out.println(a.equals(b));//true (потому что мы переопределили equals())

        /**
         * клонирование (мтод clone())
         * модификатор у clone() - protected поэтому метод нужно переопределить
         */
        Point aClone = a.clone();
        System.out.println(a == aClone); //а это один объект аClone другой объект (все они находятся в разных ячейках памяти) вернет false
        System.out.println(a.equals(aClone)); //true

        Point c = new Point(12,33);
        Point d = new Point(45,11);
        Point f = new Point(-8,-9);

        Figure figure = new Figure(3);
        figure.addPoint(a); //Точка Point{значение x=23, значение y=-12} была добавлена
        figure.addPoint(b); //Точка Point{значение x=23, значение y=-12} не была добавлена.Можно добавлять только разные точки
        figure.addPoint(d); //Точка Point{значение x=45, значение y=11} была добавлена
        figure.addPoint(c); //Точка Point{значение x=12, значение y=33} была добавлена
        figure.addPoint(f); //Точка Point{значение x=-8, значение y=-9} не была добавлена.Можно добавлять только разные точки

        System.out.println(figure);

        Figure figureClone = figure.clone();
        System.out.println(figure.equals(figureClone));

        for (int i = 0; i < 3; i++) {


        }
    }
}
