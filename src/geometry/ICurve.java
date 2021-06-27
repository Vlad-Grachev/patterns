package geometry;

public interface ICurve {
    IPoint getPoint(double t);
    ICurve getCopy();
}
