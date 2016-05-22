/**
 * Класс "простая дробь". Реализует базовые операции над простыми дробями, такие как сложение, вычитание, умножение и
 * деление.
 */
public class SimpleFraction {

    private int numerator; //Числитель
    private int denominator;  //Знаменатель

    public SimpleFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Выполняет сложение двух простых дробей.
     * @param a - слагаемое 1
     * @param b - слагаемое 2
     * @return - результат сложения двух простых дробей a+b.
     */
    public static SimpleFraction add(SimpleFraction a, SimpleFraction b) {
        SimpleFraction result;
        if(a.denominator == b.denominator) {
            result = new SimpleFraction(a.numerator + b.numerator, a.denominator);
            return result;
        } else {
            int resNumerator = a.numerator*b.denominator + b.numerator*a.denominator;
            result = new SimpleFraction(resNumerator, a.denominator*b.denominator);
            return result;
        }
    }

    /**
     * Выполняет операцию вычитания двух простых дробей.
     * @param a - уменьшаемое
     * @param b - вычитаемое
     * @return - разность a-b.
     */
    public static SimpleFraction subtract(SimpleFraction a, SimpleFraction b) {
        SimpleFraction result;
        if(a.denominator == b.denominator) {
            result = new SimpleFraction(a.numerator - b.numerator, a.denominator);
            return result;
        } else {
            int resNumerator = a.numerator*b.denominator - b.numerator*a.denominator;
            result = new SimpleFraction(resNumerator, a.denominator*b.denominator);
            return result;
        }
    }

    /**
     * Выполняет операцию умножени двух простых дробей.
     * @param a - множитель 1
     * @param b - множитель 2
     * @return - результам умножения a*b.
     */
    public static SimpleFraction multiply(SimpleFraction a, SimpleFraction b) {
        return new SimpleFraction(a.numerator*b.numerator, a.denominator*b.denominator);
    }

    /**
     * Выполняет операцию деления простодроби a на простую дробь b.
     * @param a - делимое
     * @param b - делитель
     * @return - результат деления a/b.
     * @throws ArithmeticException - исключение выбрасывается делитель b сожержит ноль в числителе.
     */
    public static SimpleFraction divide(SimpleFraction a, SimpleFraction b) throws ArithmeticException {
        if(b.numerator == 0) {
            throw new ArithmeticException("Недопустимая операция. Деление на ноль.");
        }
        return new SimpleFraction(a.numerator*b.denominator, a.denominator*b.numerator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleFraction that = (SimpleFraction) o;

        if (numerator != that.numerator) return false;
        return denominator == that.denominator;

    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
}
