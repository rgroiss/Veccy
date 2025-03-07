package fh.hagenberg.gop.veccy;

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

    public Rectangle boundingBox(){
        return new Rectangle(a, b);
    }

    public boolean isOverlapping(Rectangle r) {
        if(r.a.x < a.x && r.a.y < a.y && r.b.x < b.x && r.b.y < b.y) {
            return true;
        }else{
            return false;
        }
    }
}
