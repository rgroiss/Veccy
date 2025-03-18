package fh.hagenberg.gop.veccy.shapes;

import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Point implements DrawableShape {
    int x;
    int y;
    private Color color;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor(){
        return this.color;
    }

    public void setStrokeColor(Color color){
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

    }
}
