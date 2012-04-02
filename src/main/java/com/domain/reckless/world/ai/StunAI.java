package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

public class StunAI extends TemporalAI implements AI {

    public StunAI(AI currentAI, long duration) {
        super(currentAI, duration);
    }

    @Override
    protected void doNextMove(GameObject object, GameContext context) {
        // do nothing while stunned
    }
}
