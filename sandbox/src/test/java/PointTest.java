import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void test1Distance() {
        Point p1 = new Point(3,4);
        Point p2 = new Point(7,1);
        Assert.assertEquals(p2.distance(p1), 11);
    }

    @Test
    public void test2Distance() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p2.distance(p1), 0);
    }

    @Test
    public void test3Distance() {
        Point p1 = new Point(-3.5,-4.5);
        Point p2 = new Point(-7.5,-1.5);
        Assert.assertEquals(p2.distance(p1), 5);
    }

    @Test
    public void test4Distance() {
        Point p1 = new Point(-3,-4);
        Point p2 = new Point(3,4);
        Assert.assertEquals(p2.distance(p1), 10);
    }
}
