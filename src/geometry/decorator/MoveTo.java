package geometry.decorator;

import geometry.ICurve;
import geometry.IPoint;
import geometry.impl.Point;

public class MoveTo extends ADecorator {
    private IPoint shift;

    public MoveTo(ICurve component, IPoint shift) {
        super(component);

        this.shift = shift;
    }

    @Override
    public IPoint getPoint(double t) {
        IPoint initialPoint = component.getPoint(t);

        return new Point(initialPoint.getX() + shift.getX(),
                         initialPoint.getY() + shift.getY());
    }

    @Override
    public ICurve getCopy() {
        ICurve componentCopy = getComponent().getCopy();

        return new MoveTo(componentCopy, this.shift);
    }

    public IPoint getShift() {
        return shift;
    }

    public void setShift(IPoint shift) {
        this.shift = shift;
    }
}
