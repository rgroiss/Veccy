package fh.hagenberg.gop.features;

import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import at.fhhgb.mtd.gop.veccy.view.BlockingTextInputDialog;
import fh.hagenberg.gop.math.Vector3;
import fh.hagenberg.gop.veccy.shapes.Text;

public class TextFeature implements NamedFeature {
    private boolean selected;
    private CanvasModel cv;
    private Text currentText;
    private Vector3 origin;

    public TextFeature(CanvasModel cv){
        this.cv = cv;
    }

    @Override
    public String getName() {
        return "Text";
    }

    @Override
    public void onSelect() {
        selected = true;
        origin = null;
        currentText = null;
        System.out.println("selected Text");
        currentText = new Text(BlockingTextInputDialog.requestTextInput(), new Vector3());
        currentText.setFillColor(cv.getCurrentFillColor());
        currentText.setStrokeColor(cv.getCurrentStrokeColor());
    }

    @Override
    public void onDeselect() {
        selected = false;
        origin = null;
        currentText = null;
        System.out.println("deselected Text");
    }

    @Override
    public void onMouseClick(int x, int y) {
        if(selected){
            //currentText = null; //Not allowing resizing
            origin = null;
        }
    }

    @Override
    public void onMouseDrag(int x, int y) {
        if (!selected || currentText == null || currentText.getContent() == null || currentText.getContent().isEmpty())
            return;

        // Simulate press/init
        if (origin == null) {
            origin = new Vector3(x, y, 1);
            currentText.setOrigin(origin);
            currentText.setFillColor(cv.getCurrentFillColor());
            currentText.setStrokeColor(cv.getCurrentStrokeColor());
            cv.addShape(currentText);
        }

        // Rectangle-like drag behavior
        double topLeftX = Math.min(origin.getX(), x);
        double topLeftY = Math.min(origin.getY(), y);
        double width = Math.abs(x - origin.getX());
        double height = Math.abs(y - origin.getY());

        currentText.setOrigin(new Vector3(topLeftX, topLeftY, 1));
        currentText.setSize(width, height);
    }

}
