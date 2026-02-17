package itmoLessons.lesson12Exception;

public class UncheckedExample {
    public void setAge ( int age){
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
    }
    public static void main(String[] args) {
        // НЕ проверяются компилятором
        // Можно НЕ обрабатывать try-catch (но лучше обрабатывать)

        // 1. NullPointerException
        String s = null;
        System.out.println(s.length()); // NullPointerException

        // 2. ArrayIndexOutOfBoundsException
        int[] arr = new int[3];
        arr[5] = 10; // ArrayIndexOutOfBoundsException

        // 3. ClassCastException
        Object obj = "Hello";
        Integer num = (Integer) obj; // ClassCastException

        // 4. IllegalArgumentException


        //// 5. NumberFormatException
//        int num = Integer.parseInt("abc"); // NumberFormatException

        // 6. ArithmeticException
        int result = 10 / 0; // ArithmeticException

        // 7. IndexOutOfBoundsException (родитель для ArrayIndex... и StringIndex...)
        String str = "hello";
        char c = str.charAt(10); // StringIndexOutOfBoundsException
    }
}
