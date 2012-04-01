package com.domain.reckless.res;

import com.domain.reckless.graphics.bitmap.Bitmap;

public interface Assets {
    interface Bitmaps {
        Bitmap background = Bitmap.load("/assets/images/background.png");
        Bitmap player[][] = Bitmap.loadTiles("/assets/images/player.png", 192/6, 512/16);
    }
}
