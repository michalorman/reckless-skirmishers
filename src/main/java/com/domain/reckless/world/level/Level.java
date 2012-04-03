package com.domain.reckless.world.level;

import com.domain.reckless.graphics.bitmap.Bitmap;

public class Level {

    public static final int SMALL = 32;

    public Tile[] tiles;

    public Bitmap bitmap;

    public int width;

    public int height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;

        tiles = new Tile[width * height];
    }

    public void createBitmap() {
        bitmap = new Bitmap(width * tiles[0].bitmap.w, height * tiles[0].bitmap.h);
        for (int i = 0; i < tiles.length; i++) {
            Tile tile = tiles[i];
            int x = i % width;
            int y = i / width;
            bitmap.blit(tile.bitmap, x * tile.bitmap.w, y * tile.bitmap.h);
        }
    }
}
