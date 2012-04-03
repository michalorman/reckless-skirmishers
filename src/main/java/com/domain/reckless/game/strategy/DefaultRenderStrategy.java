package com.domain.reckless.game.strategy;

import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.Collidable;
import com.domain.reckless.world.Renderable;

import java.util.Collection;

public class DefaultRenderStrategy implements RenderStrategy {
    private long lastRenderTime = 0;

    public void render(Screen screen, Collection<? extends Renderable> renderables, int fps) {
        screen.blit(Assets.Bitmaps.background, 0, 0);
        for (Renderable renderable : renderables) {
            renderable.render(screen);
        }
        screen.write(Assets.Fonts.font, 5, 5, fps + " FPS");
    }

    @Override
    public void render(GameContext context, int fps) {
        Screen screen = context.getScreen();
        boolean drawBBox = context.getSettings().getBool(Settings.Setting.DRAW_BBOX);
        boolean drawFps = context.getSettings().getBool(Settings.Setting.DRAW_FPS);

        screen.rectFill(0, 0, 600, 400, 0xff000000);

        Bitmap bitmap = context.getLevelBitmap();
        screen.blit(bitmap, 0, 0);

        screen.centerAt(context.getPlayerRenderPosX(),
                context.getPlayerRenderPosY());

        for (Renderable renderable : context.getRenderableObjects()) {
            renderable.render(screen);

            if (drawBBox && renderable instanceof Collidable) {
                screen.rectFill(((Collidable) renderable).getBoundingBox(), 0x55ff00ff);
            }
        }

        screen.setOffset(0, 0);

        if (drawFps) {
            screen.write(Assets.Fonts.font, 5, 5, fps + " FPS");
        }

        screen.write(Assets.Fonts.font, 395, 5, String.format("X: %d, Y: %d", context.getPlayerRenderPosX(), context.getPlayerRenderPosY()));
    }
}
