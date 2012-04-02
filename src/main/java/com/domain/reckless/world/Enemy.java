package com.domain.reckless.world;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.AI;

public abstract class Enemy extends GameObject {
    public AI ai;
    public Vect2D dest;
    private Bitmap[][] bitmap;

    protected Enemy(AI ai, Bitmap[][] bitmap) {
        this.ai = ai;
        this.bitmap = bitmap;
        facing = 3;
    }

    @Override
    public void update(Keyboard keyboard) {
        ai.nextMove(this);
    }

    @Override
    public void render(Screen screen) {
        screen.blit(bitmap[facing][0], (int) pos.x, (int) pos.y);
    }
}
