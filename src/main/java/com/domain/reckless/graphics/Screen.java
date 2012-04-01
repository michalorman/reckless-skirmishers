package com.domain.reckless.graphics;

import com.domain.reckless.graphics.bitmap.Bitmap;

public interface Screen {
    void putPixel(int x, int y, int col);
    void putPixel(int x, int y, int col, int alpha);
    void setOffset(int x, int y);
    void render();
    void blit(Bitmap bmp, int x, int y);
}
