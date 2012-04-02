package com.domain.reckless.game;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.strategy.RenderStrategy;
import com.domain.reckless.game.strategy.UpdateStrategy;
import com.domain.reckless.graphics.FrameContext;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.*;
import com.domain.reckless.world.ai.AI;
import com.domain.reckless.world.ai.RandomAI;
import com.domain.reckless.world.ai.RandomDestAI;
import com.domain.reckless.world.anim.FixedDurationAnimation;
import com.domain.reckless.world.level.Level;
import com.domain.reckless.world.level.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Implementation of {@link GameContext} that delegates update and render action
 * to appropriate components.
 */
public class StrategyDrivenGameContext implements GameContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyDrivenGameContext.class);

    private UpdateStrategy updateStrategy;

    private RenderStrategy renderStrategy;

    private Level level;

    private Collection<GameObject> objects = new ArrayList<>();

    private FrameContext frameContext;

    private Player player;

    public StrategyDrivenGameContext(FrameContext frameContext, UpdateStrategy updateStrategy, RenderStrategy renderStrategy) {
        LOGGER.info("Creating strategy driven game context");
        this.frameContext = frameContext;
        this.updateStrategy = updateStrategy;
        this.renderStrategy = renderStrategy;

        AI randomAI = new RandomAI();
        AI destAI = new RandomDestAI();

        // TODO: test objects
        for (int i = 0; i < 5 + Math.random() * 10; i++) {
            Enemy enemy = new Enemy(randomAI, Assets.Bitmaps.pharao, new FixedDurationAnimation(25, 4));
            enemy.pos = new Vect2D(Math.random() * 500, Math.random() * 380);
            enemy.delta = new Vect2D(1, 1);
            objects.add(enemy);
        }

        for (int i = 0; i < 15 + Math.random() * 50; i++) {
            Enemy enemy = new Enemy(destAI, Assets.Bitmaps.mummy, new FixedDurationAnimation(200, 4, true));
            enemy.pos = new Vect2D(Math.random() * 500, Math.random() * 380);
            enemy.delta = new Vect2D(0.5 + Math.random(), 0.5 + Math.random());
            objects.add(enemy);
        }

        player = new Player();
        player.pos = new Vect2D(Math.random() * 500, Math.random() * 380);
        player.delta = new Vect2D(3, 3);
        objects.add(player);
        generateLevel();
    }

    private void generateLevel() {
        level = new Level(Level.SMALL, Level.SMALL);
        for (int i = 0; i < level.tiles.length; i++) {
            level.tiles[i] = new Tile();
        }
        level.createBitmap();
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void update() {
        updateStrategy.update(this, objects);
    }

    @Override
    public void render(float interpolation, int fps) {
        renderStrategy.render(this, fps);
        frameContext.renderScreen();
    }

    @Override
    public Keyboard getKeyboard() {
        return frameContext.getKeyboard();
    }

    @Override
    public Screen getScreen() {
        return frameContext.getScreen();
    }

    @Override
    public Collection<? extends Renderable> getGameObjects() {
        return objects;
    }

    @Override
    public Bitmap getLevelBitmap() {
        return level.bitmap.cut((int) player.pos.x, (int) player.pos.y, frameContext.getScreenW(), frameContext.getScreenH());
    }
}
