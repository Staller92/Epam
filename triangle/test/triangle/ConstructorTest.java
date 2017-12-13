package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class ConstructorTest {
    Triangle triangle;

    double a;
    double b;
    double c;

    @DataProvider(name = "DataProvider")
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<Double>(Arrays.asList(3.0, 4.0, 5.0))},
                {new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0))},
                {new ArrayList<Double>(Arrays.asList(-26.7, 8.0, -31.5))},
                {new ArrayList<Double>(Arrays.asList(2.0, 4.0, -5.0))}};
    }


    @Test(dataProvider = "DataProvider")
    public void testConstructor(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a, b, c);

        Assert.assertEquals("", triangle.getMessage());
    }

    @Test(dataProvider = "DataProvider")
    public void testTriangle(ArrayList<Double> values) throws Exception {
        a = values.get(0);
        b = values.get(1);
        c = values.get(2);
        triangle = new Triangle(a, b, c);

        Assert.assertNotNull(triangle);
    }


}