package fh.hagenberg.gop.veccy;

import at.fhhgb.mtd.gop.veccy.VeccyGUI;
import at.fhhgb.mtd.gop.veccy.model.CanvasModel;
import at.fhhgb.mtd.gop.veccy.model.NamedFeature;
import fh.hagenberg.gop.features.*;
import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.veccy.shapes.*;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

//VM OPTION: --module-path lib --add-modules javafx.controls
public class Veccy extends Application {

    LinkedList<Shape> shapesList = new LinkedList<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VeccyGUI veccyGUI = new VeccyGUI(stage);
        CanvasModel model = veccyGUI.getModel();

        model.setCurrentlySelectedShapeHandler(index -> {
            shapesList.forEach(s-> s.setSelected(false));
            if(index >= 0 && index < shapesList.size()) {
                shapesList.get(index).setSelected(true);
            }
        });

        model.setShapeCreationHandler(drawableShape -> {
            Shape shape = (Shape) drawableShape;
            shapesList.add(shape);
        });

        model.setShapeDeletionHandler(index -> {
            if(index >= 0 && index < shapesList.size()) {
                Shape shape = shapesList.get(index);
                model.removeShape(shape);
                shapesList.remove(shape);
            }
        });

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


        NamedFeature polf = new PolygonFeature(model);
        model.addFeature(polf);
        NamedFeature pathf = new PathFeature(model);
        model.addFeature(pathf);
        NamedFeature textf = new TextFeature(model);
        model.addFeature(textf);

        NamedFeature translate = new TranslateFeature(model, shapesList);
        model.addFeature(translate);
        NamedFeature rotate = new RotateFeature(model, shapesList);
        model.addFeature(rotate);
        NamedFeature transform = new Transformfeature(model, shapesList);
        model.addFeature(transform);
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

        // Center of canvas
        int canvasCenterX = 800 / 2;
        int canvasCenterY = 700 / 2;
        Point center = new Point(canvasCenterX, canvasCenterY);

        for (int i = 360; i >= 10; i -= 20) {
            Color fill = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            Color stroke = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            // Balanced offset range, large shapes move less, small shapes can go farther
            int maxOffset = Math.max(100, 400 - i); // e.g. large shapes are kept closer to center
            int translateX = rand.nextInt(maxOffset * 2) - maxOffset;
            int translateY = rand.nextInt(maxOffset * 2) - maxOffset;

            double angle = rand.nextDouble() * 360;
            double scaleX = 0.5 + rand.nextDouble(); // 0.5 to 1.5
            double scaleY = 0.5 + rand.nextDouble();

            Matrix3 transform = TransformFactory
                    .createTranslation(translateX, translateY)
                    .mult(TransformFactory.createRotation(Math.toRadians(angle)))
                    .mult(TransformFactory.createScaling(scaleX, scaleY));

            if (rand.nextBoolean()) {
                Circle c = new Circle(i, center);
                c.setFillColor(fill);
                c.setStrokeColor(stroke);
                c.setTransform(transform);
                model.addShape(c);
            } else {
                int x = center.getX() - i;
                int y = center.getY() - i;
                Rectangle r = new Rectangle(x, y, i * 2, i * 2);
                r.setFillColor(fill);
                r.setStrokeColor(stroke);
                r.setTransform(transform);
                model.addShape(r);
            }
        }
    }

}
