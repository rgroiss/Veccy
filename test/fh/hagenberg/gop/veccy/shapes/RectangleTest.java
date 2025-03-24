package fh.hagenberg.gop.veccy.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    public void testArea(){
        Rectangle r = new Rectangle(new Point(0,0), new Point(42,12));
        assertEquals(504, r.area());
        assertNotEquals(505, r.area());
    }

    @Test
    public void testBoundingBox() {
        // Create test rectangles
        Rectangle rect1 = new Rectangle(new Point(0, 0), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(2, 3), new Point(8, 6));
        Rectangle rect3 = new Rectangle(new Point(-4, -4), new Point(4, 4));

        // Get bounding boxes
        Rectangle box1 = rect1.boundingBox();
        Rectangle box2 = rect2.boundingBox();
        Rectangle box3 = rect3.boundingBox();

        // Assert that the bounding box is the same as the original rectangle
        assertEquals(rect1.a.getX(), box1.a.getX());
        assertEquals(rect1.a.getY(), box1.a.getY());
        assertEquals(rect1.b.getX(), box1.b.getX());
        assertEquals(rect1.b.getY(), box1.b.getY());

        assertEquals(rect2.a.getX(), box2.a.getX());
        assertEquals(rect2.a.getY(), box2.a.getY());
        assertEquals(rect2.b.getX(), box2.b.getX());
        assertEquals(rect2.b.getY(), box2.b.getY());

        assertEquals(rect3.a.getX(), box3.a.getX());
        assertEquals(rect3.a.getY(), box3.a.getY());
        assertEquals(rect3.b.getX(), box3.b.getX());
        assertEquals(rect3.b.getY(), box3.b.getY());
    }


    @Test
    public void testOverlap(){
        Rectangle r1 = new Rectangle(new Point(0, 0), new Point(4, 4));
        Rectangle r2 = new Rectangle(new Point(2, 2), new Point(6, 6));
        Rectangle r3 = new Rectangle(new Point(5, 5), new Point(7, 7));
        Rectangle r4 = new Rectangle(new Point(-5, -5), new Point(-7, -7));

        assertTrue(r1.isOverlapping(r2));
        assertFalse(r1.isOverlapping(r3));
        assertFalse(r1.isOverlapping(r4));
    }
}
