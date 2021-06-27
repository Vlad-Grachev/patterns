package geometry.decorator;

import geometry.ICurve;
import geometry.IPoint;

public abstract class ADecorator implements ICurve {
    protected ICurve component;

    public ADecorator(ICurve component) {
        this.component = component;
    }

    @Override
    public abstract IPoint getPoint(double t);

    @Override
    public abstract ICurve getCopy();

    public ICurve getComponent() {
        return component;
    }

    public void setComponent(ICurve component) {
        this.component = component;
    }
}
