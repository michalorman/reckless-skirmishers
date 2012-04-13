package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.res.Assets;

public class ImmobileObject extends GameObject {
    public int xTile;

    public ImmobileObject() {
        this(0);
    }

    public ImmobileObject(int xTile) {
        super(GameObjectSpec.IMMOBILE_OBJECT);
        this.xTile = xTile;
    }

    @Override
    public void render(Screen screen) {
        Bitmap image = sheet[0][xTile];
        screen.blit(image,
                (int) pos.x - image.getWidth() / 2,
                (int) pos.y - image.getHeight() / 2);
    }

    @Override
    public int compareTo(GameObject o) {
        if (pos.y < o.pos.y - o.sheet[0][0].getHeight() / 2)
            return -1;
        return 1;
    }
}
