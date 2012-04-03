package com.domain.reckless.res;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.font.BitmapFont;

public interface Assets {
    interface Bitmaps {
        Bitmap background = Bitmap.load("/assets/images/background.png");

        Bitmap player[][] = Bitmap.loadTiles("/assets/images/player.png", 192 / 6, 512 / 16);
        Bitmap pharao[][] = Bitmap.loadTiles("/assets/images/pharao.png", 192 / 4, 192 / 4);
        Bitmap mummy[][] = Bitmap.loadTiles("/assets/images/mummy.png", 192 / 4, 192 / 4);
        Bitmap snake[][] = Bitmap.loadTiles("/assets/images/snake.png", 192 / 4, 192 / 4);

        Bitmap floorTiles[][] = Bitmap.loadTiles("/assets/images/floortiles.png", 32, 32);
        Bitmap wallTiles[][] = Bitmap.loadTiles("/assets/images/walltiles.png", 736 / 23, 56);
    }

    interface Fonts {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ___/0123456789-.!?/%$\\=*+,;:()&#\"'";

        BitmapFont font = BitmapFont.load("/assets/fonts/font_gold.png", LETTERS, 8, 8);
    }
}
