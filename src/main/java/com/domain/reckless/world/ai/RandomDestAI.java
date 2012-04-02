package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;

// Picks random point and moves towards this point. If point
// is reached picks another point.
public class RandomDestAI implements AI {
    @Override
    public void nextMove(GameObject object, GameContext context) {
        if (object.dest == null) {
            // pick new destination
            object.dest = new Vect2D(Math.random() * 500, Math.random() * 380);
        }

        // move towards destination
        if (Math.abs(object.dest.x - object.pos.x) > object.delta.x ||
                Math.abs(object.dest.y - object.pos.y) > object.delta.y) {
            // TODO: add better movement
            if (object.pos.x < object.dest.x - object.delta.x) {
                object.pos.x += object.delta.x;
                object.facing = 2;
            } else if (object.pos.x > object.dest.x + object.delta.x) {
                object.pos.x -= object.delta.x;
                object.facing = 0;
            }
            if (object.pos.y < object.dest.y - object.delta.y) {
                object.pos.y += object.delta.y;
                object.facing = 3;
            } else if (object.pos.y > object.dest.y + object.delta.x) {
                object.pos.y -= object.delta.y;
                object.facing = 1;
            }
        } else {
            object.dest = null;
        }
    }
}
