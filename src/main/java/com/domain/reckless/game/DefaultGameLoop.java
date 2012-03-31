package com.domain.reckless.game;

import com.domain.reckless.graphics.DefaultScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultGameLoop implements GameLoop {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGameLoop.class);

    private DefaultScreen screen;

    private boolean isRunning = true;

    public DefaultGameLoop(DefaultScreen screen) {
        LOGGER.info("Initializing default game loop");
        this.screen = screen;
    }

    public void run() {
        LOGGER.debug("Executing main loop");
        while (isRunning) {

        }
    }
}
