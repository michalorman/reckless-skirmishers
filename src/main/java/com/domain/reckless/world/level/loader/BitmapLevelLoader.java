package com.domain.reckless.world.level.loader;

import com.domain.reckless.graphics.FrameContext;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.GameObject;
import com.domain.reckless.world.ImmobileObject;
import com.domain.reckless.world.level.Level;
import com.domain.reckless.world.level.Tile;

import java.util.HashMap;
import java.util.Map;

public class BitmapLevelLoader implements LevelLoader {

    private static Map<Integer, Tile> tilesMapping = new HashMap<>();
    private FrameContext frameContext;

    public BitmapLevelLoader(FrameContext frameContext) {
        this.frameContext = frameContext;
    }

    static {
        tilesMapping.put(0xffffffff, new Tile(Assets.Bitmaps.floorTiles[0][5]));
        tilesMapping.put(0xff00ff00, new Tile(Assets.Bitmaps.floorTiles[0][0]));
    }

    @Override
    public Level load(String resourcePath) {
        Bitmap map = Bitmap.load(resourcePath);
        Level level = new Level(map.w, map.h);
        for (int y = 0; y < map.h; y++) {
            for (int x = 0; x < map.w; x++) {
                int pixel = map.getPixel(x, y);
                handlePixel(level, x, y, pixel);
            }
        }
        level.createBitmap();
        return level;
    }

    private void handlePixel(Level level, int x, int y, int pixel) {
        if (tilesMapping.containsKey(pixel)) {
            Tile tile = tilesMapping.get(pixel);
            level.setTile(x, y, tile);
        } else {
            if (pixel == 0xffff0000) {
                ImmobileObject object = new ImmobileObject();
                object.pos = new Vect2D(x * 32 - frameContext.getScreenWidth() / 2,
                        y * 32 - 12 - frameContext.getScreenHeight() / 2);
                level.addGameObject(object);
            }
            level.setTile(x, y, new Tile(Assets.Bitmaps.floorTiles[0][5]));
        }
    }
}
