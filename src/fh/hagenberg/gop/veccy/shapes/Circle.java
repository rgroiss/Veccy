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

        Rectangle circle = getBoundingBox();
        double originX = getPosition().getX();
        double originY = getPosition().getY();
        double width = circle.getWidth();
        double height = circle.getHeight();

        graphicsContext.fillOval(originX, originY, width, height);
        graphicsContext.strokeOval(originX, originY, width, height);
    }

    @Override
    public String toString() {
        return "Circle{center=" + getPosition() + ", radius=" + radius + "}";
    }
}
