public class Point {

    public double x;
    public double y;

    public double distance(Point p) {
        return Math.sqrt((p.x-this.x)*(p.x-this.x)+(p.y-this.y)*(p.y-this.y));
    }

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}
