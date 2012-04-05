package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.anim.FixedDurationAnimation;

import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject {
    private int xRenderOffs = Assets.Bitmaps.player[0][0].getWidth() / 2;
    private int yRenderOffs = Assets.Bitmaps.player[0][0].getHeight() / 2;
//    private static final int yOffs = 24;

    private static final Map<Facing, Integer> FACINGS = new HashMap<>();

    static {
        FACINGS.put(Facing.NW, 3);
        FACINGS.put(Facing.N, 4);
        FACINGS.put(Facing.NE, 5);
        FACINGS.put(Facing.E, 6);
        FACINGS.put(Facing.SE, 7);
        FACINGS.put(Facing.S, 0);
        FACINGS.put(Facing.SW, 1);
        FACINGS.put(Facing.W, 2);
    }

    public Player() {
        super(null,
                new FixedDurationAnimation(100, 6),
                new Rectangle(10, 20, 23, 32),
                FACINGS);
    }

    @Override
    public void render(Screen screen, float interpolation) {
        Bitmap image = Assets.Bitmaps.player[facings.get(currentFacing)][animation.nextFrameIndex(this)];
        screen.blit(image,
                (int) pos.x - image.getWidth() / 2,
                (int) pos.y - image.getHeight() / 2);
    }

    public int getRenderPosX() {
        return (int) pos.x - xRenderOffs;
    }

    public int getRenderPosY() {
        return (int) pos.y - yRenderOffs;
    }
}
