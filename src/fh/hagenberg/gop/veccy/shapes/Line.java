package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Line implements DrawableShape {
    Point start;
    Point end;
    static final int MIN_SIZE = 1;
    private Color strokeColor = Color.BLACK;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Rectangle getBoundingBox() {
        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        // Ensure at least MIN_SIZE width and height
        if (minX == maxX) maxX += MIN_SIZE; // If vertical, add width
        if (minY == maxY) maxY += MIN_SIZE; // If horizontal, add height

        return new Rectangle(new Point(minX, minY), new Point(maxX, maxY));
    }


    public Color getStrokeColor(){
        return this.strokeColor;
    }

    public void setStrokeColor(Color strokeColor){
        this.strokeColor = strokeColor;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

    }
}
