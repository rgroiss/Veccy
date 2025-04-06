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
        //Vector x Matrix
        Vector3 vec = new Vector3(new double[]{5.0, 5.0, 1.0});
        Matrix3 translation = TransformFactory.createTranslation(2, 3);
        Vector3 transformedVec = translation.mult(vec);

        assertArrayEquals(new double[]{7.0, 8.0, 1.0}, transformedVec.getValues());

        //Matrix x Matrix
        Matrix3 base = new Matrix3(new double[][]{
                {1, 0, 5},
                {0, 1, 5},
                {0, 0, 1}
        });

        Matrix3 translation2 = TransformFactory.createTranslation(2, 3);
        Matrix3 transformed = translation2.mult(base);

        Matrix3 expected = new Matrix3(new double[][]{
                {1, 0, 7},
                {0, 1, 8},
                {0, 0, 1}
        });

        assertArrayEquals(expected.getValues(), transformed.getValues());
    }

    @Test
    public void testRotation() {
        //Vector
        Vector3 vec = new Vector3(new double[]{1.0, 0.0, 1.0});
        Matrix3 rotation = TransformFactory.createRotation(Math.PI / 2);// 90° counter clock-wise
        Vector3 transformedVec = rotation.mult(vec);

        //1e-6 Toleranzwert, sonst Rundungsfehler
        assertArrayEquals(new double[]{0.0, 1.0, 1.0}, transformedVec.getValues(), 1e-6);

        //Matrix
        Matrix3 base = new Matrix3(new double[][]{
                {1, 0, 1},
                {0, 1, 0},
                {0, 0, 1}
        });

        Matrix3 rotation2 = TransformFactory.createRotation(Math.PI / 2);
        Matrix3 transformed = rotation2.mult(base);

        Matrix3 expected = new Matrix3(new double[][]{
                {0, -1, 0},
                {1,  0, 1},
                {0,  0, 1}
        });
        assertMatrixEquals(expected.getValues(), transformed.getValues(), 1e-6);
    }

    @Test
    public void testHorizontalMirroring() {
        //Vector
        Vector3 vec = new Vector3(new double[]{3.0, 4.0, 1.0});
        Matrix3 mirror = TransformFactory.createHorizontalMirroring();
        Vector3 transformedVec = mirror.mult(vec);

        assertArrayEquals(new double[]{-3.0, 4.0, 1.0}, transformedVec.getValues());

        //Matrix
        Matrix3 base = new Matrix3(new double[][]{
                {1, 0, 3},
                {0, 1, 4},
                {0, 0, 1}
        });

        Matrix3 mirror2 = TransformFactory.createHorizontalMirroring();
        Matrix3 transformed = mirror2.mult(base);

        Matrix3 expected = new Matrix3(new double[][]{
                {-1, 0, -3},
                { 0, 1,  4},
                { 0, 0,  1}
        });

        assertMatrixEquals(expected.getValues(), transformed.getValues(), 1e-6);
    }

    @Test
    public void testVerticalMirroring() {
        //Vector
        Vector3 vec = new Vector3(new double[]{3.0, 4.0, 1.0});
        Matrix3 mirror = TransformFactory.createVerticalMirroring();
        Vector3 transformedVec = mirror.mult(vec);

        assertArrayEquals(new double[]{3.0, -4.0, 1.0}, transformedVec.getValues());

        //Matrix
        Matrix3 base = new Matrix3(new double[][]{
                {1, 0, 3},
                {0, 1, 4},
                {0, 0, 1}
        });

        Matrix3 mirror2 = TransformFactory.createVerticalMirroring();
        Matrix3 transformed = mirror2.mult(base);

        Matrix3 expected = new Matrix3(new double[][]{
                {1,  0, 3},
                {0, -1, -4},
                {0,  0, 1}
        });

        assertMatrixEquals(expected.getValues(), transformed.getValues(), 1e-6);
    }

    @Test
    public void testScaling() {
        //Vector
        Vector3 vec = new Vector3(new double[]{2.0, 3.0, 1.0});
        Matrix3 scale = TransformFactory.createScaling(2.0, 4.0);
        Vector3 transformedVec = scale.mult(vec);

        assertArrayEquals(new double[]{4.0, 12.0, 1.0}, transformedVec.getValues());

        //Matrix
        Matrix3 base = new Matrix3(new double[][]{
                {1, 0, 2},
                {0, 1, 3},
                {0, 0, 1}
        });

        Matrix3 scale2 = TransformFactory.createScaling(2.0, 4.0);
        Matrix3 transformed = scale2.mult(base);

        Matrix3 expected = new Matrix3(new double[][]{
                {2.0, 0.0, 4.0},
                {0.0, 4.0,12.0},
                {0.0, 0.0, 1.0}
        });

        assertMatrixEquals(expected.getValues(), transformed.getValues(), 1e-6);
    }

    private void assertMatrixEquals(double[][] expected, double[][] actual, double delta) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], delta);
        }
    }
}
