package geometry.composite;

import geometry.ICurve;
import geometry.IPoint;
import geometry.decorator.MoveTo;
import geometry.impl.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Chain implements ICurve {
    List<ICurve> curves;

    public Chain(ICurve... curves) {
        this.curves = new ArrayList<>();
        this.curves.addAll(Arrays.asList(curves));
    }

    public Chain(Collection<ICurve> curves) {
        this.curves = new ArrayList<>();
        this.curves.addAll(curves);
    }

    public void add(ICurve curve) {
        ICurve curveToAdd = curve;
        if (!curves.isEmpty()) {
            ICurve lastCurve = curves.get(curves.size() - 1);
            
            IPoint lastCurveEndPoint = lastCurve.getPoint(1.0);
            IPoint curveToAddStartPoint = curveToAdd.getPoint(0.0);
            
            double xShift = curveToAddStartPoint.getX() > lastCurveEndPoint.getX()
                    ? -1.0 * (curveToAddStartPoint.getX() - lastCurveEndPoint.getX())
                    : lastCurveEndPoint.getX() - curveToAddStartPoint.getX();

            double yShift = curveToAddStartPoint.getY() > lastCurveEndPoint.getY()
                    ? -1.0 * (curveToAddStartPoint.getY() - lastCurveEndPoint.getY())
                    : lastCurveEndPoint.getY() - curveToAddStartPoint.getY();

            curveToAdd = new MoveTo(curveToAdd, new Point(xShift, yShift));
        }
        curves.add(curveToAdd);
    }

    public void removeLast() {
        if (!curves.isEmpty()) {
            curves.remove(curves.size() - 1);
        }
    }

    @Override
    public IPoint getPoint(double t) {
        double partition = 1.0 / curves.size();

        int currentCurveIndex = (int) (t / partition);

        return curves.get(currentCurveIndex)
                .getPoint(t / partition - currentCurveIndex);
    }

    @Override
    public ICurve getCopy() {
        return new Chain(
                curves.stream()
                    .map(ICurve::getCopy)
                    .collect(Collectors.toList()));
    }
}
