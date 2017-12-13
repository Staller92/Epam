package triangle;

import exceptions.InfinityException;
import exceptions.NegativeSidesException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class TriangleTest {


    Triangle triangle;

    double a;
    double b;
    double c;


    @DataProvider(name = "TriangleDataProvider")
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))},
                {new ArrayList<Double>(Arrays.asList(2.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))},
                {new ArrayList<Double>(Arrays.asList(Double.POSITIVE_INFINITY, 3.0, 10.0))},
                {new ArrayList<Double>(Arrays.asList(30.0, Double.POSITIVE_INFINITY, 5.0))},
                {new ArrayList<Double>(Arrays.asList(20.0, 13.0, Double.POSITIVE_INFINITY))},

                {new ArrayList<Double>(Arrays.asList(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY))},
                {new ArrayList<Double>(Arrays.asList(5.0, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY))},
                {new ArrayList<Double>(Arrays.asList(Double.NEGATIVE_INFINITY, 35.0, 10.0))},
                {new ArrayList<Double>(Arrays.asList(65.0, Double.NEGATIVE_INFINITY, 50.0))},
                {new ArrayList<Double>(Arrays.asList(30.0, 13.0, Double.NEGATIVE_INFINITY))},

                {new ArrayList<Double>(Arrays.asList(Double.NaN, Double.NaN, Double.NaN))},
                {new ArrayList<Double>(Arrays.asList(53.0, Double.NaN, Double.NaN))},
                {new ArrayList<Double>(Arrays.asList(Double.NaN, 27.0, 11.0))},
                {new ArrayList<Double>(Arrays.asList(25.0, Double.NaN, 30.0))},
                {new ArrayList<Double>(Arrays.asList(33.0, 13.0, Double.NaN))},
        };


    }

    @Test
    public void testTriangle() throws Exception {
        Triangle triangle = new Triangle(2,8,9);
        Assert.assertNotNull(triangle);
    }

    @Test(expectedExceptions = InfinityException.class, dataProvider = "TriangleDataProvider")
    public void testGetSquareInfinity(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a,b,c);
        triangle.getSquare();

    }
    @Test(expectedExceptions = InfinityException.class, dataProvider = "TriangleDataProvider")
    public void testDetectTriangleInfinity(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a,b,c);
        triangle.detectTriangle();

    }
    @Test(expectedExceptions = InfinityException.class, dataProvider = "TriangleDataProvider")
    public void testCheckTriangleInfinity(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a,b,c);
        triangle.checkTriangle();

    }
    @AfterMethod
    public void tearDown() throws Exception {
        triangle = null;
    }

}