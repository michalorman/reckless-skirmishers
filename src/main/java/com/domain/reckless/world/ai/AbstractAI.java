package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;

public abstract class AbstractAI<T extends GameObject> implements AI<T> {
    @Override
    public void nextMove(T object, GameContext context) {
        Vect2D delta = doNextMove(object, context);

        if (delta.x != 0 && delta.y != 0) {
            // move diagonally
            double deltaX = Math.abs(delta.x);
            double deltaY = Math.abs(delta.y);
            double deltaMean = (deltaX + deltaY) / 2;
            deltaX = deltaMean * Math.sqrt(2) / 2;
            deltaY = deltaMean * Math.sqrt(2) / 2;
            delta.x = delta.x < 0 ? -deltaX : deltaX;
            delta.y = delta.y < 0 ? -deltaY : deltaY;
        }

        object.pos.x += delta.x;
        object.pos.y += delta.y;
    }

    protected abstract Vect2D doNextMove(T object, GameContext context);
}
