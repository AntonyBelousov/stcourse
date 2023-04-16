public class MyFirstProgram {
    public static void main(String[] args) {
        Point p1 = new Point(3,4);
        Point p2 = new Point(7,1);

        System.out.println("Distance from function = " + distance(p1, p2));
        System.out.println("Distance from method = " + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x-p2.x)*(p1.x- p2.x)+(p1.y-p2.y)*(p1.y- p2.y));
    }
}
