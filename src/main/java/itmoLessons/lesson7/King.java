package itmoLessons.lesson7;

import itmoLessons.lesson7.base.BattleUnit;
import itmoLessons.lesson7.base.Unit;

//final class не даст наследоваться от класса King
public final class King extends Unit {
    private int gold = 500;
    //массив c типом BattleUnit
    private BattleUnit[] army;

    public King(int healthScore) {
        super(healthScore);
    }

    //реализация абстрактного метода
    @Override
    public void rest() {
        gold -= 50;
        plusHealth(5);
        System.out.println("отдых короля");
    }

    public void generateArmy() {
        if (gold < 250) {
            System.out.println("Стоимость армии 259. У короля " + gold);
            return;
        }
        gold -= 250;
        army = BattleUnit.getBattleUnits(20);
        //создали массив с 20ю null
//        army = new BattleUnit[20];
//        //заполнели весь масив через getBattleUnit
//        for (int i = 0; i < army.length; i++) {
//            army[i] = BattleUnit.getBattleUnit();
//        }
    }

    public void changeUnits() {
        if (gold < 20) {
            System.out.println("Стоимость одного юнита 20. У короля " + gold);
            return;
        }
        for (int i = 0; i < army.length; i++) {
            if (gold < 20) {
                System.out.println("Стоимость одного юнита 20. У короля " + gold);
                return;
            }
            if (army[i] != null && !army[i].isAlive()) {
                gold -= 20;
                army[i] = BattleUnit.getBattleUnit();
            }
        }
    }

    public void startBattle(King enemy){
        
    }


}
