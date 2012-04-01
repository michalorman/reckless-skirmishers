package com.domain.reckless.game;

import com.domain.reckless.game.strategy.RenderStrategy;
import com.domain.reckless.game.strategy.UpdateStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link GameContext} that delegates update and render action
 * to appropriate components.
 */
public class StrategyDrivenGameContext implements GameContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyDrivenGameContext.class);

    private UpdateStrategy updateStrategy;
    private RenderStrategy renderStrategy;

    public StrategyDrivenGameContext(UpdateStrategy updateStrategy, RenderStrategy renderStrategy) {
        LOGGER.info("Creating strategy driven game context");
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
