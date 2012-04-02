package com.domain.reckless.game.strategy;

import com.domain.reckless.world.Renderable;

import java.util.Collection;
import java.util.Set;

public interface RenderStrategy {
    void render(Collection<? extends Renderable> renderables, int fps);
}
