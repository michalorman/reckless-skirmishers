package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

/**
 * Base class for {@link AI} that last for specified duration. After duration
 * expires returns to current AI.
 */
public abstract class TemporalAI<T extends GameObject> implements AI<T> {
    public AI currentAI;
    public long expiresAt;

    public TemporalAI(AI currentAI, long duration) {
        this.currentAI = currentAI;
        this.expiresAt = System.currentTimeMillis() + duration;
    }

    @Override
    public void nextMove(T object, GameContext context) {
        long now = System.currentTimeMillis();
        if (now < expiresAt) {
            doNextMove(object, context);
        } else {
            object.ai = currentAI;
        }
    }

    protected abstract void doNextMove(T object, GameContext context);
}
