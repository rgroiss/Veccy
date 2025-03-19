package fh.hagenberg.gop.math;

public class Matrix3 {
    private double[][] values;

    // Initialisiert values als Einheitsmatrix
    // 1.0 0.0 0.0
    // 0.0 1.0 0.0
    // 0.0 0.0 1.0
    public Matrix3() {
        this.values = new double[][]{
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}
        };
    }

    // Initialisiert this.values mit dem Parameter values (kopieren Sie die Werte!)
    public Matrix3(double[][] values) {

        if(values.length == 3 && values[0].length == 3) {
            this.values = new double[values.length][values[0].length];
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    this.values[i][j] = values[i][j];
                }
            }
        }else{
            throw new IllegalArgumentException("Matrix does not have 3x3 values");
        }

    }

    // Initialisiert this.values mit den values aus dem Parameter matrix
    // (Tipp: Verwenden Sie this()!)
    public Matrix3(Matrix3 matrix) {
        this(matrix.values);
    }

    // Implementieren Sie eine Matrixmultiplikation und geben Sie eine neue Matrix3
    // Instanz mit dem Ergebnis zurück
    public Matrix3 mult(Matrix3 matrix) {
        Matrix3 result = new Matrix3();

            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    result.values[i][j] = 0;
                    for (int k = 0; k < values[i].length; k++) {
                        result.values[i][j] += this.values[i][k] * matrix.values[k][j];
                    }
                }
            }

        return result;
    }

    // Implementieren Sie eine Multiplikation Matrix3 * Vector3 und geben Sie eine
    // neue Vector3 Instanz mit dem Ergebnis zurück
    public Vector3 mult(Vector3 vector) {
        Vector3 v = new Vector3();
            double[] vectorValues = new double[values.length];
            for (int i = 0; i < values[0].length; i++) {
                for (int j = 0; j < values.length; j++) {
                    vectorValues[j] += vector.getValues()[i] * this.values[j][i];
                }
            }
            v.setValues(vectorValues);
        return v;
    }

    // Returniert die Instanzvariable values
    public double[][] getValues() {
        return this.values;
    }

    public void setValues(double[][] values) {
        if(this.values.length == values.length && this.values[0].length == values[0].length) {
            for (int i = 0; i < this.values.length; i++) {
                for (int j = 0; j < this.values[i].length; j++) {
                    this.values[i][j] = values[i][j];
                }
            }
        }

    }
}
