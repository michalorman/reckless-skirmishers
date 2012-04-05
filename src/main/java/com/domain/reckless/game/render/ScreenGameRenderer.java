package com.domain.reckless.game.render;

import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.Renderable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenGameRenderer implements GameRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenGameRenderer.class);

    private Screen screen;
    private long frames;
    private long lastFramesUpdate = System.currentTimeMillis();
    private long fps;
    private Settings settings;
    private long startTime = System.currentTimeMillis();

    public ScreenGameRenderer(Screen screen, Settings settings) {
        this.settings = settings;
        LOGGER.info("Creating screen game renderer");
        this.screen = screen;
    }

    @Override
    public void render(GameContext context, float interpolation) {
        boolean drawBBox = settings.getBool(Settings.Setting.DRAW_BBOX);
        boolean drawFps = settings.getBool(Settings.Setting.DRAW_FPS);
        boolean drawPlayerInfo = settings.getBool(Settings.Setting.DRAW_PLAYER_INFO);
        boolean drawTime = settings.getBool(Settings.Setting.DRAW_TIME);

        Bitmap bitmap = context.getLevelBitmap(screen.getWidth(), screen.getHeight());
        screen.blit(bitmap, 0, 0);

        screen.centerAt(context.getPlayerRenderPosX(),
                context.getPlayerRenderPosY());

        for (Renderable renderable : context.getRenderableObjects()) {
            renderable.render(screen, interpolation);

            if (drawBBox && renderable instanceof Collidable) {
                screen.rectFill(((Collidable) renderable).getBoundingBox(), 0x55ff00ff);
            }
        }

        screen.setOffset(0, 0);

        if (drawPlayerInfo) {
            screen.write(Assets.Fonts.fontGold, 5, 20, String.format("X: %d Y: %d", (int) context.getPlayer().pos.x, (int) context.getPlayer().pos.y));
        }

        if (drawTime) {
            long seconds = (System.currentTimeMillis() - startTime) / 1000;
            screen.write(Assets.Fonts.fontGold, screen.getWidth() - 70, 5, String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60)));
        }

        if (drawFps) {
            ++frames;
            long now = System.currentTimeMillis();

            if (now - lastFramesUpdate >= 1000) {
                fps = frames;
                frames = 0;
                lastFramesUpdate += 1000;
            }

            screen.write(Assets.Fonts.fontGold, 5, 5, fps + " FPS");
        }

        screen.render();
    }
}
