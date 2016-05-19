package test;

import main.SimpleFraction;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorTest {


    @Test
    public void patternTest() {
        String input = "1/2+1/4";
        Pattern p = Pattern.compile("/|\\+|-|\\*");
        Matcher m = p.matcher(input);

        Assert.assertTrue(m.find());
        System.out.println(m.start());
        System.out.println(m.end());
        Assert.assertTrue(m.find());
        System.out.println(m.start());
        System.out.println(m.end());
    }

    @Test
    public void addTest() {
        SimpleFraction a = new SimpleFraction(3,6);
        SimpleFraction b = new SimpleFraction(1,4);
        Assert.assertEquals(new SimpleFraction(18,24), SimpleFraction.add(a,b));
        SimpleFraction c = new SimpleFraction(1,6);
        SimpleFraction d = new SimpleFraction(1,6);
        Assert.assertEquals(new SimpleFraction(2,6), SimpleFraction.add(c,d));
    }
}