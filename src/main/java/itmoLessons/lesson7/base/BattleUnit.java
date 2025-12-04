package itmoLessons.lesson7.base;

import itmoLessons.lesson7.Infantry;
import itmoLessons.lesson7.Knight;

/**
 * класс BattleUnit наследуется от Unit
 * Unit (родительский или супер класс)
 * BattleUnit (дочерний класс)
 * в дочернем классе я могу обратиться к полю из родительского класса с мд protected
 * класс имплементирует интерфейс AttackAble
 */

//abstract - мы не можем создать экземпляр класса BattleUnit
abstract public class BattleUnit extends Unit implements AttackAble {
    protected int attackScore;

    //дочерний конструктор
    public BattleUnit(int healthScore, int attackScore) {
        //для насследования важен вызов родительского конструктора
        super(healthScore);
        if (attackScore < 1) {
            throw new IllegalArgumentException("Атака должна быть положительной");
        }
        //если атака положительна то следуем дальше
        this.attackScore = attackScore;
    }

    public int getAttackScore() {
        return attackScore;
    }

    //переопределять final метод нельзя
    public final void battleUnitVoid() {
        System.out.println("Метод BattleUnit");
    }

    public boolean runFromField() {
        if (!isAlive()) {
            System.out.println("Юнит не может сбежать");
            return false;
        }
        healthScore -= 1;
        if (!isAlive()) {
            System.out.println("Юнит не смог сбежать");
            return false;
        }
        return true;
    }

    //в метод вынесли функционал по созданию экземпляров
    //это фабричный метод
    public static BattleUnit getBattleUnit() {
        String[] type = {"knight", "infantry"};
        BattleUnit unit = null;
        switch (type[(int) (Math.random() * type.length)]) {
            case "knight":
                unit = new Knight(30, 50);
                break;
            case "infantry":
                unit = new Infantry(30, 50);
        }
        return unit;
    }


    public static BattleUnit[] getBattleUnits(int count){
        BattleUnit[] units = new BattleUnit[count];
        for (int i = 0; i < units.length; i++) {
            //в статических методах можно обратиться
            //только к статическим методам или свойствам
            units[i] = getBattleUnit();
        }
        return units;
    }
}
