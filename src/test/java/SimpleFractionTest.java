import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleFractionTest {
    private SimpleFraction a;
    private SimpleFraction b;
    private SimpleFraction c;
    private SimpleFraction d;
    private SimpleFraction e;
    private SimpleFraction f;
    private SimpleFraction big1;
    private SimpleFraction big2;

    @Before
    public void preparation() {
        a = new SimpleFraction(3,6);
        b = new SimpleFraction(1,4);
        c = new SimpleFraction(1,6);
        d = new SimpleFraction(1,6);
        e = new SimpleFraction(0,35);
        f = new SimpleFraction(456,0);
        big1 = new SimpleFraction(12342345, 345455674);
        big2 = new SimpleFraction(1223342345, 1121245675);
    }

    @Test
    public void addTest() {
        Assert.assertEquals(new SimpleFraction(18,24), SimpleFraction.add(a,b));
        Assert.assertEquals(new SimpleFraction(18,24), SimpleFraction.add(a,b));
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

    @Test(expected = ArithmeticException.class)
    public void addOverflowTest() {
        SimpleFraction.add(big1,big2);
    }

    @Test (expected = ArithmeticException.class)
    public void addZeroTest1() {
        SimpleFraction.add(f,e);
    }

    @Test (expected = ArithmeticException.class)
    public void addZeroTest2() {
        SimpleFraction.add(f,f);
    }

    @Test(expected = ArithmeticException.class)
    public void subtractOverflowTest() {
        SimpleFraction.add(big1,big2);
    }

    @Test (expected = ArithmeticException.class)
    public void subtractZeroTest() {
        SimpleFraction.subtract(f,e);
    }

    @Test(expected = ArithmeticException.class)
    public void multiplyOverflowTest() {
        SimpleFraction.add(big1,big2);
    }

    @Test (expected = ArithmeticException.class)
    public void multiplyZeroTest() {
        SimpleFraction.multiply(f,e);
    }

    @Test(expected = ArithmeticException.class)
    public void divideOverflowTest() {
        SimpleFraction.add(big1,big2);
    }

    @Test (expected = ArithmeticException.class)
    public void divideZeroTest1() {
        SimpleFraction.divide(d,e);
    }

    @Test (expected = ArithmeticException.class)
    public void divideZeroTest2() {
        SimpleFraction.divide(d,f);
    }
}
