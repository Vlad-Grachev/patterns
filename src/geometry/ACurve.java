package geometry;

public abstract class ACurve implements ICurve {
    @Override
    public abstract IPoint getPoint(double t);

    @Override
    public abstract ICurve getCopy();
}
