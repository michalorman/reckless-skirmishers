package com.domain.reckless.world.level.loader;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.level.Level;
import com.domain.reckless.world.level.Tile;

import java.util.HashMap;
import java.util.Map;

public class BitmapLevelLoader implements LevelLoader {

    private static Map<Integer, Tile> tileMapper = new HashMap<>();

    static {
        tileMapper.put(0xffffffff, new Tile(Assets.Bitmaps.floorTiles[0][5]));
        tileMapper.put(0xff00ff00, new Tile(Assets.Bitmaps.floorTiles[0][0]));
    }

    @Override
    public Level load(String resourcePath) {
        Bitmap map = Bitmap.load(resourcePath);
        Level level = new Level(map.w, map.h);
        for (int y = 0; y < map.h; y++) {
            for (int x = 0; x < map.w; x++) {
                int pixel = map.getPixel(x, y);
                placeTile(level, x, y, pixel);
            }
        }
        level.createBitmap();
        return level;
    }

    private void placeTile(Level level, int x, int y, int pixel) {
        Tile tile = tileMapper.get(pixel);
        if (tile == null) {
            tile = new Tile(Assets.Bitmaps.floorTiles[0][5]);
        }
        level.setTile(x, y, tile);
    }
}
