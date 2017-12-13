package triangle;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class TriangleTestExp {
    Triangle triangle;

    double a;
    double b;
    double c;

    @DataProvider(name = "DataProvider")
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(3.0, 4.0, 5.0))},
                {new ArrayList<Double>(Arrays.asList(3.0, 3.0, 3.0))},
                {new ArrayList<Double>(Arrays.asList(3.0, 3.0, 1.0))},
                {new ArrayList<Double>(Arrays.asList(1.0, 3.0, 1.0))},
                {new ArrayList<Double>(Arrays.asList(-2.5, -1.5, 5.0))},
                {new ArrayList<Double>(Arrays.asList(-2.0, -4.0, -10.0))},
                {new ArrayList<Double>(Arrays.asList(2.0, 4.0, 100.0))},
                {new ArrayList<Double>(Arrays.asList(10.0, 125.0, 7.0))},
                {new ArrayList<Double>(Arrays.asList(200.0, 40.0, 10.0))},
                {new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0))},
                {new ArrayList<Double>(Arrays.asList(0.0, 7.0, 9.0))},
                {new ArrayList<Double>(Arrays.asList(0.0, 7.0, 9.0))},
                {new ArrayList<Double>(Arrays.asList(10.0, 0.0, 6.0))}};

    }

    @Test(dataProvider = "DataProvider")
    public void testCheck(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a, b, c);

        if (!triangle.checkTriangle()) {

            try {
                triangle.detectTriangle();
                triangle.getSquare();
                Assert.fail();
            } catch (Exception e) {

            }

        }

    }

    @AfterMethod
    public void tearDown() throws Exception {
        triangle = null;
    }
}
