package com.domain.reckless.graphics.bitmap;

import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.graphics.font.BitmapFont;
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
            pixels[y * w + x] = 0xff000000 | col;
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
        Rectangle blitArea = new Rectangle(x, y, x + bitmap.w, y + bitmap.h);
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

    public Bitmap cut(int x, int y, int w, int h) {
        Rectangle toBeCutArea = new Rectangle(x, y, x + w, y + h);
        Rectangle wholeArea = new Rectangle(0, 0, this.w, this.h);
        Rectangle cutArea = wholeArea.intersection(toBeCutArea);
        Bitmap bitmap = new Bitmap(cutArea.width, cutArea.height);
        for (int yy = y; yy < y + cutArea.height; yy++) {
            System.arraycopy(pixels, yy * this.w + cutArea.x, bitmap.pixels, (yy - y) * bitmap.w, cutArea.width);
        }
        return bitmap;
    }

    //TODO move Font.write() body to this method?
    public void write(BitmapFont font, int x, int y, String message) {
        font.write(this, x, y, message);
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }

    private Rectangle adjustBlitArea(Rectangle blitArea) {
        if (blitArea.x1 < 0) blitArea.x1 = 0;
        if (blitArea.y1 < 0) blitArea.y1 = 0;
        if (blitArea.x2 > w) blitArea.x2 = w;
        if (blitArea.y2 > h) blitArea.y2 = h;
        return blitArea;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public static Bitmap load(String filename) {
        try {
            BufferedImage bi = readFile(filename);
            int biWidth = bi.getWidth();
            int biHeight = bi.getHeight();
            Bitmap bitmap = new Bitmap(biWidth, biHeight);
            bi.getRGB(0, 0, biWidth, biHeight, bitmap.pixels, 0, biWidth);
            return bitmap;
        } catch (IOException e) {
            return null;
        }
    }

    private static BufferedImage readFile(String filename) throws IOException {
        return ImageIO.read(String.class.getResourceAsStream(filename));
    }

    public static Bitmap loadTile(String filename, int tileX, int tileY, int tileW, int tileH) {
        try {
            BufferedImage bi = readFile(filename);
            Bitmap bitmap = new Bitmap(tileW, tileH);
            bi.getRGB(tileX * tileW, tileY * tileH, tileW, tileH, bitmap.pixels, 0, tileW);
            return bitmap;
        } catch (IOException e) {
            return null;
        }
    }

    public static Bitmap[][] loadTiles(String filename, int tileW, int tileH) {
        Bitmap bitmap = load(filename);
        if (bitmap != null) {
            Bitmap[][] bitmaps = new Bitmap[bitmap.h / tileH][];
            int cols = bitmap.w / tileW;
            for (int y = 0; y < bitmap.h / tileH; y++) {
                bitmaps[y] = new Bitmap[cols];
                for (int x = 0; x < cols; x++) {
                    Bitmap tile = bitmap.cut(x * tileW, y * tileH, tileW, tileH);
                    bitmaps[y][x] = tile;
                }
            }
            return bitmaps;
        }
        return null;
    }

}
