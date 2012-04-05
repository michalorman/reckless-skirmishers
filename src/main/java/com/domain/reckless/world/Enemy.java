package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.world.ai.AI;
import com.domain.reckless.world.anim.Animation;

public class Enemy extends GameObject {
    private Bitmap[][] bitmap;
//    private static final int yOffs = 8;

    public Enemy(AI ai, Bitmap[][] bitmap, Animation animation, Rectangle boundingBox) {
        super(ai, animation, boundingBox);
        this.bitmap = bitmap;
        facing = 3;
    }

    @Override
    public void render(Screen screen, float interpolation) {
        Bitmap image = bitmap[facing][animation.nextFrameIndex(this)];
        screen.blit(image,
                (int) pos.x - image.getWidth() / 2,
                (int) pos.y - image.getHeight() / 2);
    }
}
