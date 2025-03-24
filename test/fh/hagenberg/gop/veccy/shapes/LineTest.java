package fh.hagenberg.gop.veccy.shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LineTest {
    @Test
    public void testBoundingBox() {
        // Different lines with various orientations
        Line horizontal = new Line(new Point(2, 5), new Point(8, 5)); // Horizontal
        Line vertical = new Line(new Point(4, 1), new Point(4, 6));   // Vertical
        Line diagonal = new Line(new Point(1, 1), new Point(5, 5));   // Diagonal
        Line small = new Line(new Point(3, 3), new Point(3, 3));      // Single point

        // Get bounding boxes
        Rectangle r1 = horizontal.getBoundingBox();
        Rectangle r2 = vertical.getBoundingBox();
        Rectangle r3 = diagonal.getBoundingBox();
        Rectangle r4 = small.getBoundingBox();

        // Expected bounding boxes
        Rectangle expected1 = new Rectangle(new Point(2, 5), new Point(8, 6));  // Ensuring min height
        Rectangle expected2 = new Rectangle(new Point(4, 1), new Point(5, 6));  // Ensuring min width
        Rectangle expected3 = new Rectangle(new Point(1, 1), new Point(5, 5));  // Diagonal should match
        Rectangle expected4 = new Rectangle(new Point(3, 3), new Point(4, 4));  // Ensuring min size

        // Check expected bounding boxes
        assertEquals(expected1.a.getX(), r1.a.getX());
        assertEquals(expected2.a.getY(), r2.a.getY());
        assertEquals(expected3.b.getX(), r3.b.getX());
        assertEquals(expected4.b.getY(), r4.b.getY());

        assertNotEquals(expected1.a.getX(), r2.a.getX());
        assertNotEquals(expected2.a.getY(), r1.a.getY());
        assertNotEquals(expected3.b.getX(), r4.b.getX());
        assertNotEquals(expected4.b.getY(), r3.b.getX());
    }


    @Test
    public void testOverlap() {
        // Define different lines
        Line l1 = new Line(new Point(0, 0), new Point(5, 5)); // Diagonal
        Line l2 = new Line(new Point(3, 3), new Point(7, 7)); // Overlapping diagonal
        Line l3 = new Line(new Point(10, 10), new Point(15, 15)); // Far away (no overlap)
        Line l4 = new Line(new Point(3, 0), new Point(3, 10)); // Vertical line crossing l1
        Line l5 = new Line(new Point(2, 5), new Point(8, 5)); // Horizontal, touches l1's bounding box

        // Get bounding boxes
        Rectangle r1 = l1.getBoundingBox();
        Rectangle r2 = l2.getBoundingBox();
        Rectangle r3 = l3.getBoundingBox();
        Rectangle r4 = l4.getBoundingBox();
        Rectangle r5 = l5.getBoundingBox();

        // Test overlaps
        assertTrue(r1.isOverlapping(r2));  // Overlapping diagonals
        assertTrue(r1.isOverlapping(r4));  // Vertical crossing diagonal

        assertFalse(r1.isOverlapping(r5));  // Edge Case
        assertFalse(r1.isOverlapping(r3)); // Far apart
        assertFalse(r2.isOverlapping(r3)); // Non-overlapping diagonals
    }

}
