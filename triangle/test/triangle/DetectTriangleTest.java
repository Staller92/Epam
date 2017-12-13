package triangle;

import exceptions.NegativeSidesException;
import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class DetectTriangleTest {


    Triangle triangle;

    double a;
    double b;
    double c;

    public int expextedResultRectangularIsosceles = 10; //0b1010 ожидаем треугольник прямоуголный и равнобедренный
    public int expextedResultOrdynary = 4; // 0b0100 ожидаем треугольник обычный
    public int expextedResultRectangular = 8; // 0b1000 ожидаем треугольник прямоуголный
    public int expextedResultIsosceles = 2; // 0b0010 ожидаем треугольник равнобедренный
    public int expextedResultEquilateral = 3; // 0b0011 ожидаем треугольник равносторонний (так же является и равнобедренным)

    @DataProvider(name = "TriangleDataProviderNegative")
    public Object[][] dataNegative() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(-4.0, -3.0, -5.0))},
                {new ArrayList<Double>(Arrays.asList(-2.5, -1.5, 5.0))},
                {new ArrayList<Double>(Arrays.asList(-2.0, 4.0, -10.0))},
                {new ArrayList<Double>(Arrays.asList(30.9, -4.0, 499.9))},
                {new ArrayList<Double>(Arrays.asList(-3.0, 0.0, 0.0))},
                {new ArrayList<Double>(Arrays.asList(0.0, 0.0, 6.0))}};
    }

    @DataProvider(name = "TriangleDataProviderPositive")
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(1.0, 1.0, Math.sqrt(2))), expextedResultRectangularIsosceles},
                {new ArrayList<Double>(Arrays.asList(6.0, Math.sqrt(2) * 6.0, 6.0)), expextedResultRectangularIsosceles},
                {new ArrayList<Double>(Arrays.asList(Math.sqrt(2) * 121.0, 121.0, 121.0)), expextedResultRectangularIsosceles},

                {new ArrayList<Double>(Arrays.asList(3.5, 7.8, 5.0)), expextedResultOrdynary},
                {new ArrayList<Double>(Arrays.asList(6.0, 9.0, 11.0)), expextedResultOrdynary},
                {new ArrayList<Double>(Arrays.asList(30.0, 25.0, 26.0)), expextedResultOrdynary},

                {new ArrayList<Double>(Arrays.asList(3.0, 4.0, 5.0)), expextedResultRectangular},
                {new ArrayList<Double>(Arrays.asList(85.0, 13.0, 84.0)), expextedResultRectangular},
                {new ArrayList<Double>(Arrays.asList(68.0, 293.0, 285.0)), expextedResultRectangular},

                {new ArrayList<Double>(Arrays.asList(3.0, 3.0, 2.0)), expextedResultIsosceles},
                {new ArrayList<Double>(Arrays.asList(8.0, 5.0, 8.0)), expextedResultIsosceles},
                {new ArrayList<Double>(Arrays.asList(1.0, 10.0, 10.0)), expextedResultIsosceles},

                {new ArrayList<Double>(Arrays.asList(3.0, 3.0, 3.0)), expextedResultEquilateral},
                {new ArrayList<Double>(Arrays.asList(8.0, 8.0, 8.0)), expextedResultEquilateral},
                {new ArrayList<Double>(Arrays.asList(10.0, 10.0, 10.0)), expextedResultEquilateral}
        };
    }


    @Test(dataProvider = "TriangleDataProviderPositive")
    public void testDetectTrianglePositive(ArrayList<Double> values, int expextedResult) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a, b, c);

        Assert.assertEquals(expextedResult, triangle.detectTriangle());

    }
    @Test(expectedExceptions = NegativeSidesException.class, dataProvider = "TriangleDataProviderNegative")
    public void testDetectTriangleNegative(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a, b, c);

    }

    @AfterMethod
    public void tearDown() throws Exception {
        triangle = null;
    }
}

