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

    @Override
    public double[][] getCoordinates() {
        double[][] coordinates = new double[vertices.size()][];
        for (int i = 0; i < vertices.size(); i++) {
            coordinates[0][i] = vertices.get(i).getX();
            coordinates[1][i] = vertices.get(i).getY();
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
    public Rectangle getBoundingBox() {
        Vector3 topLeft = new Vector3(Float.MAX_VALUE, Float.MAX_VALUE, 1.0);// lowest Y, lowest X
        Vector3 bottomRight = new Vector3(Float.MIN_VALUE, Float.MIN_VALUE, 1.0);// highest Y, lowest X

        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getX() < topLeft.getX()){
                topLeft.setX(vertices.get(i).getX());
            }
            if(vertices.get(i).getY() < topLeft.getY()){
                topLeft.setY(vertices.get(i).getY());
            }
            if(vertices.get(i).getX() > bottomRight.getX()){
                bottomRight.setX(vertices.get(i).getX());
            }
            if(vertices.get(i).getY() > bottomRight.getY()){
                bottomRight.setY(vertices.get(i).getY());
            }
        }
        return new Rectangle(topLeft, bottomRight);
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
