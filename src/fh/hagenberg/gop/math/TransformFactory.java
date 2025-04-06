package fh.hagenberg.gop.math;

public class TransformFactory {
        public static Matrix3 createTranslation(int deltaX, int deltaY) {
            return new Matrix3(new double[][]{
                    {1,0,deltaX},
                    {0,1,deltaY},
                    {0,0,1}
            });
        }
    public static Matrix3 createTranslation(double deltaX, double deltaY) {
        return new Matrix3(new double[][]{
                {1,0,deltaX},
                {0,1,deltaY},
                {0,0,1}
        });
    }
        public static Matrix3 createRotation(double radians) {
            return new Matrix3(new double[][]{
                    {Math.cos(radians),-Math.sin(radians),0},
                    {Math.sin(radians),Math.cos(radians),0},
                    {0,0,1}
            });
        }
        public static Matrix3 createHorizontalMirroring() {
            return new Matrix3(new double[][]{
                    {-1,0,0},
                    {0,1,0},
                    {0,0,1}
            });
        }
        public static Matrix3 createVerticalMirroring() {
            return new Matrix3(new double[][]{
                    {1,0,0},
                    {0,-1,0},
                    {0,0,1}
            });
        }
        public static Matrix3 createScaling(double factorX, double factorY) {
            return new Matrix3(new double[][]{
                    {factorX,0,0},
                    {0,factorY,0},
                    {0,0,1}
            });
        }
    }
