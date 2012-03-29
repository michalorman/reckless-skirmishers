package com.domain.reckless.graphics.bitmap;

public interface Bitmap {

    void putPixel(int x, int y, int color);
    void putPixel(int x, int y, int color, int alpha);
    //TODO alpha putPixel

    //TODO blitting

    void load(String filename);
}
