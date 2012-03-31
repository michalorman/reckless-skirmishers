package com.domain.reckless.game;

import com.domain.reckless.graphics.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultGameLoop implements GameLoop {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGameLoop.class);

    private Screen screen;

    private boolean isRunning = true;

    public DefaultGameLoop(Screen screen) {
        LOGGER.info("Initializing default game loop");
        this.screen = screen;
    }

    public void run() {
        while (isRunning) {

        }
    }
}
