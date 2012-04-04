package com.domain.reckless.world;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;

public interface Updateable {
    void update(GameContext context, Keyboard keyboard);
}
