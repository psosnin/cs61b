package bearmaps;
import java.util.List;


public class NaivePointSet implements PointSet{
    private List<Point> points;
    public NaivePointSet(List<Point> p) {
        points = p;
    }
    /* Returns the closest point to the input coordinates */
    @Override
    public Point nearest(double x, double y) {
        double minDistance = Double.POSITIVE_INFINITY;
        Point minPoint = null;
        for (Point p : points) {
            double distance = Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
            if (distance < minDistance) {
                minPoint = p;
                minDistance = distance;
            }
        }
        return minPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }

}
