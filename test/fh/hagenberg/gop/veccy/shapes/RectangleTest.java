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
