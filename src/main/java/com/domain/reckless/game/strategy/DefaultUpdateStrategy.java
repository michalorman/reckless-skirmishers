package com.domain.reckless.game.strategy;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.world.Updateable;

import java.util.Collection;
import java.util.Set;

public class DefaultUpdateStrategy implements UpdateStrategy {
    private Keyboard keyboard;

    public DefaultUpdateStrategy(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void update(Collection<? extends Updateable> updateables) {
        for (Updateable updateable : updateables) {
            updateable.update(keyboard);
        }
    }
}
