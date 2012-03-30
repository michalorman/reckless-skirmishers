package com.domain.reckless.graphics.bitmap;

import com.domain.reckless.graphics.common.Rect;
import com.domain.reckless.graphics.utils.PixelUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    protected int w, h;
    protected int pixels[];

    public Bitmap(int w, int h) {
        this(w, h, true);
    }

    protected Bitmap(int w, int h, boolean createPixels) {
        this.w = w;
        this.h = h;
        if (createPixels) {
            pixels = new int[w * h];
        }
    }

    public void putPixel(int x, int y, int col) {
        if (isValidPosition(x, y)) {
            pixels[y * w + x] = col;
        }
    }

    public void putPixel(int x, int y, int col, int alpha) {
        if (isValidPosition(x, y)) {
            int r = col & 0xff0000;
            int g = col & 0xff00;
            int b = col & 0xff;
            int result = (alpha << 24) | r | g | b;
            pixels[y * w + x] = result;
        }
    }

    public int getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        return pixels[y * w + x];
    }

    public void blit(Bitmap bitmap, int x, int y) {
        Rect blitArea = new Rect(x, y, x + bitmap.w, y + bitmap.h);
        adjustBlitArea(blitArea);
        int blitWidth = blitArea.x2 - blitArea.x1;
        for (int yy = blitArea.y1; yy < blitArea.y2; yy++) {
            int targetPixel = yy * w + blitArea.x1;
            int sourcePixel = (yy - y) * bitmap.w + (blitArea.x1 - x);
            targetPixel -= sourcePixel;
            for (int xx = sourcePixel; xx < sourcePixel + blitWidth; xx++) {
                int col = bitmap.pixels[xx];
                int alpha = (col >> 24) & 0xff;
                if (alpha == 0xff) {
                    pixels[targetPixel + xx] = col;
                } else {
                    int bgCol = pixels[targetPixel + xx];
                    pixels[targetPixel + xx] = PixelUtils.blendColors(bgCol, col);
                }
            }
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }

    private Rect adjustBlitArea(Rect blitArea) {
        if (blitArea.x1 < 0) blitArea.x1 = 0;
        if (blitArea.y1 < 0) blitArea.y1 = 0;
        if (blitArea.x2 > w) blitArea.x2 = w;
        if (blitArea.y2 > h) blitArea.y2 = h;
        return blitArea;
    }

    public static Bitmap load(String filename) {
        Bitmap bitmap;
        try {
            BufferedImage bi = ImageIO.read(new File(filename));
            int biWidth = bi.getWidth();
            int biHeight = bi.getHeight();
            bitmap = new Bitmap(biWidth, biHeight);
            bi.getRGB(0, 0, biWidth, biHeight, bitmap.pixels, 0, biWidth);
        } catch (IOException e) {
            return null;
        }
        return bitmap;
    }

    public static Bitmap loadTile(String filename, int tileX, int tileY, int tileW, int tileH) {
        try {
            BufferedImage bi = ImageIO.read(new File(filename));
            Bitmap bitmap = new Bitmap(tileW, tileH);
            bi.getRGB(tileX * tileW, tileY * tileH, tileW, tileH, bitmap.pixels, 0, tileW);
            return bitmap;
        } catch (IOException e) {
            return null;
        }
    }

    public static Bitmap[][] loadTiles(String filename, int tileX, int tileY, int tileW, int tileH) {
        try {
            BufferedImage bi = ImageIO.read(new File(filename));
            Bitmap[][] bitmaps = new Bitmap[bi.getHeight() / tileH][];
            for (int y = 0; y < bi.getHeight() / tileH; y++) {
                bitmaps[y] = new Bitmap[bi.getWidth() / tileW];
                for (int x = 0; x < bi.getWidth() / tileW; x++) {
                    Bitmap bitmap = new Bitmap(tileW, tileH);
                    bi.getRGB(x + (tileX * tileW), y + (tileY * tileH), tileW, tileH, bitmap.pixels, 0, tileW);
                    bitmaps[y][x] = bitmap;
                }
            }
            return bitmaps;
        } catch (IOException e) {
            return null;
        }
    }

}
