package itmoLessons.lesson6.homeWork.healthApp;

public class Application {
    public static void main(String[] args) {
        MyProducts product1 = new MyProducts();
        product1.addProduct(new NutritionalValue("Кефир",
                222,
                32,
                2,
                2));
        product1.addProduct(new NutritionalValue("Халва",
                222,
                32,
                2,
                2));
        product1.addProduct(new NutritionalValue("Йогурт",
                222,
                32,
                2,
                212));
        product1.addProduct(new NutritionalValue("Сало",
                222,
                32,
                2,
                212));
        System.out.println(product1);

    }
}
