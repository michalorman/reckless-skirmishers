package com.domain.reckless.game.strategy;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.Updateable;

import java.util.Collection;

public class DefaultUpdateStrategy implements UpdateStrategy {
    @Override
    public void update(GameContext context, Collection<? extends Updateable> updateables) {
        for (Updateable updateable : updateables) {
            updateable.update(context);
        }
    }
}
