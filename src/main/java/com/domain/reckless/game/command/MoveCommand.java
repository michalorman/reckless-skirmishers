package com.domain.reckless.game.command;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.GameObject;

public enum MoveCommand implements GameCommand {
    MOVE_N(0, -1),
    MOVE_NE(1, -1),
    MOVE_E(1, 0),
    MOVE_SE(1, 1),
    MOVE_S(0, 1),
    MOVE_SW(-1, 1),
    MOVE_W(-1, 0),
    MOVE_NW(-1, -1);

    int dx, dy;

    private MoveCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute(GameContext context, GameObject object) {
        object.pos.x += dx * object.delta.x;
        object.pos.y += dy * object.delta.y;

        for (Collidable collidable : context.getCollidableObjects()) {
            if (object != collidable) {
                if (object.collides(collidable)) {
                    object.pos.x -= dx * object.delta.x;
                    object.pos.y -= dy * object.delta.y;
                }
            }
        }
    }
}
