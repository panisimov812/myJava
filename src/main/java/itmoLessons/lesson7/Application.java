package itmoLessons.lesson7;

import itmoLessons.lesson7.base.BattleUnit;
import itmoLessons.lesson7.base.Unit;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Knight knight1 = new Knight(20, 17);
        knight1.battleKnightVoid();
        knight1.runFromField();

        BattleUnit knight2 = new Knight(20, 17);
        knight2.battleUnitVoid();

        Unit knight3 = new Knight(20, 17);
        knight3.minusHealth(4);
        knight3.rest();


        Infantry infantry1 = new Infantry(18, 15);
        infantry1.runFromField();
        infantry1.rest();
        infantry1.attack(knight1);

        Scanner in = new Scanner(System.in);
        System.out.println("Укажите тип персонажа");
        String type = in.nextLine();

        //полиморфный юнит (тип не известен) полиморфизм наследования
        Unit unit = null;
        if (type.equalsIgnoreCase("knight")) {
            unit = new Knight(21, 56);
        } else if (type.equalsIgnoreCase("infantry")) {
            unit = new Infantry(23, 56);
        } else {
            unit = new King(4);
        }
        unit.rest();


        System.out.println("Укажите тип боевого юнита");
        String bType = in.nextLine();

        //полиморфный юнит (тип не известен) полиморфизм наследования
        BattleUnit battleUnit = null;
        if (bType.equalsIgnoreCase("knight")) {
            battleUnit = new Knight(21, 56);
        } else {
            battleUnit = new Infantry(23,56);
        }
        battleUnit.attack(knight1);
    }
}
