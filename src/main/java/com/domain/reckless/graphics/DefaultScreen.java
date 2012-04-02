package com.domain.reckless.graphics;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.bitmap.ImageBitmap;

public class DefaultScreen extends ImageBitmap implements Screen {
    private SwingApplicationFrame frameContext;
    private int xOffset, yOffset;

    public DefaultScreen(int w, int h, SwingApplicationFrame frameContext) {
        super(w, h);
        this.frameContext = frameContext;
    }

    @Override
    public void blit(Bitmap bmp, int x, int y) {
        super.blit(bmp, x + xOffset, y + yOffset);
    }

    @Override
    public void alphaBlit(Bitmap bmp, int x, int y, int alpha) {
        super.alphaBlit(bmp, x, y, alpha);
    }

    @Override
    public void colorBlit(Bitmap bmp, int x, int y, int color) {
        super.colorBlit(bmp, x, y, color);
    }

    @Override
    public void setOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }

    public void render() {
        frameContext.render();
    }

}
