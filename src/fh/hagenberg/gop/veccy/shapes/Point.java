package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point implements DrawableShape {
    private int x;
    private int y;
    private Color color = Color.BLACK;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.color);
        graphicsContext.setStroke(this.color);
        graphicsContext.fillRect(this.x, this.y,3, 3);//set the size to 3 for visibility
    }
}
