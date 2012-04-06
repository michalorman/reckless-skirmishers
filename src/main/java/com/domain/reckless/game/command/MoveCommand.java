package com.domain.reckless.game.command;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.GameObject;

/**
 * Enumeration of commands which commands game object to move
 * certain direction.
 */
public enum MoveCommand implements GameCommand {
    MOVE_N(0, -1, GameObject.Facing.N),
    MOVE_NE(1, -1, GameObject.Facing.NE),
    MOVE_E(1, 0, GameObject.Facing.E),
    MOVE_SE(1, 1, GameObject.Facing.SE),
    MOVE_S(0, 1, GameObject.Facing.S),
    MOVE_SW(-1, 1, GameObject.Facing.SW),
    MOVE_W(-1, 0, GameObject.Facing.W),
    MOVE_NW(-1, -1, GameObject.Facing.NW);

    private int dx, dy;
    private boolean isDiagonal;
    private GameObject.Facing facing;

    private MoveCommand(int dx, int dy, GameObject.Facing facing) {
        this.dx = dx;
        this.dy = dy;
        isDiagonal = dx != 0 && dy != 0;
        this.facing = facing;
    }

    @Override
    public void execute(GameContext context, GameObject object) {
        double xDelta = dx * object.delta.x;
        double yDelta = dy * object.delta.y;

        if (isDiagonal) {
            double deltaMean = (Math.abs(xDelta) + Math.abs(yDelta)) / 2;
            xDelta = dx * deltaMean * Math.sqrt(2) / 2;
            yDelta = dy * deltaMean * Math.sqrt(2) / 2;
        }

        object.pos.x += xDelta;
        object.pos.y += yDelta;

        object.setCurrentFacing(facing);
        object.animate();

        for (Collidable collidable : context.getCollidableObjects()) {
            if (object != collidable) {
                if (object.collides(collidable)) {
                    object.pos.x -= xDelta;
                    object.pos.y -= yDelta;
                }
            }
        }
    }
}
