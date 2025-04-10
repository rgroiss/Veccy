package fh.hagenberg.gop.veccy.shapes;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Point;
import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    private Vector3 end;
    static final int MIN_SIZE = 1;

    public Line(Point start, Point end) {
        super(start);
        this.end = new Vector3(end);
    }

    public Line(Vector3 start, Vector3 end) {
        super(start);
        this.end = new Vector3(end);
    }

    public Rectangle getBoundingBox() {
        double minX = Math.min(getPosition().getX(), end.getX());
        double maxX = Math.max(getPosition().getX(), end.getX());
        double minY = Math.min(getPosition().getY(), end.getY());
        double maxY = Math.max(getPosition().getY(), end.getY());

        // Ensure at least MIN_SIZE width and height
        if (minX == maxX) maxX += MIN_SIZE; // If vertical, add width
        if (minY == maxY) maxY += MIN_SIZE; // If horizontal, add height

        return new Rectangle(
                new Vector3(minX, minY, 1.0),
                new Vector3(maxX, maxY, 1.0)
        );
    }

    public Vector3 getEnd() {
        return end;
    }
    public Vector3 getStart() {return getPosition();}

    public void setEnd(Point end) {
        this.end = new Vector3(end);
    }

    public void setEnd(Vector3 end) {
        this.end = new Vector3(end);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(getPosition().getX(), getPosition().getY(), end.getX(), end.getY());
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + getPosition() +
                ", end=" + end +
                ", strokeColor=" + getStrokeColor() +
                '}';
    }
}
