package com.domain.reckless.game;

import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.*;
import com.domain.reckless.world.ai.SimpleAI;
import com.domain.reckless.world.anim.FixedDurationAnimation;
import com.domain.reckless.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Implementation of {@link GameContext} that delegates update and render action
 * to appropriate components.
 */
public class DefaultGameContext implements GameContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGameContext.class);

    private Level level;

    private Collection<GameObject> objects = new ArrayList<>();

    private Player player;

    private Settings settings;

    public DefaultGameContext(Level level, Settings settings) {
        LOGGER.info("Creating default game context");
        this.settings = settings;
        this.level = level;

        // TODO: test objects
        for (int i = 0; i < 20 + Math.random() * 4; i++) {
            Enemy enemy = new Enemy(new SimpleAI(),
                    Assets.Bitmaps.pharao,
                    new FixedDurationAnimation(250, 4, true),
                    new Rectangle(5, 20, 28, 32));
            enemy.pos = new Vect2D(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            enemy.delta = new Vect2D(1.75, 1.75);
            objects.add(enemy);
        }

        for (int i = 0; i < 40 + Math.random() * 8; i++) {
            Enemy enemy = new Enemy(new SimpleAI(),
                    Assets.Bitmaps.mummy,
                    new FixedDurationAnimation(200, 4, true),
                    new Rectangle(5, 20, 28, 32));
            enemy.pos = new Vect2D(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            enemy.delta = new Vect2D(0.5 + Math.random(), 0.5 + Math.random());
            objects.add(enemy);
        }

        for (int i = 0; i < 5 + Math.random() * 15; i++) {
            Enemy enemy = new Enemy(new SimpleAI(),
                    Assets.Bitmaps.snake,
                    new FixedDurationAnimation(75, 4, true),
                    new Rectangle(7, 20, 23, 30));
            enemy.pos = new Vect2D(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            enemy.delta = new Vect2D(2.75, 2.75);
            objects.add(enemy);
        }

        player = new Player();
        player.pos = new Vect2D(0, 0);
        player.delta = new Vect2D(3.5, 3.5);
        objects.add(player);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public Collection<? extends Renderable> getRenderableObjects() {
        Collection<Renderable> renderables = new TreeSet<>();

        renderables.addAll(objects);

        return renderables;
    }

    @Override
    public Collection<? extends Collidable> getCollidableObjects() {
        return objects;
    }

    @Override
    public Collection<? extends Updateable> getUpdateableObject() {
        return objects;
    }

    @Override
    public Bitmap getLevelBitmap(int width, int height) {
        return level.getBitmap((int) player.pos.x,
                (int) player.pos.y,
                width,
                height);
    }

    @Override
    public GameContext dup() {
        DefaultGameContext ctx = new DefaultGameContext(level, settings);
        ctx.player.pos = player.pos;
        return ctx;
    }

    @Override
    public int getLevelWidth() {
        return level.bitmap.w;
    }

    @Override
    public int getLevelHeight() {
        return level.bitmap.h;
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
    public Player getPlayer() {
        return player;
    }
}
