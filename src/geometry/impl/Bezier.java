package geometry.impl;

import geometry.ACurve;
import geometry.ICurve;
import geometry.IPoint;

public class Bezier extends ACurve {
    private IPoint a;
    private IPoint b;
    private IPoint c;
    private IPoint d;

    public Bezier(IPoint a, IPoint b, IPoint c, IPoint d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public IPoint getPoint(double t) {
        return new Point(getFunctionValue(a.getX(), b.getX(), c.getX(), d.getX(), t),
                         getFunctionValue(a.getY(), b.getY(), c.getY(), d.getY(), t));
    }

    private double getFunctionValue(double a, double b, double c, double d, double t) {
        return pow(1 - t, 3) * a + 3.0 * t * pow(1 - t, 2) * c
                + 3.0 * pow (t, 2) * (1 -t) * d + pow(t, 3) * b;
    }

    private double pow(double base, int exp) {
        for (int i = 0; i < exp; i++) {
            base *= base;
        }

        return base;
    }

    @Override
    public ICurve clone() {
        IPoint aCopy = new Point(a.getX(), a.getY());
        IPoint bCopy = new Point(b.getX(), b.getY());
        IPoint cCopy = new Point(c.getX(), c.getY());
        IPoint dCopy = new Point(d.getX(), d.getY());
        return new Bezier(aCopy, bCopy, cCopy, dCopy);
    }
}
