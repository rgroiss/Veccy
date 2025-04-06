package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle extends Shape {
    private Vector3 b;


    public Rectangle(Point a, Point b) {
        super(a);
        this.b = new Vector3(new double[]{b.getX(), b.getY(), 1});
    }

    public Rectangle(Vector3 a, Vector3 b) {
        super(a);
        this.b = new Vector3(b);
    }

    public Rectangle(int x, int y, double width, double height) {
        super(x, y);
        this.b = new Vector3(new double[]{x+width, y+height, 1});
    }

    public double area() {return getWidth() * getHeight();}

    public Vector3 getA() {
        return getPosition();
    }

    public Vector3 getB() {
        return this.b;
    }

    public double getHeight() {
        return Math.abs( (b.getY() - getPosition().getY()));
    }
    public void setHeight(double height) {b.setY(getPosition().getY() + height);}
    public double getWidth() {return Math.abs( (b.getX() - getPosition().getX()));
    }
    public void setWidth(double width) {b.setX(getPosition().getX() + width);}

    public Rectangle boundingBox(){
        return new Rectangle(getPosition(), b);
    }

    public boolean isOverlapping(Rectangle other) {
        double thisLeft = Math.min(getPosition().getX(), b.getX());
        double thisRight = Math.max(getPosition().getX(), b.getX());
        double thisBottom = Math.max(getPosition().getY(), b.getY());//reverse y, because graphics coordinate system
        double thisTop = Math.min(getPosition().getY(), b.getY());

        double otherLeft = Math.min(other.getPosition().getX(), other.b.getX());
        double otherRight = Math.max(other.getPosition().getX(), other.b.getX());
        double otherBottom = Math.max(other.getPosition().getY(), other.b.getY());
        double otherTop = Math.min(other.getPosition().getY(), other.b.getY());


        if (thisLeft >= otherRight || otherLeft >= thisRight) {
            return false;
        }
        if (thisBottom <= otherTop || otherBottom <= thisTop) {
            return false;
        }

        return true;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        double topLeftX = Math.min(getPosition().getX(), b.getX());
        double topLeftY = Math.min(getPosition().getY(), b.getY());
        double width = Math.abs(b.getX() - getPosition().getX());
        double height = Math.abs(b.getY() - getPosition().getY());

        graphicsContext.fillRect(topLeftX, topLeftY, width, height);
        graphicsContext.strokeRect(topLeftX, topLeftY, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "A= x:" + getA().getX() + ", y:" + getA().getY() +
                ", B= x:" + getB().getX() + ", y:" + getB().getY() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", fillColor=" + getFillColor() +
                ", strokeColor=" + getStrokeColor() +
                '}';
    }
}
