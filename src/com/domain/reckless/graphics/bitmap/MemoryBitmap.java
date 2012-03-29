package com.domain.reckless.graphics.bitmap;

public class MemoryBitmap implements Bitmap {
    protected int w, h;
    protected int pixels[];

    public MemoryBitmap(int w, int h) {
        this.w = w;
        this.h = h;
        pixels = new int[w * h];
    }

    public MemoryBitmap(String filename) {
        load(filename);
    }

    public void putPixel(int x, int y, int col) {
        putPixel(x, y, col, 255);
    }


    public void putPixel(int x, int y, int col, int alpha) {
        if (isValidPosition(x, y)) {
            int r = col & 0xff0000;
            int g = col & 0xff00;
            int b = col & 0xff;
            int result = alpha << 24 | r | g | b;
            pixels[y * w + x] = result;
        }
    }

    public void load(String filename) {

    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }
}
