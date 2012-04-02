package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.world.ai.AI;

public class Enemy extends GameObject {
    private Bitmap[][] bitmap;

    public Enemy(AI ai, Bitmap[][] bitmap) {
        super(ai);
        this.bitmap = bitmap;
        facing = 3;
    }

    @Override
    public void render(Screen screen) {
        screen.blit(bitmap[facing][0], (int) pos.x, (int) pos.y);
    }
}
