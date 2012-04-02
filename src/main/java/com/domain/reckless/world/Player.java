package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.KeybordInputAI;

public class Player extends GameObject {
    public Player() {
        super(new KeybordInputAI());
    }

    @Override
    public void render(Screen screen) {
        screen.blit(Assets.Bitmaps.player[facing][0], (int) pos.x, (int) pos.y);
    }
}
