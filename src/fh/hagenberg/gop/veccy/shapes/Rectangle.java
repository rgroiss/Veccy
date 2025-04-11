package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
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
        double[][] coordinates = getCoordinates();
        graphicsContext.fillPolygon(coordinates[0], coordinates[1], coordinates[0].length);
        graphicsContext.strokePolygon(coordinates[0], coordinates[1], coordinates[0].length);
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

    private double[][] getCoordinates() {
        double x1 = getA().getX();
        double y1 = getA().getY();
        double x2 = getB().getX();
        double y2 = getB().getY();

        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        Vector3 p1 = new Vector3(minX, minY, 1); // top-left
        Vector3 p2 = new Vector3(maxX, minY, 1); // top-right
        Vector3 p3 = new Vector3(maxX, maxY, 1); // bottom-right
        Vector3 p4 = new Vector3(minX, maxY, 1); // bottom-left
        Vector3[] positions = new Vector3[]{p1, p2, p3, p4};

        Matrix3 toOrigin = TransformFactory.createTranslation((int)-(getA().getX()+getWidth()/2), (int)-(getA().getY()-getHeight()/2));
        Matrix3 backToFormer = TransformFactory.createTranslation((int)(getA().getX()+getWidth()/2), (int)(getA().getY()-getHeight()/2));

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
