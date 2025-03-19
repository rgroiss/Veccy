package fh.hagenberg.gop.veccy.math;

import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Matrix3Test {

    @Test
    public void testMult(){
        Matrix3 m3 = new Matrix3(new double[][]{{1,2,3},{4,5,6},{7,8,9}});

        Vector3 v1 = new Vector3(new double[]{1,2,3});

        Matrix3 emptyM = new Matrix3();
        Matrix3 r1 = m3.mult(m3);

        Vector3 emptyV = new Vector3();
        Vector3 r2 = m3.mult(v1);//possible


        assertArrayEquals(new double[][]{{30,36,42},{66,81,96},{102,126,150}}, r1.getValues());
        assertArrayEquals(new double[]{14,32,50}, r2.getValues());

        assertThrows(IllegalArgumentException.class, () -> {Matrix3 m2 = new Matrix3(new double[][]{{0,2,3,6},{4,5,6,7}});});
        assertThrows(IllegalArgumentException.class, () -> {Matrix3 m1 = new Matrix3(new double[][]{{1,2,3},{4,5,6}});});
        assertThrows(IllegalArgumentException.class, () -> {Vector3 v2 = new Vector3(new double[]{4,5,0,7});});
        assertThrows(IllegalArgumentException.class, () -> {Vector3 v3 = new Vector3(new double[]{7,8,9,10,25});});
    }
}
