package com.domain.reckless.graphics.bitmap;

import com.domain.reckless.graphics.RGB;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.graphics.font.BitmapFont;
import com.domain.reckless.graphics.utils.PixelUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bitmap {
    public int w, h;
    public int pixels[];

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
        adjustArea(blitArea);
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

    public void alphaBlit(Bitmap bitmap, int x, int y, int alpha) {
        Rectangle blitArea = new Rectangle(x, y, x + bitmap.w, y + bitmap.h);
        adjustArea(blitArea);
        int blitWidth = blitArea.x2 - blitArea.x1;
        for (int yy = blitArea.y1; yy < blitArea.y2; yy++) {
            int targetPixel = yy * w + blitArea.x1;
            int sourcePixel = (yy - y) * bitmap.w + (blitArea.x1 - x);
            targetPixel -= sourcePixel;
            for (int xx = sourcePixel; xx < sourcePixel + blitWidth; xx++) {
                RGB rgb = new RGB(bitmap.pixels[xx]);
                if (rgb.alpha > 0) {
                    int col = (alpha << 24) | rgb.r | rgb.g | rgb.b;
                    int bgCol = pixels[targetPixel + xx];
                    pixels[targetPixel + xx] = PixelUtils.blendColors(bgCol, col);
                }
            }
        }
    }

    //TODO Fix blending colors.
    public void colorBlit(Bitmap bitmap, int x, int y, int color) {
        Rectangle blitArea = new Rectangle(x, y, x + bitmap.w, y + bitmap.h);
        adjustArea(blitArea);
        int blitWidth = blitArea.x2 - blitArea.x1;
        for (int yy = blitArea.y1; yy < blitArea.y2; yy++) {
            int targetPixel = yy * w + blitArea.x1;
            int sourcePixel = (yy - y) * bitmap.w + (blitArea.x1 - x);
            targetPixel -= sourcePixel;
            for (int xx = sourcePixel; xx < sourcePixel + blitWidth; xx++) {
                int col = bitmap.pixels[xx];
                if (col < 0) {//alpha > 0
                    pixels[targetPixel + xx] = PixelUtils.blendColors(col, color);
                }
            }
        }
    }

    //TODO check performance, check bounds.
    public Bitmap cut(int x, int y, int w, int h) {
        Rectangle toBeCutArea = new Rectangle(x, y, x + w, y + h);
        Rectangle wholeArea = new Rectangle(0, 0, this.w, this.h);
        Rectangle cutArea = wholeArea.intersection(toBeCutArea);
        Bitmap bitmap = new Bitmap(cutArea.width, cutArea.height);
        if (y < 0) {
            y = 0;
        }
        for (int yy = 0; yy < cutArea.height; yy++) {
            System.arraycopy(pixels, (y + yy) * this.w + cutArea.x, bitmap.pixels, yy * bitmap.w, cutArea.width);
        }
        return bitmap;
    }

    public void rectFill(int x1, int y1, int x2, int y2, int color) {
        for (int xx = x1; xx < x2; xx++) {
            for (int yy = y1; yy < y2; yy++) {
                if (color < 0) {
                    int alpha = (color >> 24) & 0xff;
                    putPixel(xx, yy, color, alpha);
                } else {
                    putPixel(xx, yy, color);
                }
            }
        }
    }

    //TODO move Font.write() body to this method?
    public void write(BitmapFont font, int x, int y, String message) {
        font.write(this, x, y, message);
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }

    private Rectangle adjustArea(Rectangle area) {
        if (area.x1 < 0) area.x1 = 0;
        if (area.y1 < 0) area.y1 = 0;
        if (area.x2 > w) area.x2 = w;
        if (area.y2 > h) area.y2 = h;
        return area;
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
