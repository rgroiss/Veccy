package fh.hagenberg.gop.veccy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RectangleTest {

    @Test
    public void testArea(){
        Rectangle r = new Rectangle(new Point(0,0), new Point(42,12));
        assertEquals(504, r.area());
        assertNotEquals(505, r.area());
    }
}
