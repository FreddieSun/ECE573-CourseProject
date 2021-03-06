

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
    final static double deltaT = 0.1;                     // time quantum

    public static void main(String[] args) throws FileNotFoundException {

        // for reading from stdin
        Scanner console = new Scanner(System.in);

        String filename = console.next();

        File file = new File(filename);
    
        Scanner sc = new Scanner(file);

        // number of particles
        int N = sc.nextInt();
        // radius of universe
        double radius = sc.nextDouble();

        // turn on animation mode and rescale coordinate system
        StdDraw.show(0);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        // read in and initialize bodies
        Body[] bodies = new Body[N];               // array of N bodies
        for (int i = 0; i < N; i++) {
            double rx   = sc.nextDouble();
            double ry   = sc.nextDouble();
            double vx   = sc.nextDouble();
            double vy   = sc.nextDouble();
            double mass = sc.nextDouble();
            int red     = sc.nextInt();
            int green   = sc.nextInt();
            int blue    = sc.nextInt();

            Color color = new Color(red, green, blue);
            bodies[i]   = new Body(rx, ry, vx, vy, mass, color);
        }

        double start = System.currentTimeMillis();
        int count = 0;
        for (double t = 0.0; true; t = t + deltaT) {
            count++;
            Quad quad = new Quad(0, 0, radius * 2);
            BHTree tree = new BHTree(quad);

            // build the Barnes-Hut tree
            for (int i = 0; i < N; i++)
                if (bodies[i].in(quad))
                    tree.insert(bodies[i]);

            // update the forces, positions, velocities, and accelerations
            for (int i = 0; i < N; i++) {
                bodies[i].resetForce();
                // if is still in the universe
                if (bodies[i].in(quad)) {
                    tree.updateForce(bodies[i]);
                    bodies[i].update(deltaT);
                }
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);

            for (int i = 0; i < N; i++)
                bodies[i].draw();

            StdDraw.show(10);
            t += deltaT;
            if ( count == 200)
                break;
        }

        double end = System.currentTimeMillis();
        System.out.println("200次循环，用了这么多秒： " + String.valueOf(end - start));

    }
}