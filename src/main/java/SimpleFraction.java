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
     * @throws ArithmeticException - исключение выбрасывается, если один из  операндов содержит 0 в знаменателе,
     *                              или произошло переполнение.
     */
    public static SimpleFraction add(SimpleFraction a, SimpleFraction b) throws ArithmeticException {
        checkFractions(a, b, false);
        SimpleFraction result;
        if(a.denominator == b.denominator) {
            result = new SimpleFraction(a.numerator + b.numerator, a.denominator);
            return result;
        } else {
            try {
                int resNumerator = Math.addExact(Math.multiplyExact(a.numerator, b.denominator),
                        Math.multiplyExact(b.numerator, a.denominator));
                result = new SimpleFraction(resNumerator, Math.multiplyExact(a.denominator, b.denominator));
            } catch (ArithmeticException e) {
                throw new ArithmeticException("Числитель или знаменатель слишком велик. Пожалуйста введите другие" +
                        " значения.");
            }
            return result;
        }
    }

    /**
     * Выполняет операцию вычитания двух простых дробей.
     * @param a - уменьшаемое
     * @param b - вычитаемое
     * @return - разность a-b.
     * @throws ArithmeticException - исключение выбрасывается, если один из  операндов содержит 0 в знаменателе,
     *                               или произошло переполнение.
     */
    public static SimpleFraction subtract(SimpleFraction a, SimpleFraction b) throws ArithmeticException {
        checkFractions(a, b, false);
        SimpleFraction result;
        if(a.denominator == b.denominator) {
            result = new SimpleFraction(a.numerator - b.numerator, a.denominator);
            return result;
        } else {
            try {
                int resNumerator = Math.subtractExact(Math.multiplyExact(a.numerator, b.denominator),
                        Math.multiplyExact(b.numerator, a.denominator));
                result = new SimpleFraction(resNumerator, Math.multiplyExact(a.denominator, b.denominator));
            } catch (ArithmeticException e) {
                throw new ArithmeticException("Числитель или знаменатель слишком велик. Пожалуйста введите другие" +
                        " значения.");
            }
            return result;
        }
    }

    /**
     * Выполняет операцию умножени двух простых дробей.
     * @param a - множитель 1
     * @param b - множитель 2
     * @return - результам умножения a*b.
     * @throws ArithmeticException - исключение выбрасывается, если один из  операндов содержит 0 в знаменателе,
     *                               или произошло переполнение.
     */
    public static SimpleFraction multiply(SimpleFraction a, SimpleFraction b) throws ArithmeticException {
        checkFractions(a, b, false);
        SimpleFraction result;
        try {
            result = new SimpleFraction(Math.multiplyExact(a.numerator, b.numerator),
                    Math.multiplyExact(a.denominator, b.denominator));
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Числитель или знаменатель слишком велик. Пожалуйста введите другие" +
                    " значения.");
        }
        return result;
    }

    /**
     * Выполняет операцию деления простодроби a на простую дробь b.
     * @param a - делимое
     * @param b - делитель
     * @return - результат деления a/b.
     * @throws ArithmeticException - исключение выбрасывается, если один из  операндов содержит 0 в знаменателе,
     *                                  или если делитель b сожержит ноль в числителе, или произошло переполнение.
     */
    public static SimpleFraction divide(SimpleFraction a, SimpleFraction b) throws ArithmeticException {
        checkFractions(a, b, true);
        SimpleFraction result;
        try {
            result = new SimpleFraction(Math.multiplyExact(a.numerator, b.denominator),
                    Math.multiplyExact(a.denominator, b.numerator));
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Числитель или знаменатель слишком велик. Пожалуйста введите другие" +
                    " значения.");
        }
        return result;
    }

    /**
     * Проверка дробей на содержание нулей в знаменателях и в числителе при
     * делении.
     * @param a - первый операнд
     * @param b - второй операнд
     * @param divisionOperation - true - если проверка выполняется при операции деления,
     *                          false - если при любой другой операции.
     */
    private static void checkFractions(SimpleFraction a, SimpleFraction b, boolean divisionOperation) {
        if(a.denominator == 0) {
            throw new ArithmeticException("Первая дробь содержит ноль в знаменателе.");
        }
        if(b.denominator == 0) {
            throw new ArithmeticException("Вторая дробь содержит ноль в знаменателе.");
        }
        if(divisionOperation && b.numerator == 0) {
            throw new ArithmeticException("Недопустимая операция. Деление на ноль.");
        }
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
