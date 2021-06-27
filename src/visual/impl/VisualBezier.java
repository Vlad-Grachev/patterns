package visual.impl;

import geometry.ICurve;
import geometry.IPoint;
import geometry.impl.Bezier;
import visual.VisualCurve;

import java.awt.*;

public class VisualBezier extends VisualCurve {
    private Graphics2D g2d;

    public VisualBezier(Bezier bezier) {
        this.curve = bezier;
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t);
    }
}
