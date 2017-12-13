package triangle;

import exceptions.NegativeSidesException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class GetSquareTest {
    Triangle triangle;

    double a;
    double b;
    double c;
    double square;


    @DataProvider(name = "SquareDataProviderNegative")
    public Object[][] dataNegative() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(-3.0, -4.0, -5.0))},
                {new ArrayList<Double>(Arrays.asList(-2.5, -1.5, 5.0))},
                {new ArrayList<Double>(Arrays.asList(-3.0, 4.0, -5.0))},
                {new ArrayList<Double>(Arrays.asList(30.0, -4.0, 499.9))},
                {new ArrayList<Double>(Arrays.asList(-3.0, 0.0, 0.0))},
                {new ArrayList<Double>(Arrays.asList(0.0, 0.0, 6.0))}};
    }

    @DataProvider(name = "SquareDataProviderPositive")
    public Object[][] dataPositive() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(3.0, 4.0, 5.0, 6.0))},
                {new ArrayList<Double>(Arrays.asList(8.0, 5.0, 5.0, 12.0))},
                {new ArrayList<Double>(Arrays.asList(8.0, 5.0, 5.0, 12.0))},
                {new ArrayList<Double>(Arrays.asList(120.0, 109.0, 13.0, 396.0))}};
    }


    @Test(expectedExceptions = NegativeSidesException.class, dataProvider = "SquareDataProviderNegative")
    public void testGetSquareNegative(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a, b, c);
        triangle.getSquare(); // ожидаем исключение

    }


    @Test(dataProvider = "SquareDataProviderPositive")
    public void testGetSquarePositive(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        square = values.get(3);
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(square, triangle.getSquare());

    }

    @AfterMethod
    public void tearDown() throws Exception {
        triangle = null;
    }



}