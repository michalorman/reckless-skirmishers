package com.domain.reckless.world.ai;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.GameObject;

public abstract class AbstractAI<T extends GameObject> implements AI<T> {
    @Override
    public void nextMove(T object, GameContext context, Keyboard keyboard) {

        Vect2D delta = doNextMove(object, context, keyboard);

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

        for (Collidable collidable : context.getCollidableObjects()) {
            if (object != collidable) {
                if (object.collides(collidable)) {
                    object.pos.x -= delta.x;
                    object.pos.y -= delta.y;
                    break;
                }
            }
        }
    }

    /**
     * Delegates exact movement to subclass. Subclass instead of modifying object's position
     * directly should should return deltas for current object position. This deltas are examined
     * to check for diagonal movements and adjusted accordingly in this case.
     *
     *
     * @param object  The object being moved.
     * @param context The game context.
     * @param keyboard
     * @return Delta to current object position.
     */
    protected abstract Vect2D doNextMove(T object, GameContext context, Keyboard keyboard);
}
