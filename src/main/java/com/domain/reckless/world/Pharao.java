package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;

public class Pharao extends Enemy {
    @Override
    public void render(Screen screen) {
        screen.blit(Assets.Bitmaps.pharao[3][0], (int) pos.x, (int) pos.y);
    }
}
