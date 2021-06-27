package visual.impl;

import geometry.IPoint;
import geometry.impl.Point;
import visual.AGraphicsDrawer;
import visual.PointShapeType;

import java.awt.*;

public class DashedLineGraphicsDrawer extends AGraphicsDrawer {

    private Stroke s = new BasicStroke(3, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

//    private Stroke s = new BasicStroke(3);

    public DashedLineGraphicsDrawer() {
    }

    @Override
    public void drawFirstPoint(IPoint point) {
        super.drawFirstPoint(point);

        drawRectangle(new Point(point.getX() - 7, point.getY() - 7), 14, 14);
    }

    @Override
    public void drawNextPoint(IPoint point) {
        super.drawNextPoint(point);
    }

    @Override
    public void drawLastPoint(IPoint point) {
        super.drawLastPoint(point);

        drawRectangle(new Point(point.getX() - 7, point.getY() - 7), 14, 14);
    }

    @Override
    public Color getLineColor() {
        return Color.BLACK;
    }

    @Override
    public Stroke getStroke() {
        return s;
    }
}
