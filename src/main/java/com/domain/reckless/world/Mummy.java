package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.AI;

public class Mummy extends Enemy {
    public Mummy(AI ai) {
        super(ai, Assets.Bitmaps.mummy);
    }
}
