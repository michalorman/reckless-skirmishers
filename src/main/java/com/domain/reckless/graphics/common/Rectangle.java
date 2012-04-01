package com.domain.reckless.graphics.common;

public class Rectangle extends java.awt.Rectangle {
    public int x1, y1, x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2 - x1, y2 - y1);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle(java.awt.Rectangle awtRectangle) {
        this(awtRectangle.x, awtRectangle.y, awtRectangle.x + awtRectangle.width, awtRectangle.y + awtRectangle.height);
    }

    public Rectangle intersection(Rectangle r) {
        java.awt.Rectangle result = super.intersection(r);
        return new Rectangle(result);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}
