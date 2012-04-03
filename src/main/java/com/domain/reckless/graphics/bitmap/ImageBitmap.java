package com.domain.reckless.graphics.bitmap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class ImageBitmap extends Bitmap {
    protected final BufferedImage image;

    public ImageBitmap(int w, int h) {
        super(w, h, false);
        image = getCompatibleImage(w, h);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public Image getImage() {
        return image;
    }

    private static BufferedImage getCompatibleImage(int w, int h) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration config = env.getDefaultScreenDevice().getDefaultConfiguration();
        return config.createCompatibleImage(w, h);
    }
}
