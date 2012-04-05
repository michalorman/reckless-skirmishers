package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.game.command.MoveCommand;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;

public class SimpleAI implements AI {
    private Vect2D dest;
    private long idleTo;
    private int xLast;
    private int yLast;
    private int posIdleCounter;
    private static final int MAX_IDLE_COUTER = 20;

    @Override
    public void command(GameContext context, GameObject object) {
        if (xLast != (int) object.pos.x || yLast != (int) object.pos.y) {
            posIdleCounter = 0;
            xLast = (int) object.pos.x;
            yLast = (int) object.pos.y;
        } else {
            ++posIdleCounter;
            if (posIdleCounter >= MAX_IDLE_COUTER) {
                dest = null;
            }
        }

        if (dest == null) {
            dest = new Vect2D(Math.random() * context.getLevelWidth(),
                    Math.random() * context.getLevelHeight());
        }

        long now = System.currentTimeMillis();

        if ((now - 10000 >= idleTo) && Math.random() * 10 > 9) {
            idleTo = now + 500 + (long) (Math.random() * 1500);
        }

        if (now >= idleTo) {
            object.animate();
            if (Math.abs(dest.x - object.pos.x) > object.delta.x ||
                    Math.abs(dest.y - object.pos.y) > object.delta.y) {
                if (dest.x > object.pos.x + object.delta.x) {
                    if (dest.y > object.pos.y + object.delta.y) {
                        object.addCommand(MoveCommand.MOVE_SE);
                    } else if (dest.y < object.pos.y - object.delta.y) {
                        object.addCommand(MoveCommand.MOVE_NE);
                    } else {
                        object.addCommand(MoveCommand.MOVE_E);
                    }
                } else if (dest.x < object.pos.x - object.delta.x) {
                    if (dest.y > object.pos.y + object.delta.y) {
                        object.addCommand(MoveCommand.MOVE_SW);
                    } else if (dest.y < object.pos.y - object.delta.y) {
                        object.addCommand(MoveCommand.MOVE_NW);
                    } else {
                        object.addCommand(MoveCommand.MOVE_W);
                    }
                } else {
                    if (dest.y > object.pos.y + object.delta.y) {
                        object.addCommand(MoveCommand.MOVE_S);
                    } else if (dest.y < object.pos.y - object.delta.y) {
                        object.addCommand(MoveCommand.MOVE_N);
                    } else {
                        dest = null;
                    }
                }
            }
        }
    }
}
