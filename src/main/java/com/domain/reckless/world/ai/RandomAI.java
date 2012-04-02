package com.domain.reckless.world.ai;

import com.domain.reckless.world.Enemy;

// Moves randomly
public class RandomAI implements AI {
    @Override
    public void move(Enemy enemy) {
        enemy.pos.x += sign() * enemy.delta.x;
        enemy.pos.y += sign() * enemy.delta.y;
    }

    private int sign() {
        return Math.random() * 2 - 1 > 0 ? 1 : -1;
    }
}
