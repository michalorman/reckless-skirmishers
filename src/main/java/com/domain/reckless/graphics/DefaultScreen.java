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
        super.alphaBlit(bmp, x + xOffset, y + yOffset, alpha);
    }

    @Override
    public void colorBlit(Bitmap bmp, int x, int y, int color) {
        super.colorBlit(bmp, x + xOffset, y + yOffset, color);
    }

    @Override
    public void rectFill(int x1, int y1, int x2, int y2, int color) {
        super.rectFill(x1 + xOffset, y1 + yOffset, x2 + xOffset, y2 + yOffset, color);
    }

    @Override
    public void setOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }

    public void render() {
        frameContext.render();
    }

    public void centerAt(int posx, int posy) {
        setOffset(-(posx - w / 2), -(posy - h / 2));
    }
}
