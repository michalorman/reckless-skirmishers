package com.domain.reckless.game.strategy;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.Updateable;

import java.util.Collection;
import java.util.Set;

public interface UpdateStrategy {
    void update(GameContext context, Collection<? extends Updateable> updateables);
}
