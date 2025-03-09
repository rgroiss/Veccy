package fh.hagenberg.gop.veccy.shapes;

public class Line {
    Point start;
    Point end;
    static final int MIN_SIZE = 1;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Rectangle getBoundingBox() {
        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        // Ensure at least MIN_SIZE width and height
        if (minX == maxX) maxX += MIN_SIZE; // If vertical, add width
        if (minY == maxY) maxY += MIN_SIZE; // If horizontal, add height

        return new Rectangle(new Point(minX, minY), new Point(maxX, maxY));
    }

}
