package com.domain.reckless.graphics;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.font.BitmapFont;

public interface Screen {
    void putPixel(int x, int y, int col);

    void putPixel(int x, int y, int col, int alpha);

    void setOffset(int x, int y);

    void blit(Bitmap bmp, int x, int y);

    void alphaBlit(Bitmap bmp, int x, int y, int alpha);

    void colorBlit(Bitmap bmp, int x, int y, int alpha);

    void write(BitmapFont font, int x, int y, String message);
}
