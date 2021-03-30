public class Body {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV,double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        return Math.sqrt(dx*dx  + dy*dy);
    }

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        return (mass*b.mass*G) / (r*r);  
    }

    public double calcForceExertedByX(Body b) {
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return F*(b.xxPos - xxPos) / r ;
    }
    public double calcForceExertedByY(Body b) {
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return F*(b.yyPos - yyPos) / r ;
    }
    public double calcNetForceExertedByX(Body[] b) {
        double net = 0;
        for (int i = 0; i < b.length; i++ ) {
            if (!equals(b[i])) {
                net = net + calcForceExertedByX(b[i]);
            }
        }
        return net;
    }
    public double calcNetForceExertedByY(Body[] b) {
        double net = 0;
        for (int i = 0; i < b.length; i++ ) {
            if (!equals(b[i])) {
                net = net + calcForceExertedByY(b[i]);
            }
        }
        return net;
    }

    public void update(double dt, double fx, double fy) {
        double xa = fx / mass;
        double ya = fy / mass;
        xxVel = xxVel + dt * xa;
        yyVel = yyVel + dt * ya;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
    
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "/images/" + imgFileName);
    }

}