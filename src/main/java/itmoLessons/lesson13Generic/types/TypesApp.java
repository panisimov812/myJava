package itmoLessons.lesson13Generic.types;

public class TypesApp {
    public static void main(String[] args) {
        //для данного объекта у нас строка
        User<String> stringUser = new User<>();
        stringUser.setId("123fsdv24");
        stringUser.setLogin("qwerty");

        String sId = stringUser.getId();
        System.out.println(sId.contains("fr"));

        //Теперь id пользователя будет Integer
        User<Integer> integerUser = new User<>();
        integerUser.setId(1);
        integerUser.setLogin("asd");

        Integer iId = integerUser.getId();
        System.out.println(iId > 0);


        /*public class PairContainer<T, K>*/
        PairContainer<String, Integer> container1 = new PairContainer<>("qwe", 1);
        System.out.println(container1.getKey()); //String
        System.out.println(container1.getValue()); //Integer

        PairContainer<String, User> container2 = new PairContainer<>("x", stringUser);
        Object oId = container2.getKey();

        PairContainer<String, User<String>> container3 = new PairContainer<>("y", stringUser);
        String strId = container3.getValue().getId();
    }

    //PairContainer, ключ - Integer
    //значение PairContainer
    // где ключи Integer, значание User  у которого id Integer

    PairContainer<Integer, PairContainer<Integer, User<Integer>>> container4 = new PairContainer<>(1, new PairContainer<>(2, new User<>()));

}
