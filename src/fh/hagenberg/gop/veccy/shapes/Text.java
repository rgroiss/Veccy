package fh.hagenberg.gop.veccy.shapes;

import fh.hagenberg.gop.math.Matrix3;
import fh.hagenberg.gop.math.TransformFactory;
import fh.hagenberg.gop.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Text extends Shape {
    private String content;
    private double width;
    private double height;

    public Text(String content, Vector3 position){
        super(position);
        this.content = content;
        this.width = 1;
        this.height = 1;
    }

    public void setSize(double width, double height){
        this.width = width;
        this.height = height;
    }

    public String getContent(){
        return content;
    }

    public void setOrigin(Vector3 origin){
        setPosition(origin);
    }

    public double[][] getCoordinates() {
        double x = getX();
        double y = getY();

        double w = Math.max(width, content.length() * 6);
        double h = Math.max(height, 12);

        Vector3 p1 = new Vector3(x, y, 1);           // top-left
        Vector3 p2 = new Vector3(x + w, y, 1);       // top-right
        Vector3 p3 = new Vector3(x + w, y + h, 1);   // bottom-right
        Vector3 p4 = new Vector3(x, y + h, 1);       // bottom-left
        Vector3[] corners = new Vector3[]{p1, p2, p3, p4};

        Matrix3 toOrigin = TransformFactory.createTranslation(-x - w / 2, -y - h / 2);
        Matrix3 back = TransformFactory.createTranslation(x + w / 2, y + h / 2);

        if (transform != null) {
            for (int i = 0; i < corners.length; i++) {
                corners[i] = back.mult(transform.mult(toOrigin.mult(corners[i])));
            }
        }

        double[][] coords = new double[2][4];
        for (int i = 0; i < 4; i++) {
            coords[0][i] = corners[i].getX();
            coords[1][i] = corners[i].getY();
        }
        return coords;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (content == null || content.isEmpty()) return;

        int numChars = Math.max(1, content.length());
        double averageCharWidth = width / numChars;
        double fontSize = Math.min(height, averageCharWidth * 2);
        gc.setFont(new Font(fontSize));

        double[][] coords = getCoordinates();
        double drawX = coords[0][0];
        double drawY = coords[1][0];

        gc.fillText(content, drawX, drawY + fontSize);
        gc.strokeText(content, drawX, drawY + fontSize);
    }


    @Override
    public String toString() {
        return "Text{" + content + " at " + getPosition() + " size=[" + width + "x" + height + "]}";
    }
}
