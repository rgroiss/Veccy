package fh.hagenberg.gop.veccy.shapes;

import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Polygon extends Shape{
    private final List<Vector3> vertices = new ArrayList<>();

    public Polygon(List<Vector3> vertices) {
        super(vertices.getFirst());
        this.vertices.addAll(vertices);
    }

    public Polygon(Vector3 start) {
        super(start);
        this.vertices.add(start);
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

    public void addVertex(Vector3 v) {
        vertices.add(v);
    }

    public List<Vector3> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public boolean isComplete(){
        return vertices.size() >= 3;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!isComplete()) return;

        super.draw(gc);

        double[][] coords = getCoordinates();

        double[] xPoints = coords[0];
        double[] yPoints = coords[1];

        gc.fillPolygon(xPoints, yPoints, xPoints.length);
        gc.strokePolygon(xPoints, yPoints, xPoints.length);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon{");
        sb.append("vertices=").append(vertices.size());
        sb.append(", closed=").append(isComplete());
        sb.append(", fillColor=").append(getFillColor());
        sb.append(", strokeColor=").append(getStrokeColor());
        sb.append(", position=").append(getPosition());
        sb.append('}');
        return sb.toString();
    }
}
