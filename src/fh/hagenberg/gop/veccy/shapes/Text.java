package fh.hagenberg.gop.veccy.shapes;

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

    @Override
    public void draw(GraphicsContext gc){
        super.draw(gc);
        if(content == null || content.isEmpty()) return;

        int numChars = Math.max(1, content.length()); // prevent div by 0
        double averageCharWidth = width / numChars;
        double fontSize = Math.min(height, averageCharWidth * 2);
        gc.setFont(new Font(fontSize));

        gc.fillText(content, position.getX(), position.getY() + fontSize);
        gc.strokeText(content, position.getX(), position.getY() + fontSize);
    }

    @Override
    public String toString() {
        return "Text{" + content + " at " + getPosition() + " size=[" + width + "x" + height + "]}";
    }
}
