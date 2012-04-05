package com.domain.reckless.world.ai;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

/**
 * Interface for components handling AI that generates commands
 * to given game object.
 */
public interface AI {
    void command(GameContext context, GameObject object);
}
