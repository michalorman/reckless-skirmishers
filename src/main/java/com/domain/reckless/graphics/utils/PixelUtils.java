package com.domain.reckless.graphics.utils;

import com.domain.reckless.graphics.RGB;

public class PixelUtils {

    //Food-for-thought: http://en.wikipedia.org/wiki/Alpha_compositing
    public static int blendColors(int backgroundColor, int colorToBlend) {
        RGB bgRGB = new RGB(backgroundColor);
        RGB pixelRGB = new RGB(colorToBlend);
        int bgAlpha = 256 - pixelRGB.alpha;
        int r = ((pixelRGB.r * pixelRGB.alpha + bgRGB.r * bgAlpha) >> 8) & 0xff0000;
        int g = ((pixelRGB.g * pixelRGB.alpha + bgRGB.g * bgAlpha) >> 8) & 0xff00;
        int b = ((pixelRGB.b * pixelRGB.alpha + bgRGB.b * bgAlpha) >> 8) & 0xff;
        return 0xff000000 | r | g | b;
    }

}
