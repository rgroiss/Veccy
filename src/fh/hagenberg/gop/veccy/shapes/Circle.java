package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius, Point position) {
        super(position);
        this.radius = radius;
    }

    public Circle(double radius, double x, double y) {
        super(x, y);
        this.radius = radius;
    }

    public Circle(double radius, Vector3 position) {
        super(position);
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public void setRadius(double radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    public Vector3 getCenter() {
        return getPosition();
    }

    public void setCenter(Point position) {
        setPosition(position);
    }

    public void setCenter(double x, double y, double z) {
        setPosition(x, y, z);
    }

    public void setCenter(Vector3 position) {
        setPosition(position);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        double[][] coordinates = getCoordinates();
        graphicsContext.fillPolygon(coordinates[0], coordinates[1], coordinates[0].length);
        graphicsContext.strokePolygon(coordinates[0], coordinates[1], coordinates[0].length);
    }

    @Override
    public String toString() {
        return "Circle{center=" + getPosition() + ", radius=" + radius + "}";
    }


    public double[][] getCoordinates() {
        Vector3[] positions = new Vector3[256];
        double t = 0;
        double x = getX();
        double y = getY();

        for(int i = 0; i<256; i++){
            positions[i] = new Vector3(
                    (x+radius*Math.cos(t)),
                    (y+radius*Math.sin(t)),
                    1.0);
            t += 2 * Math.PI / 256;
        }

        Matrix3 toOrigin = TransformFactory.createTranslation(-getX(), -getY());
        Matrix3 backToFormer = TransformFactory.createTranslation(getX(), getY());

        if(transform != null){
            for(int i = 0; i<positions.length; i++){
                positions[i] = backToFormer.mult(transform.mult(toOrigin.mult(positions[i])));
            }
        }
        double[][] coordinates = new double[2][positions.length];
        for(int i = 0; i<positions.length; i++){
            coordinates[0][i] = positions[i].getX();
            coordinates[1][i] = positions[i].getY();
        }
        return coordinates;
    }
}
