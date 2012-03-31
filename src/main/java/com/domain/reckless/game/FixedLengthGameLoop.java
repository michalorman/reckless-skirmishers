package com.domain.reckless.game;

import com.domain.reckless.graphics.DefaultScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedLengthGameLoop implements GameLoop {
    private static final Logger LOGGER = LoggerFactory.getLogger(FixedLengthGameLoop.class);

    private DefaultScreen screen;

    private boolean isRunning = true;

    // Limits the update amounts before render
    private static final int MAX_UPDATES_PER_RENDER = Integer.MAX_VALUE;

    // desired updates rate
    private static final double UPDATES_RATE = 3;

    // time between game updates
    private static final double UPDATES_INTERVAL = 1000000000 / UPDATES_RATE;

    // desired frame rate
    private static final double FRAMES_RATE = 6;

    // time between each render
    private static final double RENDERS_INTERVAL = 1000000000 / FRAMES_RATE;

    public FixedLengthGameLoop(DefaultScreen screen) {
        LOGGER.info("Initializing default game loop");
        this.screen = screen;
    }

    public void run() {
        LOGGER.info("Executing main loop");

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();

        double now;
        int updatesCount;

        while (isRunning) {
            now = System.nanoTime();
            updatesCount = 0;

            while (now - lastUpdateTime > UPDATES_INTERVAL && updatesCount < MAX_UPDATES_PER_RENDER) {
                // TODO: add game update here
                lastUpdateTime += UPDATES_INTERVAL;
                ++updatesCount;
            }

            if (now - lastRenderTime > RENDERS_INTERVAL) {
                float delta = Math.min(1.0f, (float) ((now - lastUpdateTime) / UPDATES_INTERVAL));
                // TODO: add render here
                lastRenderTime = now;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // consume
            }
        }
    }
}
