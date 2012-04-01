package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;

public class Player extends GameObject {
    @Override
    public void render(Screen screen) {
        screen.blit(Assets.Bitmaps.player[0][0], (int) pos.x, (int) pos.y);
    }

    @Override
    public void update() {
        pos.x += sign() * delta.x;
        pos.y += sign() * delta.y;
    }

    private double sign() {
         return (Math.random() * 2) - 1 >= 0 ? 1 : -1;
    }
}
