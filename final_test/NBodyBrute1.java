import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBodyBrute1 {
    private final static int step = 5000;
    private final static boolean isDemo = false;

    public static void main(String[] args) throws FileNotFoundException {

        String dataFolder = "dataset";

        String filename[] = {
              "galaxy10k.txt",
              "galaxy20k.txt",
              "galaxy30k.txt",
              "saturnrings.txt",
              "cluster2.5k.txt",
              "collision1.txt",
              "collision2.txt",
              "galaxyform2.5k.txt",
              "galaxymerge1.txt",
              "galaxymerge2.txt",
              "galaxymerge3.txt",
              "galaxy1.txt",
              "galaxy2.txt",
              "galaxy3.txt",
              "galaxy4.txt",
              "spiralgalaxy.txt",
              "asteroids1000.txt"
        };

        for(String fn : filename){
          File file = new File(dateset+"/"+fn);

          Scanner sc = new Scanner(file);

          final double dt = 0.1;                     // time quantum
          int N = sc.nextInt();                   // number of particles
          double radius = sc.nextDouble();        // radius of universe

          if(isDemo){
            // turn on animation mode and rescale coordinate system
            StdDraw.show(0);
            StdDraw.setXscale(-radius, +radius);
            StdDraw.setYscale(-radius, +radius);
          }

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


          double start = System.currentTimeMillis();
          int count = 0;
          // simulate the universe
          for (double t = 0.0; true; t = t + dt) {
              count++;
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

              if(isDemo){
                // draw the bodies
                StdDraw.clear(StdDraw.BLACK);
                for (int i = 0; i < N; i++) {
                    bodies[i].draw();
                }
                StdDraw.show(10);
              }

              if (count == step)
                  break;
          }
          double end = System.currentTimeMillis();

          System.out.println(fn + " cost " + String.valueOf(end - start) + " ms");
        }
    }
}
