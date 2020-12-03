import static java.lang.Math.exp;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import static java.lang.Math.E;

public class CostFunction {

    // f(x) = x1^2 + x2^2
    public static double beta0;
    public static double gamma;
    public static double alpha;

    public static double alphaDec;

    public static double lowerBound = -2 * PI;
    public static double upperBound = 2 * PI;

    public static double w1 = 0.1;
    public static double w2 = 1;

    public static void init() {
        beta0 = 0.05;
        gamma = 0.05;
        alpha = 0.05;
        alphaDec = 0.5;
    }

    public static double calc(Point p) {
        double df = Utils.distanceFromClosestObstacle(p);
        double dg = Utils.distance(p, Input.goal);
        return w1 / df + w2 * dg;
    }

}
