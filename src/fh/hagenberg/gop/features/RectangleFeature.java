package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.veccy.shapes.Point;
import fh.hagenberg.gop.veccy.shapes.Rectangle;

public class RectangleFeature implements NamedFeature {

    private Rectangle currentRectangle;
    private boolean selected;
    private CanvasModel cv;

    public RectangleFeature(CanvasModel cv) {
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public void onSelect() {
        selected = true;
        System.out.println("selected Rectangle");
    }

    @Override
    public void onDeselect() {
        selected = false;
        System.out.println("deselected Rectangle");
    }

    @Override
    public void onMouseClick(int x, int y) {
        if(this.selected){
            System.out.println("clicked on Rectangle " + x + " " + y);
            if(this.currentRectangle != null){
                this.currentRectangle = null;
            }
        }
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if(this.selected){
            System.out.println("dragged Rectangle " + x + " " + y);
            if(this.currentRectangle == null){
                // TODO start DRAWING
                this.currentRectangle = new Rectangle(new Point(x, y), new Point(x, y));
                this.currentRectangle.setStrokeColor(cv.getCurrentStrokeColor());
                this.currentRectangle.setFillColor(cv.getCurrentFillColor());
                this.cv.addShape(this.currentRectangle);
            }else{
                // TODO update RECTANGLE
                this.currentRectangle.getB().setX(x);
                this.currentRectangle.getB().setY(y);
            }
        }
    }
}
