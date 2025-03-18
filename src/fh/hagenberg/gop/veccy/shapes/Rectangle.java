package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle implements DrawableShape {
    Point a;
    Point b;
    private Color fillColor = Color.BLACK;
    private Color strokeColor = Color.BLACK;
    int width;
    int height;


    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.width = Math.max(a.x, b.x) - Math.min(a.x, b.x);
        this.height = Math.max(a.y, b.y) - Math.min(a.y, b.y);
    }

    public Rectangle(int x1, int x2, int y1, int y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
        this.width = Math.max(a.x, b.x) - Math.min(a.x, b.x);
        this.height = Math.max(a.y, b.y) - Math.min(a.y, b.y);

    }

    public int area() {
        return (b.x - a.x) * (b.y - a.y);
    }

    public int height() {
        return Math.abs(b.y - a.y);
    }
    public int width() {
        return Math.abs(b.x - a.x);
    }

    public Rectangle boundingBox(){
        return new Rectangle(a, b);
    }

    public boolean isOverlapping(Rectangle other) {
        int thisLeft = Math.min(a.x, b.x);
        int thisRight = Math.max(a.x, b.x);
        int thisBottom = Math.max(a.y, b.y);//reverse y, because graphics coordinate system
        int thisTop = Math.min(a.y, b.y);

        int otherLeft = Math.min(other.a.x, other.b.x);
        int otherRight = Math.max(other.a.x, other.b.x);
        int otherBottom = Math.max(other.a.y, other.b.y);
        int otherTop = Math.min(other.a.y, other.b.y);


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
        graphicsContext.fillRect(this.a.x, this.a.y, this.b.x, this.b.y);
        graphicsContext.strokeRect(this.a.x, this.a.y, this.b.x, this.b.y);
    }
}
