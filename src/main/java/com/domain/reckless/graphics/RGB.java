package com.domain.reckless.graphics;

public class RGB {
    public int r, g, b;
    public int alpha;

    public RGB(int color) {
        r = color & 0xff0000;
        g = color & 0xff00;
        b = color & 0xff;
        alpha = (color >> 24) & 0xff;
    }

    public int getColor() {
        return (alpha << 24) | r | g | b;
    }
}
