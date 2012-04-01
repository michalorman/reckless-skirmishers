package com.domain.reckless.game;

import com.domain.reckless.game.strategy.RenderStrategy;
import com.domain.reckless.game.strategy.UpdateStrategy;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.GameObject;
import com.domain.reckless.world.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of {@link GameContext} that delegates update and render action
 * to appropriate components.
 */
public class StrategyDrivenGameContext implements GameContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyDrivenGameContext.class);

    private UpdateStrategy updateStrategy;

    private RenderStrategy renderStrategy;

    private Set<GameObject> objects = new TreeSet<>();

    public StrategyDrivenGameContext(UpdateStrategy updateStrategy, RenderStrategy renderStrategy) {
        LOGGER.info("Creating strategy driven game context");
        this.updateStrategy = updateStrategy;
        this.renderStrategy = renderStrategy;

        // TODO: test objects
        Player player = new Player();
        player.pos = new Vect2D(100, 100);
        player.delta = new Vect2D(1.5, 1.5);
        objects.add(player);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void update() {
        updateStrategy.update(objects);
    }

    @Override
    public void render(float interpolation, int fps) {
        renderStrategy.render(objects, fps);
    }
}
