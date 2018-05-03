import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBodyBrute1 {

    public static void main(String[] args) throws FileNotFoundException {
        // for reading from sc
        Scanner console = new Scanner(System.in);

        String filename = console.next();

        File file = new File(filename);

        Scanner sc = new Scanner(file);
        
        final double dt = 0.1;                     // time quantum
        int N = sc.nextInt();                   // number of particles
        double radius = sc.nextDouble();        // radius of universe

        // turn on animation mode and rescale coordinate system
        StdDraw.show(0);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        // read in and initialize bodies
        Body[] bodies = new Body[N];               // array of N bodies
        for (int i = 0; i < N; i++) {
            double px   = sc.nextDouble();
            double py   = sc.nextDouble();
            double vx   = sc.nextDouble();
            double vy   = sc.nextDouble();
            double mass = sc.nextDouble();
            int red     = sc.nextInt();
            int green   = sc.nextInt();
            int blue    = sc.nextInt();
            Color color = new Color(red, green, blue);
            bodies[i]   = new Body(px, py, vx, vy, mass, color);
        }


        // simulate the universe
        for (double t = 0.0; true; t = t + dt) {

            // update the forces
            for (int i = 0; i < N; i++) {
                bodies[i].resetForce();
                for (int j = 0; j < N; j++) {
                    if (i != j) bodies[i].addForce(bodies[j]);
                }
            }

            // update the bodies
            for (int i = 0; i < N; i++) {
                bodies[i].update(dt);
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < N; i++) {
                bodies[i].draw();
            }
            StdDraw.show(10);

        }
    }
}