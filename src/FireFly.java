public class FireFly {
    private Point point;

    public FireFly(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public static FireFly getRandomFireFly() {
        return new FireFly(new Point(Utils.random(CostFunction.lowerBound, CostFunction.upperBound), Utils.random(CostFunction.lowerBound, CostFunction.upperBound)));
    }
    public static FireFly getRandomFireFlyAround(Point p) {
        double range = Math.sqrt(Input.safeRange / 2);
        return new FireFly(new Point(p.getX1() + Utils.random(-range, range), p.getX2() + Utils.random(-range, range)));
    }

    public double cost() {
        return CostFunction.calc(point);
    }

    public void moveTo(FireFly oth) {
        double r = Utils.distance(this.point, oth.point);
        double beta = CostFunction.beta0 * Math.pow(Math.E, -CostFunction.gamma * r * r);
        point.setX1(point.getX1() + beta * (oth.point.getX1() - point.getX1()) + CostFunction.alpha * Utils.random(-0.5, 0.5));
        point.setX2(point.getX2() + beta * (oth.point.getX2() - point.getX2()) + CostFunction.alpha * Utils.random(-0.5, 0.5));
    }

    @Override
    public String toString() {
        return "FireFly{" +
                "point=" + point +
                "cost=" + cost() +
                '}';
    }
}
