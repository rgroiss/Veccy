package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Rectangle;
import fh.hagenberg.gop.veccy.shapes.Shape;

import java.util.LinkedList;

public class RotateFeature implements NamedFeature {
    Shape currentShape;
    Vector3 center;
    boolean selected;
    CanvasModel cv;
    LinkedList<Shape> shapes;
    boolean hasPreviousTransform = false;
    Matrix3 previousTransform;

    public RotateFeature(CanvasModel cv, LinkedList<Shape> shapes) {
        this.cv = cv;
        this.shapes = shapes;
    }

    @Override
    public String getName() {
        return "Rotate";
    }

    @Override
    public void onSelect() {
        selected = true;
        for(Shape shape : shapes) {
            if(shape.isSelected()) {
                currentShape = shape;
                center = currentShape.getBoundingBox().getTransformedCenter();
                previousTransform = currentShape.getTransform();
                if(previousTransform != null) {
                    hasPreviousTransform = true;
                }
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

    }

    @Override
    public void onMouseDrag(int x, int y) {
        if(selected && currentShape != null) {
            double A = x - center.getX();
            double G = y - center.getY();
            double angle = Math.atan2(G, A);
            Matrix3 rotation = TransformFactory.createRotation(angle);
            if(hasPreviousTransform) {
                currentShape.setTransform(previousTransform.mult(rotation));
            }else{
                currentShape.setTransform(rotation);
            }
        }
    }
}
