package com.domain.reckless.res;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.font.BitmapFont;

public interface    Assets {
    interface Bitmaps {
        Bitmap background = Bitmap.load("/assets/images/background.png");
        Bitmap player[][] = Bitmap.loadTiles("/assets/images/player.png", 192 / 6, 512 / 16);
    }

    interface Fonts {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ___/0123456789-.!?/%$\\=*+,;:()&#\"'";

        BitmapFont font = BitmapFont.load("/assets/fonts/font_gold.png", LETTERS, 8, 8);
    }
}
