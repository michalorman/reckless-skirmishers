package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.KeybordInputAI;
import com.domain.reckless.world.anim.FixedDurationAnimation;

public class Player extends GameObject {
    public Player() {
        super(new KeybordInputAI(), new FixedDurationAnimation(100, 6));
    }

    @Override
    public void render(Screen screen) {
        screen.blit(Assets.Bitmaps.player[facing][animation.nextFrameIndex(this)], (int) pos.x, (int) pos.y);
    }
}
