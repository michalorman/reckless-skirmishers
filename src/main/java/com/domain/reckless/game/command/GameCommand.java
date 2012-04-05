package com.domain.reckless.game.command;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

/**
 * Interface for commands that may be given to any {@link GameObject}
 */
public interface GameCommand {
    void execute(GameContext context, GameObject object);
}
