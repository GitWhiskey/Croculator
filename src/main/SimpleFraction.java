package main;

public class SimpleFraction {

    private int numerator;
    private int denominator;

    public SimpleFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

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

    public static SimpleFraction multiply(SimpleFraction a, SimpleFraction b) {
        return new SimpleFraction(a.numerator*b.numerator, a.denominator*b.denominator);
    }

    public static SimpleFraction divide(SimpleFraction a, SimpleFraction b) {
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
