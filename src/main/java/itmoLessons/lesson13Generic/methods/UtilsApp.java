package itmoLessons.lesson13Generic.methods;


public class UtilsApp {
    public static void main(String[] args) {
        String[] strings = {"sdf", "sd", "3f2"};
        String string = "sdf";
        System.out.println(CustomUtils.inArray(strings, string));
        /* public static <T> boolean inArray()*/
        System.out.println(CustomUtils.<String>inArray(strings, string));
        //System.out.println(CustomUtils.<String>inArray(strings, 1));

        //Обертка над премитивом int
        Integer[] integers = {1, 2, 3};
        Integer integer = 11;
        //если мы уверены в типе данных передваемых в метод то можно не писать тип данных
        //а сели не уверены то прописть
        System.out.println(CustomUtils.<Integer>inArray(integers, integer));

        System.out.println(CustomUtils.compareHashCode(12, "12"));
        // с конкретизацией типов
        System.out.println(CustomUtils.<Number, String>compareHashCode(12, "12"));

    }
}
