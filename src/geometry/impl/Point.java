package geometry.impl;

import geometry.IPoint;

public class Point implements IPoint {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%.0f %.0f", getX(), getY());
    }
}
