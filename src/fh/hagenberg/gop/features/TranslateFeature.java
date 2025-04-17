package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import at.fhhgb.mtd.gop.veccy.shapes.DrawableShape;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Shape;

import java.util.LinkedList;

public class TranslateFeature implements NamedFeature {
    private Shape currentShape;
    private boolean selected;
    private CanvasModel cv;
    private LinkedList<Shape> shapes;
    private boolean hasPreviousTransform = false;
    private Matrix3 previousTransform;
    private Vector3 mouseStart;

    public TranslateFeature(CanvasModel cv, LinkedList<Shape> shapes) {
        this.cv = cv;
        this.shapes = shapes;
    }

    @Override
    public String getName() {
        return "Translate";
    }

    @Override
    public void onSelect() {
        selected = true;
        for(Shape shape : shapes) {
            if(shape.isSelected()) {
                currentShape = shape;
                previousTransform = currentShape.getTransform();
                if(previousTransform != null) {
                    hasPreviousTransform = true;
                }
                break;
            }
        }
    }

    @Override
    public void onDeselect() {
        selected = false;
        currentShape = null;
        previousTransform = null;
        hasPreviousTransform = false;
    }

    @Override
    public void onMouseClick(int x, int y) {
        if(currentShape == null) return;
        mouseStart = null;
        previousTransform = currentShape.getTransform();
        hasPreviousTransform = (previousTransform != null);
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if(selected && currentShape != null) {
            if(mouseStart == null) {
                mouseStart = new Vector3(x, y);
            }
            double dx = x - mouseStart.getX();
            double dy = y - mouseStart.getY();
            Matrix3 translate = TransformFactory.createTranslation(dx, dy);
            if(hasPreviousTransform) {
                currentShape.setTransform(translate.mult(previousTransform));
            }else{
                currentShape.setTransform(translate);
            }
        }
    }
}
