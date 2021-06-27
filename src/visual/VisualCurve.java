package visual;

import geometry.ACurve;
import geometry.ICurve;
import geometry.IPoint;
import shared.Config;

import java.util.ArrayList;
import java.util.List;

public abstract class VisualCurve implements ICurve, IDrawable {
    protected ICurve curve;

    @Override
    public void draw(IDrawer drawingContext) {
        double t = 0.0;
        int accuracy = Config.getInstance().getAccuracy();
        double step = accuracy != 0.0 ? 1.0 / accuracy : 0.0;

        drawingContext.drawFirstPoint(getPoint(t));
        t += step;

        for (t = t; t <= 1.0 - step && step != 0.0; t += step) {
            drawingContext.drawNextPoint(getPoint(t));
        }

        drawingContext.drawLastPoint(getPoint(t));

        List<IPoint> points = new ArrayList<>();
    }

    @Override
    public ICurve getCopy() {
        return null;
    }

    public ICurve getCurve() {
        return curve;
    }

    public void setCurve(ICurve curve) {
        this.curve = curve;
    }
}
