package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements DrawableShape {
    int radius;
    Point center;
    private Color fillColor = Color.BLACK;
    private Color strokeColor = Color.WHITE;

    public Circle(int radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public int getArea() {
        return (int) (Math.PI * radius * radius);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(new Point(center.getX()-radius, center.getY()-radius),
                new Point(center.getX()+radius, center.getY()+radius));
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
        Rectangle circle = getBoundingBox();
        graphicsContext.setFill(this.fillColor);
        graphicsContext.setStroke(this.strokeColor);
        graphicsContext.fillOval(circle.a.getX(), circle.a.getY(), circle.getWidth(), circle.getHeight());
        graphicsContext.strokeOval(circle.a.getX(), circle.a.getY(), circle.getWidth(), circle.getHeight());
    }
}
