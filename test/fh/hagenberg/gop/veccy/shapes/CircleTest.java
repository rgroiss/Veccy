package fh.hagenberg.gop.veccy.shapes;

import fh.hagenberg.gop.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    @Test
    public void testArea() {
        Vector3 center = new Vector3(0, 0, 1.0);

        Circle a = new Circle(0, center);   // Expected: 0
        Circle b = new Circle(1, center);   // Expected: π ≈ 3.14159
        Circle c = new Circle(2, center);   // Expected: 4π ≈ 12.56637
        Circle d = new Circle(5, center);   // Expected: 25π ≈ 78.53982
        Circle e = new Circle(10, center);  // Expected: 100π ≈ 314.15926

        assertEquals(0.0, a.getArea(), 0.001);
        assertEquals(Math.PI * 1, b.getArea(), 0.1);
        assertEquals(Math.PI * 4, c.getArea(), 0.1);
        assertEquals(Math.PI * 25, d.getArea(), 0.1);
        assertEquals(Math.PI * 100, e.getArea(), 0.1);

        assertNotEquals(1.0, a.getArea(), 0.001);
        assertNotEquals(4.0, b.getArea(), 0.001);
        assertNotEquals(315.0, e.getArea(), 0.001);
    }

    @Test
    public void testBoundingBox() {
        Vector3 center = new Vector3(0, 0, 1.0);

        Circle a = new Circle(0, center);
        Circle b = new Circle(1, center);
        Circle c = new Circle(2, center);
        Circle d = new Circle(5, center);
        Circle e = new Circle(10, center);

        Rectangle r1 = a.getBoundingBox();
        Rectangle r2 = b.getBoundingBox();
        Rectangle r3 = c.getBoundingBox();
        Rectangle r4 = d.getBoundingBox();
        Rectangle r5 = e.getBoundingBox();

        Rectangle expected1 = new Rectangle(-0, -0, 0, 0);
        Rectangle expected2 = new Rectangle(-1, -1, 2, 2);
        Rectangle expected3 = new Rectangle(-2, -2, 4, 4);
        Rectangle expected4 = new Rectangle(-5, -5, 10, 10);
        Rectangle expected5 = new Rectangle(-10, -10, 20, 20);

        assertEquals(expected1.getA().getX(), r1.getA().getX(), 0.01);
        assertEquals(expected2.getA().getY(), r2.getA().getY(), 0.01);
        assertEquals(expected3.getB().getX(), r3.getB().getX(), 0.01);
        assertEquals(expected4.getB().getY(), r4.getB().getY(), 0.01);
        assertEquals(expected5.getA().getX(), r5.getA().getX(), 0.01);

        assertNotEquals(expected1.getA().getX(), r2.getA().getX(), 0.01);
        assertNotEquals(expected2.getA().getY(), r1.getA().getY(), 0.01);
        assertNotEquals(expected3.getB().getX(), r4.getB().getX(), 0.01);
        assertNotEquals(expected4.getB().getY(), r3.getB().getY(), 0.01);
        assertNotEquals(expected5.getA().getY(), r1.getA().getX(), 0.01);
    }

    @Test
    public void testOverlap() {
        Vector3 origin = new Vector3(0, 0, 1.0);
        Vector3 near = new Vector3(5, 5, 1.0);
        Vector3 far = new Vector3(20, 20, 1.0);
        Vector3 edge = new Vector3(10, 0, 1.0);

        Circle c1 = new Circle(2, origin);     // Small circle at (0,0)
        Circle c2 = new Circle(5, near);       // Larger circle at (5,5)
        Circle c3 = new Circle(3, edge);       // Touching edge of c1
        Circle c4 = new Circle(10, far);       // Far away, no overlap
        Circle c5 = new Circle(5, origin);     // Encloses c1

        Rectangle r1 = c1.getBoundingBox();
        Rectangle r2 = c2.getBoundingBox();
        Rectangle r3 = c3.getBoundingBox();
        Rectangle r4 = c4.getBoundingBox();
        Rectangle r5 = c5.getBoundingBox();

        assertTrue(r1.isOverlapping(r2));
        assertTrue(r1.isOverlapping(r5));
        assertTrue(r2.isOverlapping(r3));
        assertTrue(r5.isOverlapping(r2));

        assertFalse(r1.isOverlapping(r4));
        assertFalse(r3.isOverlapping(r4));
    }
}
