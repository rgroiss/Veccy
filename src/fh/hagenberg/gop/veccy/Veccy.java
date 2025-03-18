package fh.hagenberg.gop.veccy;

import at.fhhgb.mtd.gop.veccy.VeccyGUI;
import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import fh.hagenberg.gop.veccy.shapes.Point;
import fh.hagenberg.gop.veccy.shapes.Rectangle;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Veccy extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VeccyGUI veccyGUI = new VeccyGUI(stage);
        CanvasModel model = veccyGUI.getModel();

        Rectangle r1 = new Rectangle(new Point(40,50), new Point(100,150));
        r1.setFillColor(Color.BLUEVIOLET);
        r1.setStrokeColor(Color.AQUAMARINE);

        Rectangle r2 = new Rectangle(new Point(300,200), new Point(500,700));
        r2.setFillColor(Color.GREEN);
        r2.setStrokeColor(Color.RED);

        model.addShape(r1);
        model.addShape(r2);

    }
}
