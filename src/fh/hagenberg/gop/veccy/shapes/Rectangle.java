package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle implements DrawableShape {
    Point a;
    Point b;
    private Color fillColor = Color.BLACK;
    private Color strokeColor = Color.BLACK;
    private int width;
    private int height;


    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.width = Math.max(a.getX(), b.getX()) - Math.min(a.getX(), b.getX());
        this.height = Math.max(a.getY(), b.getY()) - Math.min(a.getY(), b.getY());
    }

    public Rectangle(int x, int y, int width, int height) {
        this.a = new Point(x, y);
        //this.width = width;
        //this.height = height;
        this.b = new Point(x+width, y+height);
    }

    public int area() {
        return (b.getX() - a.getX()) * (b.getY() - a.getY());
    }

    public int getHeight() {
        return Math.abs(b.getY() - a.getY());
    }
    public int getWidth() {
        return Math.abs(b.getX() - a.getX());
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
        graphicsContext.setFill(this.fillColor);
        graphicsContext.setStroke(this.strokeColor);
        graphicsContext.fillRect(this.a.getX(), this.a.getY(), this.getWidth(), this.getHeight());
        graphicsContext.strokeRect(this.a.getX(), this.a.getY(), this.getWidth(), this.getHeight());
    }
}
