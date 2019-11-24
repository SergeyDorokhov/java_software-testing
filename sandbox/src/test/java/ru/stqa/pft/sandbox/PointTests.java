package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(3, 4);
        Assert.assertEquals(point1.distance(point2), 5);
        Assert.assertEquals(CalculateDistance.distance(point1, point2), 5);
        Assert.assertNotEquals(point1.distance(point2), 2);
    }
}
