package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Circle;
import fh.hagenberg.gop.veccy.shapes.Line;
import fh.hagenberg.gop.veccy.shapes.Point;

public class LineFeature implements NamedFeature {
    private Line currentLine;
    private boolean selected;
    private CanvasModel cv;

    public LineFeature(CanvasModel cv) {
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Line";
    }

    @Override
    public void onSelect() {
        selected = true;
        System.out.println("selected Line");
    }

    @Override
    public void onDeselect() {
        selected = false;
        System.out.println("deselected Line");
    }

    @Override
    public void onMouseClick(int x, int y) {
        if(this.selected){
            System.out.println("clicked on Line " + x + " " + y);
            if(this.currentLine != null){
                this.currentLine = null;
            }
        }
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if(this.selected){
            System.out.println("dragged Line " + x + " " + y);
            if(this.currentLine == null){
                Vector3 v = new Vector3(x, y);
                this.currentLine = new Line(v,v);
                this.currentLine.setStrokeColor(cv.getCurrentStrokeColor());
                this.currentLine.setFillColor(cv.getCurrentFillColor());//for potential future functions
                this.cv.addShape(this.currentLine);
            }else{
                currentLine.setEnd(new Vector3(x, y));
            }
        }
    }
}
