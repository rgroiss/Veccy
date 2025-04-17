package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point extends Shape {

    public Point(int x, int y) {
        super(x, y);
    }

    public Point(double x, double y) {
        super(x, y);
    }

    public Point(){
        super(0,0);
    }

    public Point(Vector3 point){
        super(point);
    }

    public double[][] getCoordinates() {
        Vector3 p1 = new Vector3(getX(), getY(), 1);
        Vector3 p2 = new Vector3(getX() + 3, getY() + 3, 1); // for visibility

        Matrix3 toOrigin = TransformFactory.createTranslation(-getX(), -getY());
        Matrix3 backToFormer = TransformFactory.createTranslation(getX(), getY());

        if (transform != null) {
            p1 = backToFormer.mult(transform.mult(toOrigin.mult(p1)));
            p2 = backToFormer.mult(transform.mult(toOrigin.mult(p2)));
        }

        return new double[][]{
                {p1.getX(), p2.getX()},
                {p1.getY(), p2.getY()}
        };
    }

    public Vector3 toVector3(){
        return new Vector3(getPosition());
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);

        double[][] coords = getCoordinates();
        double x = coords[0][0];
        double y = coords[1][0];
        double width = coords[0][1] - coords[0][0];
        double height = coords[1][1] - coords[1][0];

        graphicsContext.fillRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Point{" + getPosition() + "}";
    }
}
