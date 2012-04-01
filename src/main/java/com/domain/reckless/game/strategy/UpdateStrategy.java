package com.domain.reckless.game.strategy;

import com.domain.reckless.world.Updateable;

import java.util.Set;

public interface UpdateStrategy {
    void update(Set<? extends Updateable> updateables);
}
