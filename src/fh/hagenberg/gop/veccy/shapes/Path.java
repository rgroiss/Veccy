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

    @Override
    public double[][] getCoordinates() {
        double[][] coordinates = new double[vertices.size()][];
        for (int i = 0; i < vertices.size(); i++) {
            coordinates[0][i] = vertices.get(i).getX();
            coordinates[1][i] = vertices.get(i).getY();
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
        if (!isDrawable()) return;

        super.draw(gc);

        Matrix3 toOrigin = TransformFactory.createTranslation(-getPosition().getX(), -getPosition().getY());
        Matrix3 backToFormer = TransformFactory.createTranslation(getPosition().getX(), getPosition().getY());

        for (int i = 0; i < vertices.size() - 1; i++) {
            Vector3 a = vertices.get(i);
            Vector3 b = vertices.get(i + 1);

            if (transform != null) {
                a = backToFormer.mult(transform.mult(toOrigin.mult(a)));
                b = backToFormer.mult(transform.mult(toOrigin.mult(b)));
            }

            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
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
