package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Line;
import fh.hagenberg.gop.veccy.shapes.Polygon;

import java.util.ArrayList;
import java.util.List;

public class PolygonFeature implements NamedFeature {

    private Polygon currentPolygon;
    private boolean selected;
    private CanvasModel cv;

    public PolygonFeature(CanvasModel cv){
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Polygon";
    }

    @Override
    public void onSelect() {
        selected = true;
        currentPolygon = null;
        System.out.println("selected Polygon");
    }

    @Override
    public void onDeselect() {
        selected = false;
        System.out.println("deselected Polygon");

        currentPolygon = null;
    }

    //in order to finish the shape and start a new one, reselect the PolygonFeature button
    @Override
    public void onMouseClick(int x, int y) {
        if (!selected) return;

        Vector3 point = new Vector3(x, y, 1);
        System.out.println("clicked Polygon at " + x + ", " + y);

        if (currentPolygon == null) {
            currentPolygon = new Polygon(point);
            currentPolygon.setFillColor(cv.getCurrentFillColor());
            currentPolygon.setStrokeColor(cv.getCurrentStrokeColor());
            cv.addShape(currentPolygon);
        } else {
            currentPolygon.addVertex(point);
        }
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if (!selected) return;

        Vector3 point = new Vector3(x, y, 1);
        System.out.println("dragged Polygon at " + x + ", " + y);

        if (currentPolygon == null) {
            currentPolygon = new Polygon(point);
            currentPolygon.setFillColor(cv.getCurrentFillColor());
            currentPolygon.setStrokeColor(cv.getCurrentStrokeColor());
            cv.addShape(currentPolygon);
        } else {
            currentPolygon.addVertex(point);
        }
    }
}
