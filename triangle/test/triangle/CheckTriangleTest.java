package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Arrays;

public class CheckTriangleTest {

    Triangle triangle;
    String expectedResult;
    double a;
    double b;
    double c;

    @DataProvider(name = "TriangleDataProviderPositive")
    public Object[][] dataPositive() {
        return new Object[][]{

                {new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0)), ""},
                {new ArrayList<Double>(Arrays.asList(3.0, 4.0, 5.0)), ""},
                {new ArrayList<Double>(Arrays.asList(2.0, 2.0, 3.0)), ""},
                {new ArrayList<Double>(Arrays.asList(3.0, 2.0, 3.0)), ""},
                {new ArrayList<Double>(Arrays.asList(4.0, 7.0, 7.0)), ""},
                {new ArrayList<Double>(Arrays.asList(4.0, 6.0, 7.0)), ""},
                {new ArrayList<Double>(Arrays.asList(1.0, 1.0, Math.sqrt(2))), ""},
                {new ArrayList<Double>(Arrays.asList(1.0, Math.sqrt(2), 1.0)), ""},
                {new ArrayList<Double>(Arrays.asList(Math.sqrt(2), 1.0, 1.0)), ""},

        };
    }

    @DataProvider(name = "TriangleDataProviderNegative")
    public Object[][] dataNegative() {
        return new Object[][]{

                {new ArrayList<Double>(Arrays.asList(-1.0, 5.0, 10.0)), "a<=0"},
                {new ArrayList<Double>(Arrays.asList(1.0, -5.0, 8.0)), "b<=0"},
                {new ArrayList<Double>(Arrays.asList(2.0, 7.0, -9.0)), "c<=0"},
                {new ArrayList<Double>(Arrays.asList(-9.0, -7.0, -25.0)), "a<=0"},
                {new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0)), "a<=0"},
                {new ArrayList<Double>(Arrays.asList(2.0, 7.0, 9.0)), "a+b<=c"},
                {new ArrayList<Double>(Arrays.asList(1.0, 8.0, 20.0)), "a+b<=c"},
                {new ArrayList<Double>(Arrays.asList(2.0, 8.0, 6.0)), "a+c<=b"},
                {new ArrayList<Double>(Arrays.asList(1.5, 15.0, 6.4)), "a+c<=b"},
                {new ArrayList<Double>(Arrays.asList(10.0, 5.0, 5.0)), "b+c<=a"},
                {new ArrayList<Double>(Arrays.asList(26.0, 15.0, 5.0)), "b+c<=a"},

        };
    }

    @Test(dataProvider = "TriangleDataProviderNegative")
    public void testCheckTriangleNegative(ArrayList<Double> values, String expectedResult) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        this.expectedResult = expectedResult;
        triangle = new Triangle(a, b, c);
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(expectedResult, triangle.getMessage());
    }

    @Test(dataProvider = "TriangleDataProviderPositive")
    public void testCheckTrianglePositive(ArrayList<Double> values, String expectedResult) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        this.expectedResult = expectedResult;
        triangle = new Triangle(a, b, c);
        Assert.assertTrue(triangle.checkTriangle());
        Assert.assertEquals(expectedResult, triangle.getMessage());
    }


    @AfterMethod
    public void tearDown() throws Exception {
        triangle = null;
        this.expectedResult=null;
    }
}

