package visual.impl;

import geometry.IPoint;
import geometry.impl.Line;
import visual.VisualCurve;

import java.awt.*;

public class VisualLine extends VisualCurve {
    private Graphics2D g2d;

    public VisualLine(Line line) {
        this.curve = line;
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t);
    }
}
