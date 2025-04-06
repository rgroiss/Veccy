package fh.hagenberg.gop.veccy;

import at.fhhgb.mtd.gop.veccy.VeccyGUI;
import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.features.CircleFeature;
import fh.hagenberg.gop.features.LineFeature;
import fh.hagenberg.gop.features.PointFeature;
import fh.hagenberg.gop.features.RectangleFeature;
import fh.hagenberg.gop.veccy.shapes.Circle;
import fh.hagenberg.gop.veccy.shapes.Line;
import fh.hagenberg.gop.veccy.shapes.Point;
import fh.hagenberg.gop.veccy.shapes.Rectangle;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Veccy extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VeccyGUI veccyGUI = new VeccyGUI(stage);
        CanvasModel model = veccyGUI.getModel();

        //drawNestedShapes(model);
        //drawDotsAndLines(model);


        NamedFeature rf = new RectangleFeature(model);
        NamedFeature cf = new CircleFeature(model);
        NamedFeature lf = new LineFeature(model);
        NamedFeature pf = new PointFeature(model);
        model.addFeature(rf);
        model.addFeature(cf);
        model.addFeature(lf);
        model.addFeature(pf);
    }

    private void drawDotsAndLines(CanvasModel model) {
        Random rand = new Random();

        //starting x = 800
        for(int i = 0; i < 100; i++){
            Color color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            int x = rand.nextInt(200) + 800;
            int y = rand.nextInt(700);
            Point cord = new Point(x, y);

            int shapeType = rand.nextInt(2);
            if(shapeType == 0){
                int x2 = rand.nextInt(100) + 800;
                int y2 = rand.nextInt(500);
                Point secondCord = new Point(x2, y2);
                Line line = new Line(cord, secondCord);
                line.setStrokeColor(color);
                model.addShape(line);
            }
            else
            {
                cord.setFillColor(color);
                model.addShape(cord);
            }
        }
    }

    private void drawNestedShapes(CanvasModel model) {
        Random rand = new Random();
        Point center = new Point(400, 350);

        for(int i = 360; i >= 10; i-=20) {
            Color fill = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            Color stroke = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            int shapeType = rand.nextInt(2);
            if(shapeType == 0) {
                Circle c = new Circle(i, center);
                c.setFillColor(fill);
                c.setStrokeColor(stroke);
                model.addShape(c);
            }
            else
            {
                int x = center.getX()-i;
                int y = center.getY()-i;
                Rectangle r = new Rectangle(x, y, i*2, i*2);
                r.setFillColor(fill);
                r.setStrokeColor(stroke);
                model.addShape(r);
            }
        }
    }
}
