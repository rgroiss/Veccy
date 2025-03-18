package fh.hagenberg.gop.veccy;

import at.fhhgb.mtd.gop.veccy.VeccyGUI;
import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Veccy extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VeccyGUI veccyGUI = new VeccyGUI(stage);
        CanvasModel model = veccyGUI.getModel();
    }
}
