package fh.hagenberg.gop.veccy;

import at.fhhgb.mtd.gop.veccy.VeccyGUI;
import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
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

        drawNestedShapes(model);
        drawDotsAndLines(model);

        /* testing out drawing shapes
        Rectangle r1 = new Rectangle(new Point(40,50), new Point(100,150));
        r1.setFillColor(Color.BLUEVIOLET);
        r1.setStrokeColor(Color.AQUAMARINE);

        Rectangle r2 = new Rectangle(new Point(300,200), new Point(500,700));
        r2.setFillColor(Color.GREEN);
        r2.setStrokeColor(Color.RED);

        Circle c1 = new Circle(50, new Point(100,150));
        c1.setFillColor(Color.PINK);
        c1.setStrokeColor(Color.YELLOW);

        Line l1 = new Line(new Point(100,150), new Point(200,400));
        l1.setStrokeColor(Color.LIGHTCORAL);

        Point p1 = new Point(500,350);
        p1.setColor(Color.WHITE);

        model.addShape(r1);
        model.addShape(r2);
        model.addShape(c1);
        model.addShape(l1);
        model.addShape(p1);
        */
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
                cord.setColor(color);
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
