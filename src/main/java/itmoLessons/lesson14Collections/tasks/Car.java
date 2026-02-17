package itmoLessons.lesson14Collections.tasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Car {
    String vin;
    String model;

    public Car(String vin, String model) {
        this.vin = vin;
        this.model = model;
    }

    public static void main(String[] args) {
        HashSet<Car> cars = new HashSet<>();

        cars.add(new Car("123", "BMW"));
        cars.add(new Car("123", "Audi"));

        Car c1 = new Car("123", "BMW");
        Car c2 = new Car("123", "BMW");

        System.out.println(c1.equals(c2)); //false Потому что по умолчанию equals() в Object сравнивает ссылки пока мы его не переопределим
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
}

class HashSetEx {

    public static void main(String[] args) {

    }


}
