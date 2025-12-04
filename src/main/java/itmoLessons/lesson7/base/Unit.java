package itmoLessons.lesson7.base;

//родительский класс для всех персонажей
abstract public class Unit {
    //protected доступ к свойству осуществляется из текущего и дочерних классов
    protected int healthScore;
    private int maxHealthScore;

    //обязательное свойство для создания объекта
    public Unit(int healthScore) {
        if (healthScore < 1) {
            throw new IllegalArgumentException("Значение должно быть положительным");
        }
        // для текущего объекта значение должно быть такое какое переданно в конструктор
        this.healthScore = healthScore;
        maxHealthScore = healthScore;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void plusHealth(int healthScore) {
        if (!isAlive()) return;
        this.healthScore = Math.min(this.healthScore + healthScore, maxHealthScore);
    }

    //void ни чего не возвращает
    public void minusHealth(int healthScore) {
        //если не жив то ни чего не возвращаем
        if (!isAlive()) return;
        this.healthScore -= healthScore;
    }

    public boolean isAlive() {
        return healthScore > 0;
    }

    //абстрактный метод - метод без реализации
    public abstract void rest();
}
