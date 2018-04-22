public class Point {

    private double xAxis;
    private double yAxis;

    public Point(double xAxis, double yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public double getxAxis() {
        return xAxis;
    }

    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    public double getyAxis() {
        return yAxis;
    }

    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public double getDistance(final Point P) {
        final double distance = Math.abs(Math.pow(this.xAxis - P.getxAxis(), 2) +
                Math.pow(this.yAxis - P.getyAxis(), 2));
        return Math.sqrt(distance);
    }

    public static double getDistance(final Point P, final Point Q) {
        final double distance = Math.abs(Math.pow(P.getxAxis() - Q.getxAxis(), 2) +
                Math.pow(P.getyAxis() - Q.getyAxis(), 2));
        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) {
            return false;
        }
        Point P = (Point) obj;
        return P.getxAxis() == this.getxAxis() && P.getyAxis() == this.getyAxis();
    }

}
