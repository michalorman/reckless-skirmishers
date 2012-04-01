package com.domain.reckless.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedLengthGameLoop implements GameLoop {
    private static final Logger LOGGER = LoggerFactory.getLogger(FixedLengthGameLoop.class);

    // Limits the update amounts before render
    private static final int MAX_UPDATES_PER_RENDER = Integer.MAX_VALUE;

    // desired updates rate
    private static final double UPDATES_RATE = 30;

    // time between game updates
    private static final double UPDATES_INTERVAL = 1000000000 / UPDATES_RATE;

    // desired frame rate
    private static final double FRAMES_RATE = 60;

    // time between each render
    private static final double RENDERS_INTERVAL = 1000000000 / FRAMES_RATE;

    private static final double FPS_UPDATE_INTERVAL = 1000000000;

    private GameContext context;

    public FixedLengthGameLoop(GameContext context) {
        LOGGER.info("Initializing default game loop");
        this.context = context;
    }

    public void run() {
        LOGGER.info("Executing main loop");

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();

        double now;
        int updatesCount;

        int frames = 0;
        int fps = (int) FRAMES_RATE;
        double lastFpsUpdateTime = System.nanoTime();

        while (context.isRunning()) {
            now = System.nanoTime();
            updatesCount = 0;

            while (now - lastUpdateTime > UPDATES_INTERVAL && updatesCount < MAX_UPDATES_PER_RENDER) {
                context.update();
                lastUpdateTime += UPDATES_INTERVAL;
                ++updatesCount;
            }

            if (now - lastRenderTime > RENDERS_INTERVAL) {
                float delta = Math.min(1.0f, (float) ((now - lastUpdateTime) / UPDATES_INTERVAL));
                context.render(delta, fps);
                lastRenderTime = now;
                ++frames;
            }

            if (now - lastFpsUpdateTime > FPS_UPDATE_INTERVAL) {
                fps = frames;
                frames = 0;
                lastFpsUpdateTime = now;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // consume
            }
        }
    }
}
