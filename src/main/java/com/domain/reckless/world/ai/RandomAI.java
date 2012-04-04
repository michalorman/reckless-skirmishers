package com.domain.reckless.world.ai;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;

// Moves randomly
public class RandomAI extends AbstractAI {
    @Override
    protected Vect2D doNextMove(GameObject object, GameContext context, Keyboard keyboard) {
        Vect2D delta = new Vect2D();
        delta.x = sign() * object.delta.x;
        delta.y = sign() * object.delta.y;
        object.animation.setAnimating(true);
        return delta;
    }

    private int sign() {
        return Math.random() * 2 - 1 > 0 ? 1 : -1;
    }
}
