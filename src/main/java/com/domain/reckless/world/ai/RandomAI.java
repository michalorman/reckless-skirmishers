package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

// Moves randomly
public class RandomAI implements AI {
    @Override
    public void nextMove(GameObject object, GameContext context) {
        object.pos.x += sign() * object.delta.x;
        object.pos.y += sign() * object.delta.y;
    }

    private int sign() {
        return Math.random() * 2 - 1 > 0 ? 1 : -1;
    }
}
