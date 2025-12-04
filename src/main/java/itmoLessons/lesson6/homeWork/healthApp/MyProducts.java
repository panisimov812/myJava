package itmoLessons.lesson6.homeWork.healthApp;

import java.util.Arrays;

public class MyProducts {
    int size = 0;
    private NutritionalValue[] nutritionalarr = new NutritionalValue[4];

    public void addProduct(NutritionalValue nutritionalValue) {
        if (size >= nutritionalarr.length) {
            throw new IllegalArgumentException("Нельзя добавить больше продуктов");
        }
        nutritionalarr[size++] = nutritionalValue;
    }

    @Override
    public String toString() {
        return "MyProducts{" +
                "size=" + size +
                ", nutritionalarr=" + Arrays.toString(nutritionalarr) +
                '}';
    }
}
