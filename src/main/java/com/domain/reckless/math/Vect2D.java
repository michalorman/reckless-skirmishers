package com.domain.reckless.math;

public class Vect2D {

    public double x, y;

    public Vect2D() {
        this(0, 0);
    }

    public Vect2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vect2D(Vect2D other) {
        this(other.x, other.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vect2D vect2D = (Vect2D) o;

        if (Double.compare(vect2D.x, x) != 0) return false;
        if (Double.compare(vect2D.y, y) != 0) return false;

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
