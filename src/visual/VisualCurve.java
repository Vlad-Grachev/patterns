package visual;

import geometry.ICurve;
import geometry.IPoint;
import shared.Config;

public class VisualCurve implements ICurve, IDrawable {
    protected ICurve curve;

    public VisualCurve(ICurve curve) {
        this.curve = curve;
    }

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
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t);
    }

    @Override
    public ICurve clone() {
        return new VisualCurve(this.curve.clone());
    }

    public ICurve getCurve() {
        return curve;
    }

    public void setCurve(ICurve curve) {
        this.curve = curve;
    }
}
