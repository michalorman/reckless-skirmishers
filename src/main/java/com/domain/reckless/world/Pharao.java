package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.AI;

public class Pharao extends Enemy {
    public Pharao(AI ai) {
        super(ai);
    }

    @Override
    public void render(Screen screen) {
        screen.blit(Assets.Bitmaps.pharao[3][0], (int) pos.x, (int) pos.y);
    }
}
