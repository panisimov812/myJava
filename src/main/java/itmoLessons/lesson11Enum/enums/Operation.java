package itmoLessons.lesson11Enum.enums;

//каждый элемент перечисления может иметь собственную реализацию метода
public enum Operation {
    //переопределили абстрактный метод action (20 строка)
    SUM {
        @Override
        public int action(int a, int b) {
            return a + b;
        }
    },
    MULTI {
        @Override
        public int action(int a, int b) {
            return a * b;
        }
    };

    //объявили абстрактный метод без реализации
    public abstract int action(int a, int b);

}
