package fh.hagenberg.gop.math;

public class Matrix3 {
    private double[][] values;

    // Initialisiert values als Einheitsmatrix
    // 1.0 0.0 0.0
    // 0.0 1.0 0.0
    // 0.0 0.0 1.0
    public Matrix3() { ...}

    // Initialisiert this.values mit dem Parameter values (kopieren Sie die Werte!)
    public Matrix3(double[][] values) { ...}

    // Initialisiert this.values mit den values aus dem Parameter matrix
    // (Tipp: Verwenden Sie this()!)
    public Matrix3(Matrix3 matrix) { ...}

    // Implementieren Sie eine Matrixmultiplikation und geben Sie eine neue Matrix3
    // Instanz mit dem Ergebnis zurück
    public Matrix3 mult(Matrix3 matrix) { ...}

    // Implementieren Sie eine Multiplikation Matrix3 * Vector3 und geben Sie eine
    // neue Vector3 Instanz mit dem Ergebnis zurück
    public Vector3 mult(Vector3 vector) { ...}

    // Returniert die Instanzvariable values
    public double[][] getValues() { ...}
}
