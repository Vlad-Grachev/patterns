package visual;

import geometry.IPoint;

import java.util.Collection;

public interface IDrawer {
    void drawFirstPoint(IPoint point);
    void drawNextPoint(IPoint point);
    void drawLastPoint(IPoint point);
}
