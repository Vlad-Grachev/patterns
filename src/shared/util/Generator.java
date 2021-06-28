package shared.util;

import geometry.ICurve;
import geometry.IPoint;
import geometry.impl.Bezier;
import geometry.impl.Line;
import geometry.impl.Point;
import visual.VisualCurve;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator implements IGenerator {
    private Random random;

    private List recentPoints = new ArrayList();

    public Generator() {
        this.random = new Random();
    }

    private ICurve getRandomCurveByType(int beginX, int beginY, int areaWidth, int areaHeight, int curveType) {
        if (curveType == 0) {
            return getRandomLine(beginX, beginY, areaWidth, areaHeight);
        } else {
            return getRandomBezier(beginX, beginY, areaWidth, areaHeight);
        }
    }

    @Override
    public ICurve getRandomCurve(int beginX, int beginY, int areaWidth, int areaHeight) {
        int curveType = getCurveType();

        return getRandomCurveByType(beginX, beginY, areaWidth, areaHeight, curveType);
    }

    @Override
    public VisualCurve getRandomVisualCurve(int beginX, int beginY, int areaWidth, int areaHeight) {
        int curveType = getCurveType();

        if (curveType == 0) {
            return new VisualCurve(getRandomLine(beginX, beginY, areaWidth, areaHeight));
        } else {
            return new VisualCurve(getRandomBezier(beginX, beginY, areaWidth, areaHeight));
        }
    }

    @Override
    public IPoint getRandomPoint(int beginX, int beginY, int areaWidth, int areaHeight) {
        int x = random.nextInt(beginX + areaWidth +1);
        int y = random.nextInt(beginY + areaHeight + 1);

        if (recentPoints.size() != 0) {
        }

        return new Point(x, y);
    }

    private int getCurveType() {
        return random.nextInt(2);
    }

    private Line getRandomLine(int beginX, int beginY, int areaWidth, int areaHeight) {
        return new Line(
                getRandomPoint(beginX, beginY, areaWidth, areaHeight),
                getRandomPoint(beginX, beginY, areaWidth, areaHeight));
    }

    private Bezier getRandomBezier(int beginX, int beginY, int areaWidth, int areaHeight) {
        return new Bezier(
                getRandomPoint(beginX, beginY, areaWidth, areaHeight),
                getRandomPoint(beginX, beginY, areaWidth, areaHeight),
                getRandomPoint(beginX, beginY, areaWidth, areaHeight),
                getRandomPoint(beginX, beginY, areaWidth, areaHeight));
    }
}
