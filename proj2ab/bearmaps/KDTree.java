package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet{
    Node root;
    Comparator<Point> yComparator = (Point arg1, Point arg2) -> (Double.compare(arg1.getY(),arg2.getY()));
    Comparator<Point> xComparator = (Point arg1, Point arg2) -> (Double.compare(arg1.getX(),arg2.getX()));

    private class Node {
        Comparator<Point> comparator;
        Point point;
        Node leftChild;
        Node rightChild;
        Node(Point p, Comparator<Point> c) {
            point = p;
            comparator = c;
        }
    }

    public KDTree(List<Point> points) {

    }

    /* Returns the closest point to the input coordinates */
    @Override
    public Point nearest(double x, double y) {

    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }


}
