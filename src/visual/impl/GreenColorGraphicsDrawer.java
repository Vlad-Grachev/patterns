package visual.impl;

import geometry.IPoint;
import geometry.impl.Point;
import visual.AGraphicsDrawer;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class GreenColorGraphicsDrawer extends AGraphicsDrawer {

    private static final int CIRCE_RADIUS = 16;
    private static final double NEXT_LINEAR_POINT_STEP = 16;
    private static final int ARROW_WIDTH = 9;

    private Stroke s = new BasicStroke(3);

    public GreenColorGraphicsDrawer() {
    }

    @Override
    public void drawFirstPoint(IPoint point) {
        super.drawFirstPoint(point);

        super.drawOval(new Point(previousPoint.getX() - CIRCE_RADIUS / 2,
                           previousPoint.getY() - CIRCE_RADIUS / 2),
                CIRCE_RADIUS, CIRCE_RADIUS);
    }

    @Override
    public void drawLastPoint(IPoint point) {
        IPoint point2;
        boolean flag = false;
        if (point.getX() > previousPoint.getX()) {
            flag = true;
            point2 = previousPoint;
        } else {
            point2 = point;
            point = previousPoint;
        }

        IPoint nextLinearPoint = getNextLinearPoint(point2, point, NEXT_LINEAR_POINT_STEP, flag);
        super.drawLastPoint(point);
        super.drawLine(point, nextLinearPoint);

        List<IPoint> arrowAngles = getPointsForArrow(point, nextLinearPoint, ARROW_WIDTH);
        arrowAngles.forEach(p -> drawLine(nextLinearPoint, p));
    }

    @Override
    public Color getLineColor() {
        return Color.GREEN;
    }

    @Override
    public Stroke getStroke() {
        return s;
    }

    private List<IPoint> getPointsForArrow(IPoint a, IPoint b, int distance) {
        List<IPoint> result = new ArrayList<>();

        double temp = distance * abs(b.getY() - a.getY())
                      / sqrt(pow((b.getX() - a.getX()), 2) + pow((b.getY() - a.getY()), 2));

        double x1 = a.getX() + temp;
        double y1 = getYValue(a, b, x1);

        double x2 = a.getX() - temp;
        double y2 = getYValue(a, b, x2);

        result.add(new Point(x1, y1));
        result.add(new Point(x2, y2));

        return result;
    }

    private double getYValue(IPoint a, IPoint b, double x) {
        return (a.getX() - b.getX()) * (x - a.getX()) / (b.getY() - a.getY()) + a.getY();
    }

    private IPoint getNextLinearPoint(IPoint a, IPoint b, double step, boolean flag) {
        double localStep = abs(b.getY() - a.getY()) > 18 ? step * 0.1 : step;
        double nextX = flag ? b.getX() + step : b.getX() - localStep;
        double nextY = ((a.getY() - b.getY()) * nextX + a.getX() * b.getY() - b.getX() * a.getY())
                       / (a.getX() - b.getX());
        return new Point(nextX, nextY);
    }
}
