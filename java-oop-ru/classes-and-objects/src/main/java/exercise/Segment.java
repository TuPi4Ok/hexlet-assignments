package exercise;

// BEGIN
public class Segment {
    Point beginPoint;
    Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        return new Point(this.getBeginPoint().getX() + (this.getEndPoint().getX() - this.getBeginPoint().getX())/2,
                this.getBeginPoint().getY() + (this.getEndPoint().getY() - this.getBeginPoint().getY())/2);
    }
}
// END
