package itmoLessons.lesson7;

import itmoLessons.lesson7.base.AttackAble;
import itmoLessons.lesson7.base.BattleUnit;

//final перед классом запрещает наследование
//класс имплементирует интерфейс AttackAble (для нескольких интерфейсов перечислить implements Int1, Int2...
public final class Knight extends BattleUnit  {
    private final int additionalHealth = 10;

    public Knight(int healthScore, int additionalHealth) {
        super(healthScore, additionalHealth);
    }

    public void battleKnightVoid() {
        System.out.println("Метод KnightVoid");
    }

    @Override
    public void rest() {
        plusHealth(3);
        System.out.println("Отдых рыцаря");
    }

    @Override
    public void attack(BattleUnit enemy) {
        if (enemy.isAlive() && this.isAlive()) enemy.minusHealth(attackScore);
        if (enemy.isAlive()) this.minusHealth(enemy.getAttackScore());
        if (this.isAlive()) plusHealth((int) (Math.random() * additionalHealth));
    }
}
