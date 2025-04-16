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
    Shape currentShape;
    boolean selected;
    CanvasModel cv;
    LinkedList<Shape> shapes;

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
            }
        }
    }

    @Override
    public void onDeselect() {
        selected = false;
        currentShape = null;
    }

    @Override
    public void onMouseClick(int x, int y) {
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if(selected && currentShape != null) {
            double dx = x - currentShape.getX();
            double dy = y - currentShape.getY();
            Matrix3 translate = TransformFactory.createTranslation(dx, dy);
            currentShape.setTransform(translate);
        }
    }
}
