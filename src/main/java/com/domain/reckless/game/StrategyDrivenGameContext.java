package com.domain.reckless.game;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.game.strategy.RenderStrategy;
import com.domain.reckless.game.strategy.UpdateStrategy;
import com.domain.reckless.graphics.FrameContext;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.*;
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

    private Settings settings;

    public StrategyDrivenGameContext(FrameContext frameContext, UpdateStrategy updateStrategy, RenderStrategy renderStrategy, Settings settings) {
        LOGGER.info("Creating strategy driven game context");
        this.frameContext = frameContext;
        this.updateStrategy = updateStrategy;
        this.renderStrategy = renderStrategy;
        this.settings = settings;

        generateLevel();

        // TODO: test objects
        for (int i = 0; i < Math.random() * 4; i++) {
            Enemy enemy = new Enemy(new RandomDestAI(),
                    Assets.Bitmaps.pharao,
                    new FixedDurationAnimation(250, 4, true),
                    new Rectangle(5, 20, 28, 32));
            enemy.pos = new Vect2D(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            enemy.delta = new Vect2D(0.75, 0.75);
            objects.add(enemy);
        }

        for (int i = 0; i < 2 + Math.random() * 8; i++) {
            Enemy enemy = new Enemy(new RandomDestAI(),
                    Assets.Bitmaps.mummy,
                    new FixedDurationAnimation(200, 4, true),
                    new Rectangle(5, 20, 28, 32));
            enemy.pos = new Vect2D(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            enemy.delta = new Vect2D(0.5 + Math.random(), 0.5 + Math.random());
            objects.add(enemy);
        }

        for (int i = 0; i < 5 + Math.random() * 15; i++) {
            Enemy enemy = new Enemy(new RandomDestAI(),
                    Assets.Bitmaps.snake,
                    new FixedDurationAnimation(75, 4, true),
                    new Rectangle(10, 25, 20, 32));
            enemy.pos = new Vect2D(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            enemy.delta = new Vect2D(2.75, 2.75);
            objects.add(enemy);
        }

        player = new Player();
        player.pos = new Vect2D(0, 0);
        player.delta = new Vect2D(3.5, 3.5);
        objects.add(player);
    }

    private void generateLevel() {
        level = new Level(Level.SMALL, Level.SMALL);

        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7, 1, 1, 1, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0,50, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0,50, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0,50, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0,51,51,51,51, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0,10, 9, 1, 1, 1, 8,11, 0, 0, 0, 0,50,50,50,50, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0,50, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 1, 1, 0, 0, 0, 2, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0,50, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,52, 0, 0, 1, 1, 0, 0, 0, 2, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0,50, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,52, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,52,52,52, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,52, 0, 0, 0, 0, 0, 0, 0,12, 4, 4, 4, 4, 4,13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3,53,53,53, 0, 0,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1},
                {1, 1, 1, 8, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        Bitmap[] tiles = {
                Assets.Bitmaps.floorTiles[0][5],
                Assets.Bitmaps.floorTiles[0][0],
                Assets.Bitmaps.floorTiles[1][3],
                Assets.Bitmaps.floorTiles[1][1],
                Assets.Bitmaps.floorTiles[1][2],
                Assets.Bitmaps.floorTiles[1][0],
                Assets.Bitmaps.floorTiles[1][7],
                Assets.Bitmaps.floorTiles[1][6],
                Assets.Bitmaps.floorTiles[1][5],
                Assets.Bitmaps.floorTiles[1][4],
                Assets.Bitmaps.floorTiles[2][0],
                Assets.Bitmaps.floorTiles[2][1],
                Assets.Bitmaps.floorTiles[2][2],
                Assets.Bitmaps.floorTiles[2][3],
        };

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int mapCode = map[y][x];
                int tileCode = mapCode >= 50 ? mapCode - 50 : mapCode;
                level.tiles[y * level.width + x] = new Tile(tiles[tileCode]);
                if (mapCode >= 50) {
                    ImmobileObject im = new ImmobileObject(mapCode - 50);
                    im.pos = new Vect2D(x * 32 - frameContext.getScreenWidth() / 2,
                            y * 32 - 12 - frameContext.getScreenHeight() / 2);
                    objects.add(im);
                }
            }
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
    public Collection<? extends Renderable> getRenderableObjects() {
        return new TreeSet<>(objects);
    }

    @Override
    public Collection<? extends Collidable> getCollidableObjects() {
        return objects;
    }

    @Override
    public Bitmap getLevelBitmap() {
        return level.bitmap.cut((int) player.pos.x,
                (int) player.pos.y,
                frameContext.getScreenWidth(),
                frameContext.getScreenHeight());
    }

    @Override
    public int getPlayerRenderPosX() {
        return player.getRenderPosX();
    }

    @Override
    public int getPlayerRenderPosY() {
        return player.getRenderPosY();
    }

    @Override
    public Settings getSettings() {
        return settings;
    }
}
