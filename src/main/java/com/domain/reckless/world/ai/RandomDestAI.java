package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;

// Picks random point and moves towards this point. If point
// is reached picks another point.
public class RandomDestAI extends AbstractAI {

    @Override
    protected Vect2D doNextMove(GameObject object, GameContext context) {
        Vect2D delta = new Vect2D();

        if (object.dest == null) {
            // pick new destination
            object.dest = new Vect2D(Math.random() * 500, Math.random() * 380);
        }

        boolean xc = false;
        boolean yc = false;

        // move towards destination
        if (Math.abs(object.dest.x - object.pos.x) > object.delta.x ||
                Math.abs(object.dest.y - object.pos.y) > object.delta.y) {
            // TODO: add better movement
            if (object.pos.x < object.dest.x - object.delta.x) {
                delta.x = object.delta.x;
                object.facing = 2;
                xc = true;
            } else if (object.pos.x > object.dest.x + object.delta.x) {
                delta.x = -object.delta.x;
                object.facing = 0;
                xc = true;
            }
            if (object.pos.y < object.dest.y - object.delta.y) {
                delta.y = object.delta.y;
                object.facing = 3;
                yc = true;
            } else if (object.pos.y > object.dest.y + object.delta.x) {
                delta.y = -object.delta.y;
                object.facing = 1;
                yc = true;
            }
            if (!xc && !yc) {
                object.dest = null;
            }
        } else {
            object.dest = null;
        }

        return delta;
    }
}
