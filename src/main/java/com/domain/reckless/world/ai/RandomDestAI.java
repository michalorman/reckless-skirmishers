package com.domain.reckless.world.ai;

import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.Enemy;

// Picks random point and moves towards this point. If point
// is reached picks another point.
public class RandomDestAI implements AI {
    @Override
    public void move(Enemy enemy) {
        if (enemy.dest == null) {
            // pick new destination
            enemy.dest = new Vect2D(Math.random() * 500, Math.random() * 380);
        }

        // move towards destination
        if (Math.abs(enemy.dest.x - enemy.pos.x) > enemy.delta.x &&
                Math.abs(enemy.dest.y - enemy.pos.y) > enemy.delta.y) {
            // TODO: add better movement
            if (enemy.pos.x < enemy.dest.x)
                enemy.pos.x += enemy.delta.x;
            if (enemy.pos.x > enemy.dest.x)
                enemy.pos.x -= enemy.delta.x;
            if (enemy.pos.y < enemy.dest.y)
                enemy.pos.y += enemy.delta.y;
            if (enemy.pos.y > enemy.dest.y)
                enemy.pos.y -= enemy.delta.y;
        } else {
            enemy.dest = null;
        }
    }
}
