package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius, Point position) {
        super(position);
        this.radius = radius;
    }

    public Circle(double radius, Vector3 position) {
        super(position);
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(new Vector3(getPosition().getX()-radius, getPosition().getY()-radius, 1.0),
                new Vector3(getPosition().getX()+radius, getPosition().getY()+radius, 1.0));
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
        Rectangle bounds = getBoundingBox();

        double x = Math.min(bounds.getA().getX(), bounds.getB().getX());
        double y = Math.min(bounds.getA().getY(), bounds.getB().getY());
        double w = bounds.getWidth();
        double h = bounds.getHeight();

        graphicsContext.fillOval(x, y, w, h);
        graphicsContext.strokeOval(x, y, w, h);
    }

    @Override
    public String toString() {
        return "Circle{center=" + getPosition() + ", radius=" + radius + "}";
    }
}
