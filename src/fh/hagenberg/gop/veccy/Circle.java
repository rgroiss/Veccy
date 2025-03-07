package fh.hagenberg.gop.veccy;

public class Circle {
    int radius;
    Point center;

    public Circle(int radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(new Point(center.x-radius, center.y-radius),
                new Point(center.x+radius, center.y+radius));
    }
}
