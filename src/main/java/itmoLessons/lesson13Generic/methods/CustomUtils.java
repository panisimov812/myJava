package itmoLessons.lesson13Generic.methods;

public class CustomUtils {
    //типизипрванные методы (generic methods)
    //метод, который проверяет наличие элемента в массиве
    //T- неизвестный тип, при вызове метода будет конкретизироваться при вызове
    //T[] - массив неопределенного типа
    // T element - тип данных элемента
    // T вместо boolean что-то будет возвращать public static <T> T inArray(T[] arr, T element) {
    // Так как T неизвестный тип данных и массив, может быть строкой, числой и тд. и раз он может быть чем угодно
    //значит у T мы можем вызвать только методы Object, потому что они доступны всем объектам
    //ПРИМИТИВЫ И ДЖЕНЕРИКИ НЕ СОВМЕСТИМЫ

    /**
     *
     * Как работает
     * При компиляции T[] из аргумента скомпилируется в тип Object и получится что есть некий статический метод который
     * принимает на вход массив типа Object и элемент типа Object
     * В скомпилированных файлах тип Т превращается в Object
     */
    public static <T> boolean inArray(T[] arr, T element) {
        if (arr == null || element == null) {
            throw new IllegalArgumentException("arr и element не могут быть null");
        }
        // перебираем наш массив
        for (T t : arr) {
            if (element.equals(t)) return true;
        }
        return false;
    }


    /**если метод собирается принять на вход или возвращать разные типы данных то мы используем несколько букв
     При вызове T может быть Number и любым из его родителей
     При вызове K может быть String и любым из его родителей
     */
    public static <T extends Number, K extends String> int compareHashCode(T first, K second) {
        // у first можно вызвать метод Number и его родителей
        // у second можно вызвать метод String и его родителей
        return Integer.compare(first.hashCode(), second.hashCode());
    }
}
