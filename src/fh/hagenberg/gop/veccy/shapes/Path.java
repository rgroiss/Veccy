package fh.hagenberg.gop.veccy.shapes;

import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path extends Shape {
    private final List<Vector3> vertices = new ArrayList<>();

    public Path(Vector3 start) {
        super(start);
        vertices.add(start);
    }

    public double[][] getCoordinates() {
        Vector3[] points = vertices.toArray(new Vector3[0]);

        double sumX = 0;
        double sumY = 0;
        for (Vector3 p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }
        double centerX = sumX / points.length;
        double centerY = sumY / points.length;

        Matrix3 toOrigin = TransformFactory.createTranslation(-centerX, -centerY);
        Matrix3 backToFormer = TransformFactory.createTranslation(centerX, centerY);

        if (transform != null) {
            for (int i = 0; i < points.length; i++) {
                points[i] = backToFormer.mult(transform.mult(toOrigin.mult(points[i])));
            }
        }

        double[][] coordinates = new double[2][points.length];
        for (int i = 0; i < points.length; i++) {
            coordinates[0][i] = points[i].getX();
            coordinates[1][i] = points[i].getY();
        }
        return coordinates;
    }

    public void addPoint(Vector3 point) {
        vertices.add(point);
    }

    public List<Vector3> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public boolean isDrawable(){
        return vertices.size() > 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!isDrawable()) return;

        super.draw(gc);

        double[][] coords = getCoordinates();
        for (int i = 0; i < coords[0].length - 1; i++) {
            double x1 = coords[0][i];
            double y1 = coords[1][i];
            double x2 = coords[0][i + 1];
            double y2 = coords[1][i + 1];
            gc.strokeLine(x1, y1, x2, y2);
        }
    }



    @Override
    public String toString() {
        return "Path{" +
                "points=" + vertices.size() +
                ", strokeColor=" + getStrokeColor() +
                ", position=" + getPosition() +
                '}';
    }
}
