public class Point {
    private double x1, x2;

    public boolean isReach() {
        return Math.abs(x1 - Input.goal.x1) <= Input.eps && Math.abs(x2 - Input.goal.x2) <= Input.eps;
    }



    public Point(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                '}';
    }
}
