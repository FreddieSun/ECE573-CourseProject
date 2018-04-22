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

    public double getDistance(Point P) {
        double distanceSquare = Math.abs(Math.pow(this.xAxis - P.getyAxis(), 2)) +
                Math.abs(Math.pow(this.yAxis - P.getyAxis(), 2));
        return Math.sqrt(distanceSquare);
    }

    public double getDistance(Point P, Point Q) {
        double distanceSquare = Math.abs(Math.pow(Q.xAxis - P.getyAxis(), 2)) +
                Math.abs(Math.pow(Q.yAxis - P.getyAxis(), 2));
        return Math.sqrt(distanceSquare);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) {
            return false;
        }
        Point P = (Point) obj;
        return P.getxAxis() == this.getxAxis() && P.getyAxis() == this.getyAxis();
    }

    @Override
    public String toString() {
        return "X: " + this.xAxis + "\tY: " + this.yAxis;
    }
}
