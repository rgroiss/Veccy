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
        assertEquals(rect1.a.x, box1.a.x);
        assertEquals(rect1.a.y, box1.a.y);
        assertEquals(rect1.b.x, box1.b.x);
        assertEquals(rect1.b.y, box1.b.y);

        assertEquals(rect2.a.x, box2.a.x);
        assertEquals(rect2.a.y, box2.a.y);
        assertEquals(rect2.b.x, box2.b.x);
        assertEquals(rect2.b.y, box2.b.y);

        assertEquals(rect3.a.x, box3.a.x);
        assertEquals(rect3.a.y, box3.a.y);
        assertEquals(rect3.b.x, box3.b.x);
        assertEquals(rect3.b.y, box3.b.y);
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
