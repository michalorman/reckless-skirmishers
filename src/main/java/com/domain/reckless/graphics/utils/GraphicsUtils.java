package com.domain.reckless.graphics.utils;

import com.domain.reckless.graphics.RGB;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsUtils {

    //Food-for-thought: http://en.wikipedia.org/wiki/Alpha_compositing
    public static int blendColors(int firstPixel, int secondPixel) {
        RGB firstRGB = new RGB(firstPixel);
        RGB secondRGB = new RGB(secondPixel);
        int firstAlpha = 256 - secondRGB.alpha;
        int r = ((secondRGB.r * secondRGB.alpha + firstRGB.r * firstAlpha) >> 8) & 0xff0000;
        int g = ((secondRGB.g * secondRGB.alpha + firstRGB.g * firstAlpha) >> 8) & 0xff00;
        int b = ((secondRGB.b * secondRGB.alpha + firstRGB.b * firstAlpha) >> 8) & 0xff;
        return 0xff000000 | r | g | b;
    }

    public static BufferedImage createCompatibleBufferedImage(int w, int h) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration config = env.getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage newImage = config.createCompatibleImage(w, h, config.getColorModel().getTransparency());
        return newImage;
    }

    public static BufferedImage toCompatibleBufferedImage(BufferedImage bufferedImage) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration config = env.getDefaultScreenDevice().getDefaultConfiguration();
        if (bufferedImage.getColorModel().equals(config.getColorModel())) {
            return bufferedImage;
        }
        BufferedImage newImage = config.createCompatibleImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getTransparency());
        Graphics g = newImage.getGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        return newImage;
    }

}
