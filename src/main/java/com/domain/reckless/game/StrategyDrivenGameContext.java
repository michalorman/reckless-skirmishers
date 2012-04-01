package com.domain.reckless.game;

import com.domain.reckless.game.strategy.RenderStrategy;
import com.domain.reckless.game.strategy.UpdateStrategy;

/**
 * Implementation of {@link GameContext} that delegates update and render action
 * to appropriate components.
 */
public class StrategyDrivenGameContext implements GameContext {

    private UpdateStrategy updateStrategy;
    private RenderStrategy renderStrategy;

    public StrategyDrivenGameContext(UpdateStrategy updateStrategy, RenderStrategy renderStrategy) {
        this.updateStrategy = updateStrategy;
        this.renderStrategy = renderStrategy;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void update() {
        updateStrategy.update(this);
    }

    @Override
    public void render(float interpolation) {
        renderStrategy.render(this);
    }
}
