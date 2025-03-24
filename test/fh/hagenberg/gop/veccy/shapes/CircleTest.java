package fh.hagenberg.gop.veccy.shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {
    @Test
    public void testArea(){
        Point center = new Point(0,0);

        Circle a = new Circle(0, center);   // Expected: 0
        Circle b = new Circle(1, center);   // Expected: π ≈ 3.14159
        Circle c = new Circle(2, center);   // Expected: 4π ≈ 12.56637
        Circle d = new Circle(5, center);   // Expected: 25π ≈ 78.53982
        Circle e = new Circle(10, center);  // Expected: 100π ≈ 314.15926

        assertEquals(0,a.getArea());
        assertEquals(3,b.getArea());
        assertEquals(12,c.getArea());
        assertEquals(78,d.getArea());
        assertEquals(314,e.getArea());

        assertNotEquals(1,a.getArea());
        assertNotEquals(4,b.getArea());
        assertNotEquals(315,e.getArea());
    }

    @Test
    public void testBoundingBox(){
        Point center = new Point(0,0);

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

        Rectangle expected1 = new Rectangle(new Point(0, 0), new Point(0, 0));
        Rectangle expected2 = new Rectangle(new Point(-1, -1), new Point(1, 1));
        Rectangle expected3 = new Rectangle(new Point(-2, -2), new Point(2, 2));
        Rectangle expected4 = new Rectangle(new Point(-5, -5), new Point(5, 5));
        Rectangle expected5 = new Rectangle(new Point(-10, -10), new Point(10, 10));

        assertEquals(expected1.a.getX(), r1.a.getX());
        assertEquals(expected2.a.getY(), r2.a.getY());
        assertEquals(expected3.b.getX(), r3.b.getX());
        assertEquals(expected4.b.getY(), r4.b.getY());
        assertEquals(expected5.a.getX() , r5.a.getX());

        assertNotEquals(expected1.a.getX(), r2.a.getX());
        assertNotEquals(expected2.a.getY(), r1.a.getY());
        assertNotEquals(expected3.b.getX(), r4.b.getX());
        assertNotEquals(expected4.b.getY(), r3.b.getX());
        assertNotEquals(expected5.a.getY() , r1.a.getX());
    }

    @Test
    public void testOverlap(){
        // Define points
        Point origin = new Point(0, 0);
        Point near = new Point(5, 5);
        Point far = new Point(20, 20);
        Point edgeCase = new Point(10, 0); // Right on the boundary

        // Create Circles
        Circle c1 = new Circle(2, origin);  // Small circle at (0,0)
        Circle c2 = new Circle(5, near);    // Larger circle at (5,5)
        Circle c3 = new Circle(3, edgeCase); // Touching edge of c1
        Circle c4 = new Circle(10, far);    // Far away, no overlap
        Circle c5 = new Circle(5, origin);  // Encloses c1

        // Get bounding boxes
        Rectangle r1 = c1.getBoundingBox();
        Rectangle r2 = c2.getBoundingBox();
        Rectangle r3 = c3.getBoundingBox();
        Rectangle r4 = c4.getBoundingBox();
        Rectangle r5 = c5.getBoundingBox();

        // Test Overlaps
        assertTrue(r1.isOverlapping(r2));  // Overlapping (c1 and c2)
        assertTrue(r1.isOverlapping(r5));  // One inside the other (c1 inside c5)
        assertTrue(r2.isOverlapping(r3));  // Edge touching (c2 and c3)
        assertTrue(r5.isOverlapping(r2));  // Larger one overlaps smaller

        assertFalse(r1.isOverlapping(r4)); // Far apart (c1 and c4)
        assertFalse(r3.isOverlapping(r4)); // No overlap (c3 and c4)
    }
}
