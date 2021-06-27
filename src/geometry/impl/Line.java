package geometry.impl;

import geometry.ACurve;
import geometry.ICurve;
import geometry.IPoint;

public class Line extends ACurve {
    private IPoint a;
    private IPoint b;

    public Line(IPoint a, IPoint b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public IPoint getPoint(double t) {
        return new Point(getFunctionValue(a.getX(), b.getX(),t),
                         getFunctionValue(a.getY(), b.getY(),t));
    }

    private double getFunctionValue(double a, double b, double t) {
        return (1 - t) * a + t * b;
    }

    @Override
    public ICurve getCopy() {
        IPoint aCopy = new Point(a.getX(), a.getY());
        IPoint bCopy = new Point(b.getX(), b.getY());
        return new Line(aCopy, bCopy);
    }
}
