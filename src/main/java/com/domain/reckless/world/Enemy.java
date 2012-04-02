package com.domain.reckless.world;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.ai.AI;

public abstract class Enemy extends GameObject {
    public AI ai;
    public Vect2D dest;

    protected Enemy(AI ai) {
        this.ai = ai;
    }

    @Override
    public void update(Keyboard keyboard) {
        ai.move(this);
    }
}
