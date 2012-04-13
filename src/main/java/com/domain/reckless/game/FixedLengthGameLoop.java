package com.domain.reckless.game;

import com.domain.reckless.game.input.GameInputHandler;
import com.domain.reckless.game.render.GameRenderer;
import com.domain.reckless.game.update.GameUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedLengthGameLoop implements GameLoop {
    private static final Logger LOGGER = LoggerFactory.getLogger(FixedLengthGameLoop.class);

    // Limits the update amounts before render
    private static final int MAX_UPDATES_PER_RENDER = Integer.MAX_VALUE;

    private static final double NANOS_PER_SECOND = 1000000000;

    // desired updates rate
    private static final double UPDATES_RATE = 60;

    // time between game updates
    private static final double UPDATES_INTERVAL = NANOS_PER_SECOND / UPDATES_RATE;

    // desired frame rate
    private static final double FRAMES_RATE = 60;

    // time between each render
    private static final double RENDERS_INTERVAL = NANOS_PER_SECOND / FRAMES_RATE;

    private GameContext context;

    private GameRenderer renderer;

    private GameUpdater updater;

    private GameInputHandler inputHandler;

    public FixedLengthGameLoop(GameContext context, GameRenderer renderer, GameUpdater updater,
                               GameInputHandler inputHandler) {
        LOGGER.info("Initializing default game loop");
        this.renderer = renderer;
        this.updater = updater;
        this.context = context;
        this.inputHandler = inputHandler;
    }

    public void run() {
        LOGGER.info("Executing main loop");

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();

        double now;
        int updatesCount;

        while (context.isRunning()) {
            now = System.nanoTime();
            updatesCount = 0;

            while (now - lastUpdateTime >= UPDATES_INTERVAL && updatesCount < MAX_UPDATES_PER_RENDER) {
                inputHandler.handle(context);
                updater.update(context);
                lastUpdateTime += UPDATES_INTERVAL;
                ++updatesCount;
            }

            if (now - lastRenderTime >= RENDERS_INTERVAL) {
                renderer.render(context);
                lastRenderTime += RENDERS_INTERVAL;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // consume
            }
        }
    }
}
