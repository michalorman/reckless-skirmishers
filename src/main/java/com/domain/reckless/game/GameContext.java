package com.domain.reckless.game;

import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.Player;
import com.domain.reckless.world.Renderable;
import com.domain.reckless.world.Updateable;

import java.util.Collection;

/**
 * Interface for components responsible for storing information about level and
 * game objects. Basing on this information provides renderable, collidable
 */
public interface GameContext {

    /**
     * Indicates if the game is running, or user quits the game.
     *
     * @return <tt>true</tt> if still running otherwise <tt>false</tt>.
     */
    boolean isRunning();

    Collection<? extends Renderable> getRenderableObjects();

    Collection<? extends Collidable> getCollidableObjects();

    Collection<? extends Updateable> getUpdateableObject();

    int getPlayerRenderPosX();

    int getPlayerRenderPosY();

    Player getPlayer();

    /**
     * Cuts level bitmap relatively to players position with specified
     * width and height which should be set to width and height of the
     * target (eg. screen) where the bitmap will be pasted.
     *
     * @param width The width of bitmap to return.
     * @param height The height of bitmap to return.
     * @return Level bitmap.
     */
    Bitmap getLevelBitmap(int width, int height);
}
