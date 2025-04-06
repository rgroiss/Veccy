package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements DrawableShape {
    protected Vector3 position;
    protected Matrix3 transform;
    protected Color fillColor = Color.WHITE;
    protected Color strokeColor = Color.WHITE;

    public Shape(int x, int y){
        this.position = new Vector3(new double[]{x, y, 1});
    }

    public Shape(Vector3 position){
        this.position = position;
    }

    public Shape(Point position){
        this(position.getX(), position.getY());
    }

    //retourniert Integer laut Angabe...
    public int getX(){
        return (int)this.position.getX();
    }

    public int getY(){
        return (int)this.position.getY();
    }

    public void setX(int x){
        this.position.setX(x);
    }

    public void setY(int y){
        this.position.setY(y);
    }

    public Vector3 getPosition(){
        return this.position;
    }

    public void setPosition(Vector3 position){
        this.position = new Vector3(position);
    }

    public void setPosition(Point position){
        this.position = new Vector3(position);
    }

    public void setPosition(double x, double y, double z){
        this.position = new Vector3(x, y, z);
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public void setStrokeColor(Color strokeColor){
        this.strokeColor = strokeColor;
    }

    public Color getFillColor(){
        return this.fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setTransform(Matrix3 transform){
        this.transform = transform;
    }

    @Override
    public void draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(this.fillColor);
        graphicsContext.setStroke(this.strokeColor);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "position=" + position +
                ", fillColor=" + fillColor +
                ", strokeColor=" + strokeColor +
                '}';
    }
}
