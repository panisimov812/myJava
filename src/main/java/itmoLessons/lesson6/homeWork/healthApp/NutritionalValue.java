package itmoLessons.lesson6.homeWork.healthApp;

public class NutritionalValue {
    private String productName;
    private int protein;
    private int fat;
    private int carbohydrates;
    private int calories;

    public NutritionalValue() {
    }

    public NutritionalValue(String nameOfProductValue) {
        setNameOfProduct(nameOfProductValue);
    }

    public NutritionalValue(String nameOfProductValue,
                            int countOfProteinValue) {
        this(nameOfProductValue);
        setCountOfProtein(countOfProteinValue);
    }

    public NutritionalValue(String nameOfProductValue,
                            int countOfProteinValue,
                            int countOfFatValue) {
        this(nameOfProductValue, countOfProteinValue);
        setCountOfFat(countOfFatValue);
    }

    public NutritionalValue(String nameOfProductValue,
                            int countOfProteinValue,
                            int countOfFatValue,
                            int countOfCarbohydratesValue,
                            int countOfCaloriesValue
    ) {
        this(nameOfProductValue, countOfProteinValue, countOfFatValue);
        setCountOfCarbohydrates(countOfCarbohydratesValue);
        setCountOfCalories(countOfCaloriesValue);
    }


    public void setNameOfProduct(String nameOfProduct) {
        if (nameOfProduct == null || nameOfProduct.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null " +
                    "или пустым");
        }
        this.productName = nameOfProduct;
    }

    public void setCountOfProtein(int countOfProtein) {
        if (countOfProtein > 1123 || countOfProtein < 1) {
            throw new IllegalArgumentException("Белков должно быть меньше чем  1123");
        }
        this.protein = countOfProtein;
    }

    public void setCountOfFat(int countOfFat) {
        if (countOfFat > 1123 || countOfFat < 1) {
            throw new IllegalArgumentException("Жиров должно быть меньше чем  123");
        }
        this.fat = countOfFat;
    }


    public void setCountOfCarbohydrates(int countOfCarbohydrates) {
        if (countOfCarbohydrates > 1123 || countOfCarbohydrates < 1) {
            throw new IllegalArgumentException("Углеводов должно быть меньше чем  123");
        }
        this.carbohydrates = countOfCarbohydrates;
    }

    public void setCountOfCalories(int countOfCalories) {
        if (countOfCalories > 2000 || countOfCalories <= 0) {
            throw new IllegalArgumentException("Проверь ккал");
        }
        this.calories = countOfCalories;
    }

    @Override
    public String toString() {
        return "NutritionalValue{" +
                "productName='" + productName + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                ", calories=" + calories +
                '}';
    }
}
