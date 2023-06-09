public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11; 

    public Planet (double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    };

    public Planet (Planet p) {
        xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
    }

    public double calcDistance (Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    public double calcForceExertedBy (Planet p) {
        double r = calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX (Planet p) {
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY (Planet p) {
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dy / r;
    }

    public double calcNetForceExertedByX (Planet[] allPlanets) {
        double NetForceX = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            NetForceX += calcForceExertedByX(p);
        }
        return NetForceX;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets) {
        double NetForceY = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            NetForceY += calcForceExertedByY(p);
        }
        return NetForceY;
    }

    public void update (double dt, double ForceX, double ForceY) {
        double accX = ForceX / mass;
        double accY = ForceY / mass;
        xxVel += dt * accX;
        yyVel += dt * accY;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
    }

}
