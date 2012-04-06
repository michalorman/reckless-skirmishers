package com.domain.reckless.math;

public class Vect2d {

    public double x, y;

    public Vect2d() {
        this(0, 0);
    }

    public Vect2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vect2d(Vect2d other) {
        this(other.x, other.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vect2d vect2d = (Vect2d) o;

        if (Double.compare(vect2d.x, x) != 0) return false;
        if (Double.compare(vect2d.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
