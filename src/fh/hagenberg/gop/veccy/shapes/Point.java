package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
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

    @Override
    public double[][] getCoordinates() {
        return new double[][]{{getX(), getY()}};
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(
                new Vector3(getX() - 1, getY() - 1),
                new Vector3(getX() + 1, getY() + 1));
    }

    public Vector3 toVector3(){
        return new Vector3(getPosition());
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        graphicsContext.fillRect(super.getX(), super.getY(),3, 3);//set the size to 3 for visibility
    }

    @Override
    public String toString() {
        return "Point{" + getPosition() + "}";
    }
}
