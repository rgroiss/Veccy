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

        double[] xPoints = new double[vertices.size()];
        double[] yPoints = new double[vertices.size()];

        Matrix3 toOrigin = TransformFactory.createTranslation(-getPosition().getX(), -getPosition().getY());
        Matrix3 backToFormer = TransformFactory.createTranslation(getPosition().getX(), getPosition().getY());

        for (int i = 0; i < vertices.size(); i++) {
            Vector3 v = vertices.get(i);

            if (transform != null) {
                v = backToFormer.mult(transform.mult(toOrigin.mult(v)));
            }

            xPoints[i] = v.getX();
            yPoints[i] = v.getY();
        }

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
