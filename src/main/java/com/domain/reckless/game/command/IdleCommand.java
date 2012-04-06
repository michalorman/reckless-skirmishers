package com.domain.reckless.game.command;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

import static com.domain.reckless.world.GameObject.Facing.IDLE;

/**
 * Commands game object to stay idle.
 */
public class IdleCommand implements GameCommand {
    @Override
    public void execute(GameContext context, GameObject object) {
        object.setCurrentFacing(IDLE);
    }
}
