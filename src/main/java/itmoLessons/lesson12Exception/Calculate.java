package itmoLessons.lesson12Exception;

public class Calculate {
    public static double sum(double a, double b) throws CalcException {
        if (a < 0 || b < 0) {
            throw new CalcException("Числа должны быть положительными");
        }
        return a + b;
    }

    public static double minus(double a, double b) throws CalcException {
        if (a < 0 || b < 0) {
            throw new CalcException("Числа должны быть положительными");
        }
        return a - b;
    }

    public static int random(int max) {
        return (int) (Math.random() * max);
    }

    public static double random(int min, int max) throws CalcException {
        return (int) (min + Math.random() * minus(max, min));
    }


}
