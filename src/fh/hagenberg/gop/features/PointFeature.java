package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.veccy.shapes.Point;
import fh.hagenberg.gop.veccy.shapes.Rectangle;

public class PointFeature implements NamedFeature {
    private Point currentPoint;
    private boolean selected;
    private CanvasModel cv;

    public PointFeature(CanvasModel cv) {
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Point";
    }

    @Override
    public void onSelect() {
        selected = true;
        System.out.println("selected Point");
    }

    @Override
    public void onDeselect() {
        selected = false;
        System.out.println("deselected Point");
    }

    @Override
    public void onMouseClick(int x, int y) {
        if(this.selected){
            System.out.println("clicked Point " + x + " " + y);
            if(this.currentPoint == null){
                // TODO start DRAWING
                this.currentPoint = new Point(x, y);
                this.currentPoint.setFillColor(cv.getCurrentFillColor());
                this.cv.addShape(this.currentPoint);
                this.currentPoint = null;
            }
        }
    }

    @Override
    public void onMouseDrag(int x, int y) {
        //optional: draw lots of dots when dragging
        if(selected){
            System.out.println("dragged Point " + x + " " + y);
            this.currentPoint = null;
            this.currentPoint = new Point(x, y);
            this.currentPoint.setFillColor(cv.getCurrentFillColor());
            this.currentPoint.setStrokeColor(cv.getCurrentFillColor());//for potential future functions
            this.cv.addShape(this.currentPoint);
            this.currentPoint = null;
        }
    }
}
