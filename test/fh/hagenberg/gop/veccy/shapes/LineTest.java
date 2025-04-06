package fh.hagenberg.gop.veccy.shapes;

import fh.hagenberg.gop.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {

    @Test
    public void testBoundingBox() {
        // Lines with different orientations
        Line horizontal = new Line(new Vector3(2, 5, 1.0), new Vector3(8, 5, 1.0)); // Horizontal
        Line vertical = new Line(new Vector3(4, 1, 1.0), new Vector3(4, 6, 1.0));   // Vertical
        Line diagonal = new Line(new Vector3(1, 1, 1.0), new Vector3(5, 5, 1.0));   // Diagonal
        Line small = new Line(new Vector3(3, 3, 1.0), new Vector3(3, 3, 1.0));      // Single point

        // Get bounding boxes
        Rectangle r1 = horizontal.getBoundingBox();
        Rectangle r2 = vertical.getBoundingBox();
        Rectangle r3 = diagonal.getBoundingBox();
        Rectangle r4 = small.getBoundingBox();

        // Expected bounding boxes
        Rectangle expected1 = new Rectangle(2, 5, 6, 1);  // min height = 1
        Rectangle expected2 = new Rectangle(4, 1, 1, 5);  // min width = 1
        Rectangle expected3 = new Rectangle(1, 1, 4, 4);  // diagonal
        Rectangle expected4 = new Rectangle(3, 3, 1, 1);  // min size

        // Check expected bounding boxes
        assertEquals(expected1.getA().getX(), r1.getA().getX(), 0.01);
        assertEquals(expected2.getA().getY(), r2.getA().getY(), 0.01);
        assertEquals(expected3.getB().getX(), r3.getB().getX(), 0.01);
        assertEquals(expected4.getB().getY(), r4.getB().getY(), 0.01);

        assertNotEquals(expected1.getA().getX(), r2.getA().getX(), 0.01);
        assertNotEquals(expected2.getA().getY(), r1.getA().getY(), 0.01);
        assertNotEquals(expected3.getB().getX(), r4.getB().getX(), 0.01);
        assertNotEquals(expected4.getB().getY(), r3.getB().getY(), 0.01);
    }

    @Test
    public void testOverlap() {
        // Lines
        Line l1 = new Line(new Vector3(0, 0, 1.0), new Vector3(5, 5, 1.0));     // Diagonal
        Line l2 = new Line(new Vector3(3, 3, 1.0), new Vector3(7, 7, 1.0));     // Overlaps with l1
        Line l3 = new Line(new Vector3(10, 10, 1.0), new Vector3(15, 15, 1.0)); // Far away
        Line l4 = new Line(new Vector3(3, 0, 1.0), new Vector3(3, 10, 1.0));    // Vertical
        Line l5 = new Line(new Vector3(2, 5, 1.0), new Vector3(8, 5, 1.0));     // Horizontal (touching?)

        // Bounding boxes
        Rectangle r1 = l1.getBoundingBox();
        Rectangle r2 = l2.getBoundingBox();
        Rectangle r3 = l3.getBoundingBox();
        Rectangle r4 = l4.getBoundingBox();
        Rectangle r5 = l5.getBoundingBox();

        // Overlaps
        assertTrue(r1.isOverlapping(r2)); // Overlapping diagonals
        assertTrue(r1.isOverlapping(r4)); // Vertical crosses diagonal

        assertFalse(r1.isOverlapping(r5)); // Edge case
        assertFalse(r1.isOverlapping(r3)); // Far apart
        assertFalse(r2.isOverlapping(r3)); // Diagonals don't touch
    }
}
