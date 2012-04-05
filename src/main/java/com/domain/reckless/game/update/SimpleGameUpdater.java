package com.domain.reckless.game.update;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.Updateable;

public class SimpleGameUpdater implements GameUpdater {

    @Override
    public void update(GameContext context) {
        for (Updateable updateable : context.getUpdateableObject()) {
            updateable.update(context);
        }
    }
}
