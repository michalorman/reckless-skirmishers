package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;

// Moves randomly
public class RandomAI extends AbstractAI {
    @Override
    protected Vect2D doNextMove(GameObject object, GameContext context) {
        Vect2D delta = new Vect2D();
        delta.x = sign() * object.delta.x;
        delta.y = sign() * object.delta.y;
        return delta;
    }

    private int sign() {
        return Math.random() * 2 - 1 > 0 ? 1 : -1;
    }
}
