public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet [] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Planet planets[] = new Planet[N];
        in.readDouble();

        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return planets;
    }


    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet [] planets = readPlanets(filename);
        int N = planets.length;
        double R = readRadius(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        String imageToDraw = "images/starfield.jpg";
        double t = 0.0;

        while (t < T) {
            double[] xForce = new double[N];
            double[] yForce = new double[N];
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for (int i = 0; i < N; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }




}
