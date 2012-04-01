package com.domain.reckless.graphics;

public interface Screen {
    void putPixel(int x, int y, int col);
    void putPixel(int x, int y, int col, int alpha);
    void setOffset(int x, int y);
}
