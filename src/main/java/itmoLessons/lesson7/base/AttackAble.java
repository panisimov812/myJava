package itmoLessons.lesson7.base;


//экземпляры интерфейса создвать нельзя,
//интерфейсы создаются что бы хранить методы без реализации
public interface AttackAble {
    //в интерфейсе нет конструкторов и свойств


    //модификатор по умолчанию public
    void attack(BattleUnit enemy);

    //может содержать метод с реализацией, но тогда он должен быть определен как default
    default void someVoid() {
        System.out.println("Метод с реализацией");
    }
}
