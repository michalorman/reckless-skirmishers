package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.world.ai.AI;
import com.domain.reckless.world.anim.Animation;

import java.util.HashMap;
import java.util.Map;

public class Enemy extends GameObject {
    private Bitmap[][] bitmap;
//    private static final int yOffs = 8;

    private static final Map<Facing, Integer> FACINGS = new HashMap<>();

        static {
            FACINGS.put(Facing.NW, 1);
            FACINGS.put(Facing.N, 1);
            FACINGS.put(Facing.NE, 1);
            FACINGS.put(Facing.E, 2);
            FACINGS.put(Facing.SE, 3);
            FACINGS.put(Facing.S, 3);
            FACINGS.put(Facing.SW, 3);
            FACINGS.put(Facing.W, 0);
        }

    public Enemy(AI ai, Bitmap[][] bitmap, Animation animation, Rectangle boundingBox) {
        super(ai, animation, boundingBox, FACINGS);
        this.bitmap = bitmap;
    }

    @Override
    public void render(Screen screen, float interpolation) {
        Bitmap image = bitmap[facings.get(currentFacing)][animation.nextFrameIndex(this)];
        screen.blit(image,
                (int) pos.x - image.getWidth() / 2,
                (int) pos.y - image.getHeight() / 2);
    }
}
