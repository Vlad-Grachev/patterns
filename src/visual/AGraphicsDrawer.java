package visual;

import geometry.IPoint;
import geometry.impl.Point;

import java.awt.*;
import java.util.Collection;

public abstract class AGraphicsDrawer implements IDrawer {

    private Graphics2D g2d;

    protected IPoint previousPoint;

    public abstract Color getLineColor();

    public abstract Stroke getStroke();

    @Override
    public void drawFirstPoint(IPoint point) {
        refreshG2D();
        previousPoint = point;
    }

    @Override
    public void drawNextPoint(IPoint point) {
        drawLine(previousPoint, point);
        previousPoint = point;
    }

    @Override
    public void drawLastPoint(IPoint point) {
        drawLine(previousPoint, point);
        previousPoint = null;
    }

    public void refreshG2D() {
        getG2d().setColor(getLineColor());
        getG2d().setStroke(getStroke());
    }

    public void drawLine(IPoint a, IPoint b) {
        if (a != null && b != null) {
            refreshG2D();
            getG2d().drawLine(
                    (int)a.getX(), (int)a.getY(),
                    (int) b.getX(), (int) b.getY());
        }
    }

    public void drawRectangle(IPoint a, int width, int height) {
        if (a != null) {
            refreshG2D();
            getG2d().fillRect((int) a.getX(), (int) a.getY(),
                    width, height);
        }
    }

    public void drawOval(IPoint center, int width, int height) {
        if (center != null) {
            refreshG2D();
            getG2d().fillOval((int) center.getX(), (int) center.getY(),
                    width, height);
        }
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }
}
