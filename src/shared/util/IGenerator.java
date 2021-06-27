package shared.util;

import geometry.ICurve;
import geometry.IPoint;
import visual.VisualCurve;

public interface IGenerator {
    IPoint getRandomPoint(int beginX, int beginY, int areaWidth, int areaHeight);
    ICurve getRandomCurve(int beginX, int beginY, int areaWidth, int areaHeight);
    VisualCurve getRandomVisualCurve(int beginX, int beginY, int areaWidth, int areaHeight);
}
