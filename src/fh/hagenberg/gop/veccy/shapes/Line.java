package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Line implements DrawableShape {
    private Point start;
    private Point end;
    static final int MIN_SIZE = 1;
    private Color strokeColor = Color.BLACK;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Rectangle getBoundingBox() {
        int minX = Math.min(start.getX(), end.getX());
        int maxX = Math.max(start.getX(), end.getX());
        int minY = Math.min(start.getY(), end.getY());
        int maxY = Math.max(start.getY(), end.getY());

        // Ensure at least MIN_SIZE width and height
        if (minX == maxX) maxX += MIN_SIZE; // If vertical, add width
        if (minY == maxY) maxY += MIN_SIZE; // If horizontal, add height

        return new Rectangle(new Point(minX, minY), new Point(maxX, maxY));
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Color getStrokeColor(){
        return this.strokeColor;
    }

    public void setStrokeColor(Color strokeColor){
        this.strokeColor = strokeColor;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(this.strokeColor);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
