package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

// Moves randomly for specified amount of millisecond. After
// confusion expires returns to current AI.
public class ConfudeAI extends TemporalAI implements AI {

    public AI confudeAI = new RandomAI();

    public ConfudeAI(AI currentAI, long duration) {
        super(currentAI, duration);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doNextMove(GameObject object, GameContext context) {
        confudeAI.nextMove(object, context);
    }
}
