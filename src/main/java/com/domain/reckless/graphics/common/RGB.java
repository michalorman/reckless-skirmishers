package com.domain.reckless.graphics.common;

public class RGB {
    public int r, g, b;
    public int alpha;

    public RGB(int color) {
        r = color & 0xff0000;
        g = color & 0xff00;
        b = color & 0xff;
        alpha = color & 0xff000000;
    }
}
