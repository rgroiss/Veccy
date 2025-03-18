package fh.hagenberg.gop.math;

public class Vector3 {
    private double[] values;

    // Initialisiert values mit { 0.0, 0.0, 1.0 }
    public Vector3() {
        this(new double[]{0.0, 0.0, 1.0});
    }

    // Initialisiert this.values mit dem Parameter values (kopieren Sie die Werte!)
    public Vector3(double[] values) {
        this.values = new double[values.length];
        System.arraycopy(values, 0, this.values, 0, values.length);
    }

    // Initialisiert this.values mit den values aus dem Parameter vector
    // (verwenden Sie this()!)
    public Vector3(Vector3 vector) {
        this(vector.values);
    }

    // Returniert die Instanzvariable values
    public double[] getValues() {
        return this.values;
    }
}
