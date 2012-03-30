package com.domain.reckless.graphics.common;

public class Rect {
    public int x1, y1, x2, y2;

    public Rect(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rect[x1: ").append(x1).append(" y1: ").append(y1).append(" x2: ").append(x2)
                .append(" y2: ").append(y2).append("]");
        return sb.toString();
    }
}
