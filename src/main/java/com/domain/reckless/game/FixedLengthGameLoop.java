package com.domain.reckless.game;

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
    private static final double UPDATES_RATE = 30;

    // time between game updates
    private static final double UPDATES_INTERVAL = NANOS_PER_SECOND / UPDATES_RATE;

    // desired frame rate
    private static final double FRAMES_RATE = 120;

    // time between each render
    private static final double RENDERS_INTERVAL = NANOS_PER_SECOND / FRAMES_RATE;

    private GameContext context;

    private GameRenderer renderer;

    private GameUpdater updater;

    public FixedLengthGameLoop(GameContext context, GameRenderer renderer, GameUpdater updater) {
        LOGGER.info("Initializing default game loop");
        this.renderer = renderer;
        this.updater = updater;
        this.context = context;
    }

    public void run() {
        LOGGER.info("Executing main loop");

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();

        double now;
        int updatesCount;

//        double startSim = System.nanoTime();

        while (context.isRunning()) {
            now = System.nanoTime();
            updatesCount = 0;

            while (now - lastUpdateTime >= UPDATES_INTERVAL && updatesCount < MAX_UPDATES_PER_RENDER) {
                updater.update(context);
                lastUpdateTime += UPDATES_INTERVAL;
                ++updatesCount;
            }

            if (now - lastRenderTime >= RENDERS_INTERVAL) {
                float delta = Math.min(1.0f, (float) ((now - lastUpdateTime) / UPDATES_INTERVAL));
                renderer.render(context, delta);
                lastRenderTime += RENDERS_INTERVAL;
            }

//            if (now - startSim >= 5 * NANOS_PER_SECOND) {
//                context = context.dup();
//                startSim = now;
//            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // consume
            }
        }
    }
}
