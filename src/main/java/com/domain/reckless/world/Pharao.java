package com.domain.reckless.world;

import com.domain.reckless.res.Assets;
import com.domain.reckless.world.ai.AI;

public class Pharao extends Enemy {
    public Pharao(AI ai) {
        super(ai, Assets.Bitmaps.pharao);
    }
}
