public class Planet {
    public static final double G = 6.67 * Math.pow(10, -11);
    public static final double changeT = 25000.0;
    public static final double T = 157788000.0;

    private final String planetName;
    private final Point initialVelocity;
    private final Point initialPoint;
    private final double mass;

    private Point currentPoint;
    private Point currentVelocity;
    private Point sunPoint;

    private double F;
    private double r;
    private double sunMass;

    public Planet(Point initialPoint, Point initialVelocity, double mass, String planetName) {
        this.initialVelocity = initialVelocity;
        this.initialPoint = initialPoint;
        this.mass = mass;
        this.planetName = planetName;

        //Cannot make a reference to initial point as it will affect initial point values
        this.currentPoint = new Point(initialPoint.getxAxis(), initialPoint.getyAxis());
        this.currentVelocity = new Point(initialVelocity.getxAxis(), initialVelocity.getyAxis());
    }

    private double getFx() {
        return (F * (sunPoint.getxAxis() - this.currentPoint.getxAxis())) / Point.getDistance(currentPoint, sunPoint);
    }

    /** Calculates Force in y direction */
    private double getFy() {
        return (F * (sunPoint.getyAxis() - this.currentPoint.getyAxis())) / Point.getDistance(currentPoint, sunPoint);
    }

    /** Calculates acceleration and returns it as a Point */
    private Point getAcceleration() {
        final double xAccel = getFx() / this.mass;
        final double yAccel = getFy() / this.mass;
        return new Point(xAccel, yAccel);
    }

    /** Calculates Velocity and updates global velocity variable */
    private void calculateVelocity() {
        final Point acceleration = getAcceleration();
        this.currentVelocity.setxAxis(this.currentVelocity.getxAxis() + changeT * acceleration.getxAxis());
        this.currentVelocity.setyAxis(this.currentVelocity.getyAxis() + changeT * acceleration.getyAxis());
    }

    /** Calculates new position based off of velocity */
    private void calculatePoint() {
        calculateVelocity();
        this.currentPoint.setxAxis(this.currentPoint.getxAxis() + changeT * this.currentVelocity.getxAxis());
        this.currentPoint.setyAxis(this.currentPoint.getyAxis() + changeT * this.currentVelocity.getyAxis());
    }

    /** Moves the planet by calculating its new position */
    public void move() {
        calculatePoint();
    }

    /** Sets the mass of the sun, used for calculating gravity and force */
    public void setSunMass(double sunMass) {
        this.sunMass = sunMass;
    }

    /** Sets the position of the sun, used for calculating gravity and force */
    public void setSunPoint(final Point sun) {
        this.sunPoint = sun;
    }

    /** Sets the F value which is affects planets orbit */
    public void calculateF() {
        //Distance formula from sun center to planet center
        this.r = currentPoint.getDistance(this.sunPoint);
        this.F = (G * this.sunMass * this.mass) / Math.pow(r, 2);
    }


    public double getF() {
        return F;
    }

    public String getPlanetName() {
        return planetName;
    }

    public Point getInitialVelocity() {
        return initialVelocity;
    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public double getMass() {
        return mass;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public Point getCurrentVelocity() {
        return currentVelocity;
    }

}
