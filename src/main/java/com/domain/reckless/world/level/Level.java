package com.domain.reckless.world.level;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.world.GameObject;

import java.util.ArrayList;
import java.util.Collection;

public class Level {

    public static final int SMALL = 64;

    public Tile[] tiles;

    public Bitmap bitmap;

    public int width;

    public int height;

    private Collection<GameObject> objects = new ArrayList<>();

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

    public Bitmap getBitmap(int x, int y, int width, int height) {
        return bitmap.cut(x, y, width, height);
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[y * width + x] = tile;
    }

    public Collection<? extends GameObject> getGameObjects() {
        return objects;
    }

    public void addGameObject(GameObject object) {
        objects.add(object);
    }
}
