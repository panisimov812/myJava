package leetCode.RomanInt;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Римские цифры представлены семью различными символами  : I, V, X, L, и .CDM
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * Например,  2записывается как II римская цифра,
 * просто две единицы, сложенные вместе.
 * 12 записывается как  XII, то есть просто X + II.
 * Число 27 записывается как XXVII, то есть XX + V + II.
 * <p>
 * Римские цифры обычно пишутся слева направо от большего к меньшему.
 * Однако число «четыре» не равно IIII.
 * Вместо этого число «четыре» записывается как IV.
 * Поскольку единица стоит перед пятёркой, мы вычитаем её, получая четыре.
 * <p>
 * Тот же принцип применим к числу «девять», которое записывается как IX.
 * Вычитание используется в шести случаях:
 * <p>
 * I можно поместить перед V(5) и X(10), чтобы получилось 4 и 9.
 * X можно разместить перед L(50) и C(100), чтобы получить 40 и 90.
 * C можно разместить перед D(500) и M(1000), чтобы получить 400 и 900.
 * Данную римскую цифру преобразуйте в целое число.
 * <p>
 * Пример 1:
 * Ввод: s = "III"
 * Вывод: 3
 * Пояснение: III = 3.
 * Пример 2:
 * Вход: s = "LVIII"
 * Выход: 58
 * Пояснение: L = 50, V = 5, III = 3.
 * Пример 3:
 * Ввод: s = "MCMXCIV"
 * Вывод: 1994
 * Пояснение: M = 1000, CM = 900, XC = 90 и IV = 4.
 */
public class RomanToInt {

    public void romanToInteger(String s) {
        HashMap<String, Integer> romanToIntMap = new HashMap();
        romanToIntMap.put("I", 1);
        romanToIntMap.put("V", 5);
        romanToIntMap.put("X", 10);
        romanToIntMap.put("L", 50);
        romanToIntMap.put("C", 100);
        romanToIntMap.put("D", 500);
        romanToIntMap.put("M", 1000);

    }


    public static void main(String[] args) {
        String example = "LVIII"; //58
        RomanToInt romanToInt = new RomanToInt();
        romanToInt.romanToInteger(example);

    }
}

/*
 int count = 0;
        int counter = 1;
        String[] stringArr = s.split("");
        String[] romanStringInteger = {"I", "V", "X", "L", "C", "D", "M"};
        int[] romanIntInteger = {1, 5, 10, 50, 100, 500, 1000};
        int[] resultArray = new int[7];

        System.out.println(Arrays.toString(stringArr));
        for (int i = 0; i < stringArr.length; i++) {
            for (int j = 0; j < romanStringInteger.length; j++) {
                if (stringArr[i].equals(romanStringInteger[j])) {
                    resultArray[i] = romanIntInteger[j];
                    System.out.println(Arrays.toString(resultArray));
                    break;
                }
            }
        }

        for (int x = 0; x < resultArray.length; x++) {
            if (resultArray[x] >= 1) {
                count += resultArray[x];
            }
        }
        System.out.println(count);
        return count;
 */