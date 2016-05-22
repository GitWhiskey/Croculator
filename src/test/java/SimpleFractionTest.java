import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleFractionTest {
    SimpleFraction a;
    SimpleFraction b;
    SimpleFraction c;
    SimpleFraction d;
    SimpleFraction e;
    SimpleFraction f;

    @Before
    public void preparation() {
        a = new SimpleFraction(3,6);
        b = new SimpleFraction(1,4);
        c = new SimpleFraction(1,6);
        d = new SimpleFraction(1,6);
        e = new SimpleFraction(0,35);
        f = new SimpleFraction(456,0);
    }

    @Test
    public void addTest() {
        Assert.assertEquals(new SimpleFraction(18,24), SimpleFraction.add(a,b));
        Assert.assertEquals(new SimpleFraction(2,6), SimpleFraction.add(c,d));
    }

    @Test
    public void subtractTest() {
        Assert.assertEquals(new SimpleFraction(6,24), SimpleFraction.subtract(a,b));
        Assert.assertEquals(new SimpleFraction(-6,24), SimpleFraction.subtract(b,a));
        Assert.assertEquals(new SimpleFraction(0,6), SimpleFraction.subtract(c,d));
    }

    @Test
    public void multiplyTest() {
        Assert.assertEquals(new SimpleFraction(3,24), SimpleFraction.multiply(a,b));
        Assert.assertEquals(new SimpleFraction(1,36), SimpleFraction.multiply(c,d));
    }

    @Test
    public void divideTest() {
        Assert.assertEquals(new SimpleFraction(12,6), SimpleFraction.divide(a,b));
        Assert.assertEquals(new SimpleFraction(6,6), SimpleFraction.divide(c,d));
        Assert.assertEquals(new SimpleFraction(0,35), SimpleFraction.divide(e,b));
    }

    @Test (expected = ArithmeticException.class)
    public void badAddTest1() {
        SimpleFraction.add(f,e);
    }

    @Test (expected = ArithmeticException.class)
    public void badAddTest2() {
        SimpleFraction.add(f,f);
    }

    @Test (expected = ArithmeticException.class)
    public void badSubtractTest() {
        SimpleFraction.subtract(f,e);
    }

    @Test (expected = ArithmeticException.class)
    public void badMultiplyTest() {
        SimpleFraction.multiply(f,e);
    }

    @Test (expected = ArithmeticException.class)
    public void badDivideTest1() {
        SimpleFraction.divide(d,e);
    }

    @Test (expected = ArithmeticException.class)
    public void badDivideTest2() {
        SimpleFraction.divide(d,f);
    }
}
