public class Planet {
    public static final double G = 6.67 * Math.pow(10, -11);
    public static final double changeT = 25000.0;
    public static final double T = 157788000.0;

    private String planetName;
    private Point initialVelocity;
    private Point initialPoint;
    private double mass;


    private Point currentPoint;
    private Point currentVelocity;
    private Point sunPoint;

    private double F;
    private double r;
    private double sunMass;

    public Planet(final Point initialPoint, final Point initialVelocity, final double mass, final String planetName) {
        this.initialVelocity = initialVelocity;
        this.initialPoint = initialPoint;
        this.mass = mass;
        this.planetName = planetName;

        //Cannot make a reference to initial point as it will affect initial point values
        this.currentPoint = new Point(initialPoint.getxAxis(), initialPoint.getyAxis());
        this.currentVelocity = new Point(initialVelocity.getxAxis(), initialVelocity.getyAxis());
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
