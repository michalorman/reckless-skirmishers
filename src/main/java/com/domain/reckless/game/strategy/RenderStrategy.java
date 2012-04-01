package com.domain.reckless.game.strategy;

import com.domain.reckless.world.Renderable;

import java.util.Set;

public interface RenderStrategy {
    void render(Set<? extends Renderable> renderables);
}
