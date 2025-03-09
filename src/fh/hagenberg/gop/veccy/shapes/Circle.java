package fh.hagenberg.gop.veccy.shapes;

public class Circle {
    int radius;
    Point center;

    public Circle(int radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public int getArea() {
        return (int) (Math.PI * radius * radius);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(new Point(center.x-radius, center.y-radius),
                new Point(center.x+radius, center.y+radius));
    }
}
