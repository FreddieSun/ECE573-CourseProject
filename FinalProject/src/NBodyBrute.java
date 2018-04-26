import java.io.File;

public class NBodyBrute {

    //Time variables
    public static final double changeT = 25000.0;
    public static final double T = 157788000.0;

    private final int numPlanets;
    private final double universeRadius;
    private final Planet[] planets;


    public NBodyBrute() {
        //Reads info from file
        final In fileIn = new In(new File("planets.txt"));

        this.numPlanets = fileIn.readInt();
        this.universeRadius = fileIn.readDouble();

        this.planets = new Planet[numPlanets];

        for(int i = 0; i < this.numPlanets; i++) {
            final Point initialPoint = new Point(fileIn.readDouble(), fileIn.readDouble());
            final Point initialVelocity = new Point(fileIn.readDouble(), fileIn.readDouble());
            final double mass = fileIn.readDouble();
            final String planetName = fileIn.readString();
            this.planets[i] = new Planet(initialPoint, initialVelocity, mass, planetName);
        }
        fileIn.close();

        StdDraw.setScale(-1 * universeRadius, universeRadius);

        //Gets the mass and location of the Sun which is one of the planets
        double sunMass = 0;
        Point sunPoint = null;
        for(Planet planet : planets) {
            if(planet.getPlanetName().contains("sun")) {
                sunMass = planet.getMass();
                sunPoint = planet.getCurrentPoint();
            }
        }

        //Goes through all of the planets and sets their sunMass and sunPoint
        //Values in order to calculate F, the effect of the sun's gravity on the planet's orbit
        for(Planet planet : planets) {
            if(!planet.getPlanetName().contains("sun")) {
                planet.setSunMass(sunMass);
                planet.setSunPoint(sunPoint);
                planet.calculateF();
            }
        }


        //Draw the planets the first time
        drawPlanets();

        //Start at t = 0, increase by changeT until we've reached max time (T)
        for(double time = 0; changeT <= T; time += changeT) {
            for(Planet planet : planets) {
                if(!planet.getPlanetName().contains("sun")) {
                    planet.move();
                }
            }
            drawPlanets();
        }
    }

    private void drawPlanets() {
        StdDraw.show(25);
        StdDraw.picture(0, 0, "starfield.jpg");
        for(Planet planet : planets) {
            StdDraw.picture(planet.getCurrentPoint().getxAxis(), planet.getCurrentPoint().getyAxis(), planet.getPlanetName());
        }
    }

    public static void main(String[] args) {
        NBodyBrute nBodySimulation = new NBodyBrute();
    }


}
