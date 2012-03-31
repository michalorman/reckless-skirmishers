package com.domain.reckless.core;

import com.domain.reckless.game.DefaultGameLoop;
import com.domain.reckless.game.Game;
import com.domain.reckless.game.GameLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecklessSkirmishers {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecklessSkirmishers.class);

    public static void main(String[] args) {
        LOGGER.info("Bootstraping Reckless Skirmishers...");

        GameLoop gameLoop = new DefaultGameLoop(null);
        Thread thread = new Thread(new Game());
        thread.start();
    }
}
