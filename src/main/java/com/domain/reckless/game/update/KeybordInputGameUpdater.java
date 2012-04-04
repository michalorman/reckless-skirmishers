package com.domain.reckless.game.update;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.Updateable;

public class KeybordInputGameUpdater implements GameUpdater {

    private Keyboard keyboard;

    public KeybordInputGameUpdater(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void update(GameContext context) {
        for (Updateable updateable : context.getUpdateableObject()) {
            updateable.update(context, keyboard);
        }
    }
}
