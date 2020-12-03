public class Utils {
    public static double distance(Point a, Point b) {
        return Math.sqrt((a.getX1() - b.getX1()) * (a.getX1() - b.getX1()) + (a.getX2() - b.getX2()) * (a.getX2() - b.getX2()));
    }

    public static double random(double l, double r) {
        return Math.random() * (r - l) + l;
    }

    public static double distanceFromClosestObstacle(Point p) {
        double distance = 1e15;
        for(Obstacle obstacle : Input.obstacles) {
            distance = Math.min(distance, distanceFromObstacle(p, obstacle));
        }
        if(distance < Input.safeRange / 5) {
            distance = Input.zero;
        }
        return distance;
    }

    public static double distanceFromObstacle(Point p, Obstacle obstacle) {
        double distance = 1e15;
        for(int i = 0; i < obstacle.getVertexes().size(); i ++) {
            Point p1 = obstacle.getVertexes().get(i != 0 ? i - 1 : obstacle.getVertexes().size() - 1);
            Point p2 = obstacle.getVertexes().get(i);
            distance = Math.min(distance, distanceFromSegment(p, p1, p2));
        }
        return distance;
    }

    public static double distanceFromSegment(Point a, Point p1, Point p2) {
        double distance = 1e15;
        double A = a.getX1() - p1.getX1();
        double B = a.getX2() - p1.getX2();
        double C = p2.getX1() - p1.getX1();
        double D = p2.getX2() - p1.getX2();

        double dot = A * C + B * D;
        double len_sq = C * C + D * D;
        double param = -1;
        if (len_sq != 0) //in case of 0 length line
            param = dot / len_sq;

        double xx, yy;

        if (param < 0) {
            xx = p1.getX1();
            yy = p1.getX2();
        }
        else if (param > 1) {
            xx = p2.getX1();
            yy = p2.getX2();
        }
        else {
            xx = p1.getX1() + param * C;
            yy = p1.getX2() + param * D;
        }

        double dx = a.getX1() - xx;
        double dy = a.getX2() - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

}
