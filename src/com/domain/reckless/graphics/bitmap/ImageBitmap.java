package com.domain.reckless.graphics.bitmap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class ImageBitmap extends MemoryBitmap {
    protected BufferedImage image;

    public ImageBitmap(int w, int h) {
        super(w, h);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public ImageBitmap(double w, double h) {
        this((int) w, (int) h);
    }

    public Image getImage() {
        return image;
    }
}
