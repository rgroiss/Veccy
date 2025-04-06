package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Circle;
import fh.hagenberg.gop.veccy.shapes.Point;
import fh.hagenberg.gop.veccy.shapes.Rectangle;

public class CircleFeature implements NamedFeature {
    private Circle currentCircle;
    private boolean selected;
    private CanvasModel cv;
    Vector3 center;

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
            this.currentCircle = null;
        }
    }

        @Override
        public void onMouseDrag(int x, int y) {
            if(this.selected){
                System.out.println("dragged Circle " + x + " " + y);
                if(this.currentCircle == null){
                    this.currentCircle = new Circle(0, new Vector3(x, y));
                    center = this.currentCircle.getCenter();
                    this.currentCircle.setStrokeColor(cv.getCurrentStrokeColor());
                    this.currentCircle.setFillColor(cv.getCurrentFillColor());
                    this.cv.addShape(this.currentCircle);
                }else{
                    double dx = x - center.getX();
                    double dy = y - center.getY();
                    double radius = Math.sqrt(dx*dx + dy*dy);
                    this.currentCircle.setRadius(radius);
                }
            }
        }
    }

