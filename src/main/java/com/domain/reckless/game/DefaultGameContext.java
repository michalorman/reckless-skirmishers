package com.domain.reckless.game;

import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.math.Vect2d;
import com.domain.reckless.world.*;
import com.domain.reckless.world.ai.SimpleAI;
import com.domain.reckless.world.anim.FixedDurationAnimation;
import com.domain.reckless.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import static com.domain.reckless.Constants.Speed.*;

/**
 * Implementation of {@link GameContext} that delegates update and render action
 * to appropriate components.
 */
public class DefaultGameContext implements GameContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGameContext.class);

    private Level level;

    private Collection<GameObject> objects = new ArrayList<>();

    private GameObject player;

    private Settings settings;

    public DefaultGameContext(Level level, Settings settings) {
        LOGGER.info("Creating default game context");
        this.settings = settings;
        this.level = level;

        // TODO: test objects
        for (int i = 0; i < 20 + Math.random() * 4; i++) {
            GameObject object = new GameObject(GameObjectSpec.PHARAO);
            object.setAi(new SimpleAI());
            object.setAnimation(new FixedDurationAnimation(250, 4, true));
            object.pos = new Vect2d(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            object.delta = new Vect2d(VERY_SLOW, VERY_SLOW);
            objects.add(object);
        }

        for (int i = 0; i < 40 + Math.random() * 8; i++) {
            GameObject object = new GameObject(GameObjectSpec.MUMMY);
            object.setAi(new SimpleAI());
            object.setAnimation(new FixedDurationAnimation(200, 4, true));
            object.pos = new Vect2d(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            object.delta = new Vect2d(SLOW, SLOW);
            objects.add(object);
        }

        for (int i = 0; i < 5 + Math.random() * 15; i++) {
            GameObject object = new GameObject(GameObjectSpec.SNAKE);
            object.setAi(new SimpleAI());
            object.setAnimation(new FixedDurationAnimation(75, 4, true));
            object.pos = new Vect2d(Math.random() * level.bitmap.getWidth() - 100, Math.random() * level.bitmap.getHeight() - 100);
            object.delta = new Vect2d(VERY_FAST, VERY_FAST);
            objects.add(object);
        }

        player = new GameObject(GameObjectSpec.PLAYER);
        player.setAnimation(new FixedDurationAnimation(100, 6, true));
        player.pos = new Vect2d(0, 0);
        player.delta = new Vect2d(NORMAL, NORMAL);
        objects.add(player);
        objects.addAll(level.getGameObjects());
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
    public GameObject getPlayer() {
        return player;
    }

    @Override
    public int getPlayerRenderPosX() {
        return player.getRenderPosX();
    }

    @Override
    public int getPlayerRenderPosY() {
        return player.getRenderPosY();
    }
}
