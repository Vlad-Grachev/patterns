package geometry.decorator;

import geometry.ICurve;
import geometry.IPoint;

public class Fragment extends ADecorator {
    private double tStart = 0.0;
    private double tFinish = 1.0;

    public Fragment(ICurve component, double tStart, double tFinish) {
        super(component);
        this.tStart = tStart;
        this.tFinish = tFinish;
    }

    @Override
    public IPoint getPoint(double t) {
        return component.getPoint(tStart +  t * (tFinish - tStart));
    }

    @Override
    public ICurve getCopy() {
        ICurve componentCopy = getComponent().getCopy();
        return new Fragment(componentCopy, this.tStart, this.tFinish);
    }

    public double getTStart() {
        return tStart;
    }

    public void setTStart(double tStart) {
        this.tStart = tStart;
    }

    public double getTFinish() {
        return tFinish;
    }

    public void setTFinish(double tFinish) {
        this.tFinish = tFinish > tStart ? tFinish : this.tFinish;
    }
}
