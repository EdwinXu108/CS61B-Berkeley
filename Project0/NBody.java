public class NBody {
    
    public static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets (String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] arr_Planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
            arr_Planets[i] = new Planet(xP, yP, xV, yV, m, img);
        } 
        return arr_Planets;
    }

    public static void main(String[] args) {
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] arr_Planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();

        double time = 0;
        int num = arr_Planets.length;

        while (time <= T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];

            for (int i = 0; i < num; i++) {
                xForces[i] = arr_Planets[i].calcNetForceExertedByX(arr_Planets);
                yForces[i] = arr_Planets[i].calcNetForceExertedByY(arr_Planets);
            }

            for (int i = 0; i < num; i++) {
                arr_Planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet p: arr_Planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", arr_Planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < arr_Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          arr_Planets[i].xxPos, arr_Planets[i].yyPos, arr_Planets[i].xxVel,
                          arr_Planets[i].yyVel, arr_Planets[i].mass, arr_Planets[i].imgFileName);   
        }

    }
}