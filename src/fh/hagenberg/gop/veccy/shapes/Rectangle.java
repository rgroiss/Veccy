package fh.hagenberg.gop.veccy.shapes;

public class Rectangle {
    Point a;
    Point b;


    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public int area() {
        return (b.x - a.x) * (b.y - a.y);
    }

    public int height() {
        return Math.abs(b.y - a.y);
    }
    public int width() {
        return Math.abs(b.x - a.x);
    }

    public Rectangle boundingBox(){
        return new Rectangle(a, b);
    }

    public boolean isOverlapping(Rectangle other) {
        int thisLeft = Math.min(a.x, b.x);
        int thisRight = Math.max(a.x, b.x);
        int thisBottom = Math.max(a.y, b.y);//reverse y, because graphics coordinate system
        int thisTop = Math.min(a.y, b.y);

        int otherLeft = Math.min(other.a.x, other.b.x);
        int otherRight = Math.max(other.a.x, other.b.x);
        int otherBottom = Math.max(other.a.y, other.b.y);
        int otherTop = Math.min(other.a.y, other.b.y);


        if (thisLeft >= otherRight || otherLeft >= thisRight) {
            return false;
        }
        if (thisBottom <= otherTop || otherBottom <= thisTop) {
            return false;
        }

        return true;
    }
}
