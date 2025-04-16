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
    private boolean selected = false;

    public Shape(int x, int y){
        this.position = new Vector3(x,y);
    }

    public Shape(double x, double y){
        this.position = new Vector3(x,y);
    }

    public Shape(Vector3 position){
        this.position = new Vector3(position);
    }

    public Shape(Point position){
        this(position.getX(), position.getY());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract double[][] getCoordinates();

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

    public Rectangle getBoundingBox() {
        double[][] cords = getCoordinates();

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;

        for (int i = 0; i < cords[0].length; i++) {
            double x = cords[0][i];
            double y = cords[1][i];

            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }

        return new Rectangle(new Vector3(minX, minY, 0), new Vector3(maxX, maxY, 0));
    }


    @Override
    public void draw(GraphicsContext graphicsContext){
        if(isSelected()){
            Rectangle boundingBox = getBoundingBox();
            graphicsContext.setStroke(Color.GREEN);
            graphicsContext.strokeRect(boundingBox.getX(), boundingBox.getY(),
                    boundingBox.getWidth(), boundingBox.getHeight());
        }

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
