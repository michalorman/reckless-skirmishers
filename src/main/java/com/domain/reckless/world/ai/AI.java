package com.domain.reckless.world.ai;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;

public interface AI<T extends GameObject> {
    void nextMove(T object, GameContext context, Keyboard keyboard);
}
