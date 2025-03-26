package fh.hagenberg.gop.veccy.math;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransformFactoryTest {

    @Test
    public void testTranslation() {
        Vector3 vec = new Vector3(new double[]{5.0, 5.0, 1.0});
        Matrix3 translation = TransformFactory.createTranslation(2, 3);
        Vector3 transformedVec = translation.mult(vec);

        assertArrayEquals(new double[]{7.0, 8.0, 1.0}, transformedVec.getValues());
    }

    @Test
    public void testRotation() {
        Vector3 vec = new Vector3(new double[]{1.0, 0.0, 1.0});
        Matrix3 rotation = TransformFactory.createRotation(Math.PI / 2);
        Vector3 transformedVec = rotation.mult(vec);

        //1e-6 Toleranzwert, sonst Rundungsfehler
        assertArrayEquals(new double[]{0.0, 1.0, 1.0}, transformedVec.getValues(), 1e-6);
    }

    @Test
    public void testHorizontalMirroring() {
        Vector3 vec = new Vector3(new double[]{3.0, 4.0, 1.0});
        Matrix3 mirror = TransformFactory.createHorizontalMirroring();
        Vector3 transformedVec = mirror.mult(vec);

        assertArrayEquals(new double[]{-3.0, 4.0, 1.0}, transformedVec.getValues());
    }

    @Test
    public void testVerticalMirroring() {
        Vector3 vec = new Vector3(new double[]{3.0, 4.0, 1.0});
        Matrix3 mirror = TransformFactory.createVerticalMirroring();
        Vector3 transformedVec = mirror.mult(vec);

        assertArrayEquals(new double[]{3.0, -4.0, 1.0}, transformedVec.getValues());
    }

    @Test
    public void testScaling() {
        Vector3 vec = new Vector3(new double[]{2.0, 3.0, 1.0});
        Matrix3 scale = TransformFactory.createScaling(2.0, 4.0);
        Vector3 transformedVec = scale.mult(vec);

        assertArrayEquals(new double[]{4.0, 12.0, 1.0}, transformedVec.getValues());
    }
}
