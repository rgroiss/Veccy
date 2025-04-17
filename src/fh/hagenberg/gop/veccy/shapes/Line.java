package fh.hagenberg.gop.veccy.shapes;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
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

    public double[][] getCoordinates() {
        Vector3[] points = new Vector3[]{getPosition(), end};

        double sumX = 0;
        double sumY = 0;
        for (Vector3 p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }
        double centerX = sumX / points.length;
        double centerY = sumY / points.length;

        Matrix3 toOrigin = TransformFactory.createTranslation(-centerX, -centerY);
        Matrix3 backToFormer = TransformFactory.createTranslation(centerX, centerY);

        if (transform != null) {
            for (int i = 0; i < points.length; i++) {
                points[i] = backToFormer.mult(transform.mult(toOrigin.mult(points[i])));
            }
        }

        double[][] coords = new double[2][points.length];
        for (int i = 0; i < points.length; i++) {
            coords[0][i] = points[i].getX();
            coords[1][i] = points[i].getY();
        }
        return coords;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        double[][] coordinates = getCoordinates();
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(coordinates[0][0], coordinates[1][0], coordinates[0][1], coordinates[1][1]);
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
