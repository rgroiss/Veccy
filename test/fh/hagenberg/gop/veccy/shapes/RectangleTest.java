package fh.hagenberg.gop.veccy.shapes;

import fh.hagenberg.gop.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    public void testArea() {
        Rectangle r = new Rectangle(
                new Vector3(0, 0, 1.0),
                new Vector3(42, 12, 1.0)
        );
        assertEquals(504.0, r.area(), 0.001);
        assertNotEquals(505.0, r.area(), 0.001);
    }

    @Test
    public void testBoundingBox() {
        Rectangle rect1 = new Rectangle(
                new Vector3(0, 0, 1.0),
                new Vector3(5, 5, 1.0)
        );
        Rectangle rect2 = new Rectangle(
                new Vector3(2, 3, 1.0),
                new Vector3(8, 6, 1.0)
        );
        Rectangle rect3 = new Rectangle(
                new Vector3(-4, -4, 1.0),
                new Vector3(4, 4, 1.0)
        );

        Rectangle box1 = rect1.getBoundingBox();
        Rectangle box2 = rect2.getBoundingBox();
        Rectangle box3 = rect3.getBoundingBox();

        assertEquals(rect1.getA().getX(), box1.getA().getX(), 0.01);
        assertEquals(rect1.getA().getY(), box1.getA().getY(), 0.01);
        assertEquals(rect1.getB().getX(), box1.getB().getX(), 0.01);
        assertEquals(rect1.getB().getY(), box1.getB().getY(), 0.01);

        assertEquals(rect2.getA().getX(), box2.getA().getX(), 0.01);
        assertEquals(rect2.getA().getY(), box2.getA().getY(), 0.01);
        assertEquals(rect2.getB().getX(), box2.getB().getX(), 0.01);
        assertEquals(rect2.getB().getY(), box2.getB().getY(), 0.01);

        assertEquals(rect3.getA().getX(), box3.getA().getX(), 0.01);
        assertEquals(rect3.getA().getY(), box3.getA().getY(), 0.01);
        assertEquals(rect3.getB().getX(), box3.getB().getX(), 0.01);
        assertEquals(rect3.getB().getY(), box3.getB().getY(), 0.01);
    }

    @Test
    public void testOverlap() {
        Rectangle r1 = new Rectangle(
                new Vector3(0, 0, 1.0),
                new Vector3(4, 4, 1.0)
        );
        Rectangle r2 = new Rectangle(
                new Vector3(2, 2, 1.0),
                new Vector3(6, 6, 1.0)
        );
        Rectangle r3 = new Rectangle(
                new Vector3(5, 5, 1.0),
                new Vector3(7, 7, 1.0)
        );
        Rectangle r4 = new Rectangle(
                new Vector3(-5, -5, 1.0),
                new Vector3(-7, -7, 1.0)
        );

        assertTrue(r1.isOverlapping(r2));
        assertFalse(r1.isOverlapping(r3));
        assertFalse(r1.isOverlapping(r4));
    }
}
