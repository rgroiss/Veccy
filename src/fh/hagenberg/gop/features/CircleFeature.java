package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.veccy.shapes.Circle;
import fh.hagenberg.gop.veccy.shapes.Point;
import fh.hagenberg.gop.veccy.shapes.Rectangle;

public class CircleFeature implements NamedFeature {
    private Circle currentCirlce;
    private boolean selected;
    private CanvasModel cv;

    public CircleFeature(CanvasModel cv) {
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public void onSelect() {
        selected = true;
        System.out.println("selected Circle");
    }

    @Override
    public void onDeselect() {
        selected = false;
        System.out.println("deselected Circle");
    }

    @Override
    public void onMouseClick(int x, int y) {
        if(this.selected){
            System.out.println("clicked on Circle " + x + " " + y);
            if(this.currentCirlce != null){
                this.currentCirlce = null;
            }
        }
    }

        @Override
        public void onMouseDrag(int x, int y) {
            if(this.selected){
                System.out.println("dragged Circle " + x + " " + y);
                if(this.currentCirlce == null){
                    // TODO start DRAWING
                    this.currentCirlce = new Circle(0, new Point(x, y));
                    this.currentCirlce.setStrokeColor(cv.getCurrentStrokeColor());
                    this.currentCirlce.setFillColor(cv.getCurrentFillColor());
                    this.cv.addShape(this.currentCirlce);
                }else{
                    // TODO update RECTANGLE
                    Point center = this.currentCirlce.getCenter();
                    double dx = x - center.getX();
                    double dy = y - center.getY();
                    double radius = Math.sqrt(dx*dx + dy*dy);
                    this.currentCirlce.setRadius((int)radius);
                }
            }
        }
    }

