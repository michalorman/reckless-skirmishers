package com.domain.reckless.graphics;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.bitmap.ImageBitmap;

public class DefaultScreen extends ImageBitmap implements Screen {
    private ApplicationFrame af;
    private int xOffset, yOffset;

    public DefaultScreen(Builder builder) {
        super(builder.w, builder.h);
        af = new ApplicationFrame.Builder(w, h, this).scale(builder.scale).title(builder.title).resizable(false).build();
    }

    @Override
    public void blit(Bitmap bmp, int x, int y) {
        super.blit(bmp, x + xOffset, y + yOffset);
    }

    @Override
    public void setOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }

    public void render() {
        af.render();
    }

    public static class Builder {
        private final int w;
        private final int h;
        private double scale = 1;
        private String title;

        public Builder(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public Builder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public DefaultScreen build() {
            return new DefaultScreen(this);
        }
    }
}
