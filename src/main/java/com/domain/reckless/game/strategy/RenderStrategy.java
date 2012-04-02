package com.domain.reckless.game.strategy;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.game.StrategyDrivenGameContext;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.world.Renderable;

import java.util.Collection;
import java.util.Set;

public interface RenderStrategy {
    void render(GameContext context, int fps);
}
