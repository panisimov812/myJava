package itmoLessons.lesson13Generic.wildcards;

import itmoLessons.lesson13Generic.types.PairContainer;

public class WildCardsApp {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Cat cat = new Cat();
        Dog dog = new Dog();

        //собираем зверей в контейнеры
        PairContainer<Integer, Animal> container1 = new PairContainer<>(1, animal);
        PairContainer<Integer, Cat> container2 = new PairContainer<>(1, cat);
        PairContainer<Integer, Dog> container3 = new PairContainer<>(1, dog);

        WildCardsApp.<Animal> copyValue(container1, container1);
        WildCardsApp.<Animal> copyValue(container2, container1);
    }

    public static <T> void copyValue(
            PairContainer<Integer, ? extends T> from,
            PairContainer<Integer, ? super T> to
    ) {
        // Предположим, что T - Animal
        // запись ? extends T - тип Animal и все его потомки, т.е. Cat и Dog
        // запись ? super T - тип Animal и все его предки, т.е. Object

        to.setValue(from.getValue());
    }
}
