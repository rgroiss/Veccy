package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle implements DrawableShape {
    Point a;
    Point b;
    private Color fillColor = Color.BLACK;
    private Color strokeColor = Color.BLACK;


    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Rectangle(int x, int y, int width, int height) {
        this.a = new Point(x, y);
        this.b = new Point(x+width, y+height);
    }

    public int area() {
        return (b.getX() - a.getX()) * (b.getY() - a.getY());
    }

    public Point getA() {
        return this.a;
    }

    public Point getB() {
        return this.b;
    }

    public int getHeight() {
        return Math.abs(b.getY() - a.getY());
    }
    public void setHeight(int height) {
        this.b.setY(height);
    }
    public int getWidth() {
        return Math.abs(b.getX() - a.getX());
    }
    public void setWidth(int width) {
        this.b.setX(width);
    }

    public Rectangle boundingBox(){
        return new Rectangle(a, b);
    }

    public boolean isOverlapping(Rectangle other) {
        int thisLeft = Math.min(a.getX(), b.getX());
        int thisRight = Math.max(a.getX(), b.getX());
        int thisBottom = Math.max(a.getY(), b.getY());//reverse y, because graphics coordinate system
        int thisTop = Math.min(a.getY(), b.getY());

        int otherLeft = Math.min(other.a.getX(), other.b.getX());
        int otherRight = Math.max(other.a.getX(), other.b.getX());
        int otherBottom = Math.max(other.a.getY(), other.b.getY());
        int otherTop = Math.min(other.a.getY(), other.b.getY());


        if (thisLeft >= otherRight || otherLeft >= thisRight) {
            return false;
        }
        if (thisBottom <= otherTop || otherBottom <= thisTop) {
            return false;
        }

        return true;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor(){
        return this.strokeColor;
    }

    public void setStrokeColor(Color strokeColor){
        this.strokeColor = strokeColor;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        int topLeftX = Math.min(a.getX(), b.getX());
        int topLeftY = Math.min(a.getY(), b.getY());
        int width = Math.abs(b.getX() - a.getX());
        int height = Math.abs(b.getY() - a.getY());

        graphicsContext.setFill(this.fillColor);
        graphicsContext.setStroke(this.strokeColor);
        graphicsContext.fillRect(topLeftX, topLeftY, width, height);
        graphicsContext.strokeRect(topLeftX, topLeftY, width, height);
    }
}
