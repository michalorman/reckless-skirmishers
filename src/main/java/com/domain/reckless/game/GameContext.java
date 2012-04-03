package com.domain.reckless.game;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.Player;
import com.domain.reckless.world.Renderable;
import com.domain.reckless.world.level.Level;

import java.util.Collection;

/**
 * Single point of all information regarding the running game.
 */
public interface GameContext {

    /**
     * Indicates if the game is running, or user quits the game.
     *
     * @return <tt>true</tt> if still running otherwise <tt>false</tt>.
     */
    boolean isRunning();

    /**
     * Update the game state.
     */
    void update();

    /**
     * Render the game.
     *
     * @param interpolation the value of interpolation.
     * @param fps
     */
    void render(float interpolation, int fps);

    /**
     * Returns the keyboard handler.
     *
     * @return keyboard handler.
     */
    Keyboard getKeyboard();

    Screen getScreen();

    Collection<? extends Renderable> getRenderableObjects();

    Collection<? extends Collidable> getCollidableObjects();

    Bitmap getLevelBitmap();

    int getPlayerRenderPosX();

    int getPlayerRenderPosY();
}
