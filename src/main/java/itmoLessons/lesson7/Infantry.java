package itmoLessons.lesson7;

import itmoLessons.lesson7.base.AttackAble;
import itmoLessons.lesson7.base.BattleUnit;

public class Infantry extends BattleUnit implements AttackAble {
    //final св-во нельзя изменить
    //значение можно задать только при объявлении
    //или через конструктор, но не в сеттерах и тп.
    private final int additionalAttack = 5;

    public Infantry(int healthScore, int attackScore) {
        super(healthScore, attackScore);
    }

    public void battleInfantryVoid() {
        System.out.println("Метод Infantry");
    }

    @Override
    public void rest() {
        plusHealth(1);
        System.out.println("Отдых пехотинца");
    }

    //переопределяем метод
    //при переопределения метода тип может быть только выше ниже чем в родителе быть не может
    //тип, название и аргументы остаются такими же
    //при переопределении можно написать полностью новую реализацию которая перекроет реализацию родительского мтеода
    @Override
    public boolean runFromField() {
        //если нравится реализация родительского метода но и хотите добавить еще кое что
        // super. это обращение к родителю
        if (super.runFromField()) {
            attackScore -= 3;
            return true;
        }
        return false;
    }

    @Override
    public void attack(BattleUnit enemy) {
        System.out.println("начинается атака пехотинца");
        if (this.isAlive() && enemy.isAlive()) minusHealth(1);
        System.out.println("Начинается атака противника");
        if (enemy.isAlive()) this.minusHealth(1);
    }
}
