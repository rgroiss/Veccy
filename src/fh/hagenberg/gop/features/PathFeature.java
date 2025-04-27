package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Line;
import fh.hagenberg.gop.veccy.shapes.Path;

import java.util.ArrayList;
import java.util.List;

public class PathFeature implements NamedFeature {
    private Path currentPath;
    private boolean selected;
    private CanvasModel cv;

    public PathFeature(CanvasModel cv){
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Path";
    }

    @Override
    public void onSelect() {
        selected = true;
        currentPath = null;
        System.out.println("selected Path");
    }

    @Override
    public void onDeselect() {
        selected = false;
        System.out.println("deselected Path");

        currentPath = null;
    }

    //I chose to make it draw onDrag, so it's different from Line
    @Override
    public void onMouseClick(int x, int y) {
        if(!selected) return;
        System.out.println("clicked Path at " + x + ", " + y);
        currentPath = null;
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if(!selected) return;

        Vector3 point = new Vector3(x,y,1);
        System.out.println("dragged Path at " + x + ", " + y);

        if(currentPath == null){
            currentPath = new Path(point);
            currentPath.setStrokeColor(cv.getCurrentStrokeColor());
            currentPath.setFillColor(cv.getCurrentFillColor());
            cv.addShape(currentPath);
        }else{
            currentPath.addPoint(point);
        }
    }
}
