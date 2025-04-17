package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import at.fhhgb.mtd.gop.veccy.model.TransformConfig;
import at.fhhgb.mtd.gop.veccy.view.BlockingTransformInputDialog;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.veccy.shapes.Shape;

import java.util.LinkedList;

public class Transformfeature implements NamedFeature {
    private final CanvasModel cv;
    private final LinkedList<Shape> shapes;

    public Transformfeature(CanvasModel cv, LinkedList<Shape> shapes) {
        this.cv = cv;
        this.shapes = shapes;
    }

    @Override
    public String getName() {
        return "Transform";
    }

    @Override
    public void onSelect() {
        Shape selectedShape = null;
        for (Shape shape : shapes) {
            if (shape.isSelected()) {
                selectedShape = shape;
                break;
            }
        }
        if (selectedShape == null) return;
        TransformConfig config = BlockingTransformInputDialog.requestConfigInput();
        if (config == null) return;

        Matrix3 transform = new Matrix3();

        //Mirroring
        if(config.isMirrorX()) {
            transform = TransformFactory.createHorizontalMirroring().mult(transform);
        }
        if(config.isMirrorY()) {
            transform = TransformFactory.createVerticalMirroring().mult(transform);
        }
        //Rotation
        transform = TransformFactory.createRotation(Math.toRadians(config.getRotation())).mult(transform);

        //Scaling
        transform = TransformFactory.createScaling(config.getScaleX(), config.getScaleY()).mult(transform);

        Matrix3 previousTransform = selectedShape.getTransform();
        if (previousTransform != null) {
            transform = transform.mult(previousTransform);
        }
        selectedShape.setTransform(transform);
    }

    @Override
    public void onDeselect() {
    }

    @Override
    public void onMouseClick(int x, int y) {

    }

    @Override
    public void onMouseDrag(int x, int y) {

    }
}
