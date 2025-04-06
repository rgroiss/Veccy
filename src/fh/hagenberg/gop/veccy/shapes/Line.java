package fh.hagenberg.gop.veccy.shapes;
import fh.hagenberg.gop.veccy.shapes.Point;
import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    private Point start;
    private Point end;
    static final int MIN_SIZE = 1;

    public Line(Point start, Point end) {
        super(start);
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

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
