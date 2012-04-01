package com.domain.reckless.game.strategy;

import com.domain.reckless.world.Updateable;

import java.util.Set;

public class DefaultUpdateStrategy implements UpdateStrategy {
    @Override
    public void update(Set<? extends Updateable> updateables) {
        for (Updateable updateable : updateables) {
            updateable.update();
        }
    }
}
