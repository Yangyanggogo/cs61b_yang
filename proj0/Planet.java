public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;


    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        this(P.xxPos, P.yyPos, P.xxVel, P.yyVel, P.mass, P.imgFileName);
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return 6.67e-11 * this.mass * p.mass/(r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * dx/this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) * dy/this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet [] allPlanets) {
        double res = 0;
        for (int i = 0; i < allPlanets.length; i += 1) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            else {
                res += this.calcForceExertedByX(allPlanets[i]);
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet [] allPlanets) {
        double res = 0;
        for (int i = 0; i < allPlanets.length; i += 1) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            else {
                res += this.calcForceExertedByY(allPlanets[i]);
            }
        }
        return res;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
