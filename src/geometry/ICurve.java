package geometry;

public interface ICurve extends Cloneable {
    IPoint getPoint(double t);
    ICurve clone();
}
