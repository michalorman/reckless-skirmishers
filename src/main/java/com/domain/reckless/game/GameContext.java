package com.domain.reckless.game;

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
}
