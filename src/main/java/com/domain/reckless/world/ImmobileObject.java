package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.res.Assets;

public class ImmobileObject extends GameObject {
    public Bitmap[][] tiles = Assets.Bitmaps.wallTiles;
    public int xTile;

    public ImmobileObject() {
        this(0);
    }

    public ImmobileObject(int xTile) {
        super(null, null, new Rectangle(0, 10, 32, 45), null);
        this.xTile = xTile;
    }

    @Override
    public void render(Screen screen, float interpolation) {
        Bitmap image = tiles[0][xTile];
        screen.blit(image,
                (int) pos.x - image.getWidth() / 2,
                (int) pos.y - image.getHeight() / 2);
    }
}
