package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    //annotation
    @Test
    public void testDistance1(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        assert p1.distance(p2) == 5.0;
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        Assert.assertEquals(p1.distance(p2), 5.0, "Point method is incorrect");
    }

    @Test
    public void testDistance3(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        Assert.assertTrue(p1.distance(p2) == 5.0,  "Point method is incorrect");
    }
}
