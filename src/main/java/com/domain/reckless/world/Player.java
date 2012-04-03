package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.KeybordInputAI;
import com.domain.reckless.world.anim.FixedDurationAnimation;

public class Player extends GameObject {
    private int xRenderOffs = Assets.Bitmaps.player[0][0].getWidth() / 2;
    private int yRenderOffs = Assets.Bitmaps.player[0][0].getHeight() / 2;
//    private static final int yOffs = 24;

    public Player() {
        super(new KeybordInputAI(),
                new FixedDurationAnimation(100, 6),
                new Rectangle(10, 20, 23, 32));
    }

    @Override
    public void render(Screen screen) {
        Bitmap image = Assets.Bitmaps.player[facing][animation.nextFrameIndex(this)];
        screen.blit(image,
                (int) pos.x - image.getWidth() / 2,
                (int) pos.y - image.getHeight() / 2);
    }

    public int getRenderPosX() {
        return (int) pos.x + xRenderOffs;
    }

    public int getRenderPosY() {
        return (int) pos.y + yRenderOffs;
    }
}
